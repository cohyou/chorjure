](println (+ 1 2))

(def a {:objs [] :arws []})

(def b (conj (:objs a) 'a))

(println b)

(defrecord Arrow [parent first second contents])

(def pos (->Arrow nil nil nil "pos")) ; 半順序集合

(def data1 (->Arrow nil nil nil "data1")) ; 2-圏 Data
(def schema1 (->Arrow data1 nil nil "schema1")) ; スキーマ
(def collage1 (->Arrow schema1 nil nil "collage1")) ; コラージュ
(def sets (->Arrow schema1 nil nil "sets")) ; 集合と写像の圏、Sets
(def instance1 (->Arrow schema1 collage1 sets "instance1")) ; データインスタンス

; コラージュの中身（まずはTypeだけ
; TypeのPresentation
; algebraic signature Sigmaは (S-Sigma, Phi-Sigma) で定義される
; S-Sigmaはbase sortの集合、Phi-Sigmaはfunction symbolの集合
(def base-sorts (->Arrow schema1 nil nil "base-sorts")) ; 親がschema1なのはひとまず
(def function-symbols (->Arrow schema1 nil nil "function-symbols")) ; 親がschema1なのはひとまず

; base sortsはまずはBoolだけ
(def bool-sort (->Arrow base-sorts nil nil "bool-sort")) ; ひとまず真偽値

; function symbolはnotだけ
(def function-not (->Arrow function-symbols [bool-sort] bool-sort "function-not")) ; BOOL -> BOOL
(def function-not-true (->Arrow function-not [t-value] f-value "function-not-true")) ; true |-> false
(def function-not-false (->Arrow function-not [f-value] t-value "function-not-false")) ; false |-> true

; context
; context "gamma over lambda" は集合Gamma(v)と写像Gamma(s): Gamma(v) -> S-Sigma(base sortの集合)
; 言い換えると、スライス圏 Set/S-Sigma
; もしくはS-Sigmaを離散圏とみなした場合の関手圏 [S-Sigma, Set]と同値
; Gamma = (x1: s1, ..., xn: sn) とエンコードされる (Gamma(v)が有限の場合)
; 要するに、型付き項集合のことである。
(def context1 (->Arrow schema1 nil nil "context1")) ; 親がschema1なのはひとまず
(def symbol-a (->Arrow context1 nil nil "symbol-a"))
(def typed-symbol-a (->Arrow context1 symbol-a bool-sort "typed-symbol-a")) ; 1つだけ

; instance
(def bool-set (->Arrow instance1 nil nil "bool-set"))
(def t-value (->Arrow bool-set nil nil "true-value")) ; true
(def f-value (->Arrow bool-set nil nil "false-value")) ; false

(def a (->Arrow bool-inst bool-sort bool-set "bool-inst"))

(println (:contents pos))
