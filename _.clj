(println (+ 1 2))

(def a {:objs [] :arws []})

(def b (conj (:objs a) 'a))

(println b)
