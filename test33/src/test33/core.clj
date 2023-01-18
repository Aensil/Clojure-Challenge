(ns test33.core)

;---------------------------------

;Pure Functions Are Referentially Transparent
(+ 1 2)
; => 3

(defn wisdom
  [words]
  (str words ", Daniel-san"))

(wisdom "Always bathe on Fridays")
; => "Always bathe on Fridays, Daniel-san"

;NOT REFERENTIAL TRANSPARENCY
;depends in a random to get a result
(defn year-end-evaluation
  []
  (if (> (rand) 0.5)
    "You get a raise!"
    "Better luck next year!"))

(defn analyze-file
  [filename]
  (analysis (slurp filename)))

(defn analysis
  [text]
  (str "Character count: " (count text)))

;-------------------------------------------
;Values don't change
(def great-baby-name "Rosanthony")
great-baby-name
; => "Rosanthony"

(let [great-baby-name "Bloodthunder"]
  great-baby-name)
; => "Bloodthunder"

great-baby-name
; => "Rosanthony"

;----------------------------------------

(defn sum
  ([vals] (sum vals 0))
  ([vals accumulating-total]
   (if (empty? vals)
            accumulating-total
            (sum (rest vals) (+ (first vals) accumulating-total)))))


(sum [39 5 1]) ; single-arity body calls two-arity body
;(sum [39 5 1] 0)
;(sum [5 1] 39)
;(sum [1] 44)
;(sum [] 45) ; base case is reached, so return accumulating-total
; => 45

(require '[clojure.string :as s])
(defn clean
  [text]
  (s/replace (s/trim text) #"lol" "LOL"))

(clean "My boa constrictor is so sassy lol!  ")
; => "My boa constrictor is so sassy LOL!"

;-------------------------------

;COMP
((comp inc *) 2 3)
; => 7

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(fn [c] (:strength (:attributes c)))

(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(c-int character)
; => 10

(c-str character)
; => 4

(c-dex character)
; => 5

(filter (comp not zero?) [0 1 0 2 0 3 0 4])
;;=> (1 2 3 4)


;Spells base on slots
(defn spell-slots
  [char]
  (int (inc (/ (c-int char) 2))))

(spell-slots character)
; => 6

(def spell-slots-comp (comp int inc #(/ % 2) c-int))

;Compose 2 funtions without the COMP command
(defn two-comp
  [f g]
  (fn [& args]
    (f (apply g args))))

;-------------------------

;MEMOIZE
(+ 3 (+ 5 8))

(defn sleepy-identity
  "Returns the given value after 1 second"
  [x]
  (Thread/sleep 1000)
  x)
(sleepy-identity "Mr. Fantastico")
; => "Mr. Fantastico" after 1 second

(sleepy-identity "Mr. Fantastico")
; => "Mr. Fantastico" after 1 second

;Memoize version
(def memo-sleepy-identity (memoize sleepy-identity))
(memo-sleepy-identity "Mr. Fantastico")
; => "Mr. Fantastico" after 1 second
; "Elapsed time: 1000.7037 msecs"

;once it's catched it returns in O(1)
(memo-sleepy-identity "Mr. Fantastico")
; => "Mr. Fantastico" immediately
; "Elapsed time: 0.0899 msecs"

;-------------------------------------------------------------------------

(def p {:name "James" :age 26})
;;=> #'user/p

(update-in p [:age] inc)
;;=> {:name "James", :age 27}

;; remember, the value of p hasn't changed!
(update-in p [:age] + 10)
;;=> {:name "James", :age 36}












