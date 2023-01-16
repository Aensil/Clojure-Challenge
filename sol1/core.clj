(ns sol1.core)

(def invoice (clojure.edn/read-string (slurp "invoice.edn")))

(defn cases [[x y]] (= (= (= x 19) true) (= (= y 1) true))) ;True means trouble
;(defn evalll [x] (do (println x) (evall x)))

(defn valu [x] (list (get (first (get x :taxable/taxes [0 0])) :tax/rate 0) (get (first (get x :retentionable/retentions [0 0])) :retention/rate 0)))
;(defn k [x] (do (println x) (numb x)))

(->> (get invoice :invoice/items)
     (map valu)
     (into [])
     (map cases)
     ;------- (filter #(= % true))
     ;(some #(= % true))
     (def solution)
     )

(println solution)
;(println (evall [7 8]))