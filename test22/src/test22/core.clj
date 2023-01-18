(ns test22.core)



(defn titleize
  [topic]
  (str topic " for the Brave and True"))

(map titleize ["Hamsters" "Ragnarok"])
; => ("Hamsters for the Brave and True" "Ragnarok for the Brave and True")

(map titleize '("Empathy" "Decorating"))
; => ("Empathy for the Brave and True" "Decorating for the Brave and True")

(map titleize #{"Elbows" "Soap Carving"})
; => ("Elbows for the Brave and True" "Soap Carving for the Brave and True")

;Only sending the second term in this case "Winking"
(map #(titleize (second %)) {:uncomfortable-thing "Winking"})
; => ("Winking for the Brave and True")

;-----------------------------------------------

;Polimorfism

(seq '(1 2 3))
; => (1 2 3)

(seq [1 2 3])
; => (1 2 3)

(seq #{1 2 3})
; => (1 2 3)

(seq {:name "Bill Compton" :occupation "Dead mopey guy"})
; => ([:name "Bill Compton"] [:occupation "Dead mopey guy"])

;RESUL CAST BACK TO MAP
(into {} (seq {:a 1 :b 2 :c 3}))
; => {:a 1, :c 3, :b 2}

;-----------------------------
;Funtions

;MAP
(map inc [1 2 3])
; => (2 3 4)

(map str ["a" "b" "c"] ["A" "B" "C"])
; => ("aA" "bB" "cC")

(list (str "a" "A") (str "b" "B") (str "c" "C"))
; => ("aA" "bB" "cC")


(def human-consumption [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human   human
   :critter critter})

(map unify-diet-data human-consumption critter-consumption)
; => ({:human 8.1, :critter 0.0}
; => {:human 7.3, :critter 0.2}
; => {:human 6.6, :critter 0.3}
; => {:human 5.0, :critter 1.1})


(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))

(stats [3 4 10])
; => (17 3 17/3)

(stats [80 1 44 13 6])
; => (144 5 144/5)

;-------------------------

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])

(map :real identities)
; => ("Bruce Wayne" "Peter Parker" "Your mom" "Your dad")

;------------------------

;REDUCE
;Reduce here to change the values of a map
(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {}
        {:max 30 :min 10})
; => {:max 31, :min 11}

(assoc {:a 1} :b 2)
; => {:a 1, :b 2}

(assoc (assoc {} :max (inc 30))
  :min (inc 10))
; => {:max 31, :min 11}

;reduce to filter out keys from a map based on their value
(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
        {}
        {:human   4.1
         :critter 3.9})
; => {:human 4.1}

;------------------------------------
;take while drop
(take 3 [1 2 3 4 5 6 7 8 9 10])
; => (1 2 3)

(drop 3 [1 2 3 4 5 6 7 8 9 10])
; => (4 5 6 7 8 9 10)


(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

#_; =>
        ({:month 1, :day 1, :human 5.3, :critter 2.3}
         {:month 1, :day 2, :human 5.1, :critter 2.0}
         {:month 2, :day 1, :human 4.9, :critter 2.1}
         {:month 2, :day 2, :human 5.0, :critter 2.5})

(drop-while #(< (:month %) 3) food-journal)
#_; =>
        ({:month 3 :day 1 :human 4.2 :critter 3.3}
         {:month 3 :day 2 :human 4.0 :critter 3.8}
         {:month 4 :day 1 :human 3.7 :critter 3.9}
         {:month 4 :day 2 :human 3.7 :critter 3.6})

;--------------------------------------------------
(filter #(< (:human %) 5) food-journal)
#_ ; =>
({:month 2 :day 1 :human 4.9 :critter 2.1}
 {:month 3 :day 1 :human 4.2 :critter 3.3}
 {:month 3 :day 2 :human 4.0 :critter 3.8}
 {:month 4 :day 1 :human 3.7 :critter 3.9}
 {:month 4 :day 2 :human 3.7 :critter 3.6})

(filter #(< (:month %) 3) food-journal)
#_ ; =>
({:month 1 :day 1 :human 5.3 :critter 2.3}
{:month 1 :day 2 :human 5.1 :critter 2.0}
{:month 2 :day 1 :human 4.9 :critter 2.1}
{:month 2 :day 2 :human 5.0 :critter 2.5})

(some #(> (:critter %) 5) food-journal)
; => nil

;Useful to find if and element and return true if found
(some #(> (:critter %) 3) food-journal)
; => true

;Also works like filter
(some #(and (> (:critter %) 3) %) food-journal)
; => {:month 3 :day 1 :human 4.2 :critter 3.3}

;--------------------------------------
;SORT
(sort [3 1 2])
; => (1 2 3)

(sort-by count ["aaa" "c" "bb"])
; => ("c" "bb" "aaa")

;-------------------------------------
;COMCAT
(concat [1 2] [3 4])
; => (1 2 3 4)

;---------------------------------------------
(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))

;---------
(time (vampire-related-details 0))
; => "Elapsed time: 1334.5964 msecs"
; => {:name "McFishwich", :makes-blood-puns? false, :has-pulse? true}

;maps is lazy
(time (def mapped-details (map vampire-related-details (range 0 1000000))))
; => "Elapsed time: 0.049 msecs"
; => #'user/mapped-details

(time (first mapped-details))
; => "Elapsed time: 0.022 msecs"
; => {:name "McFishwich", :makes-blood-puns? false, :has-pulse? true}

(time (identify-vampire (range 0 1000000)))
; => "Elapsed time: 32019.912 msecs"
; => {:name "Damon Salvatore", :makes-blood-puns? true, :has-pulse? false}

;-----------
;LAZY REPEAT
(concat (take 8 (repeat "na")) ["Batman!"])
; => ("na" "na" "na" "na" "na" "na" "na" "na" "Batman!")

;Generation of 3 randoms
(take 3 (repeatedly (fn [] (rand-int 10))))                 ;RANDOM FROM 0-9
; => (8 4 0)

;TAKE or FIRST makes funtions determine a endpoint
;even-numbers INFINITE FUNTION that adds '+2' starting 0
(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(take 10 (even-numbers))
; => (0 2 4 6 8 10 12 14 16 18)

;Infinite fibonacci
(defn fib
  ([]
   (fib 1 1))
  ([a b]
   (lazy-seq (cons a (fib b (+ a b))))))

(take 5 (fib))
; => (1 1 2 3 5)

(cons 0 '(2 4 6))
; => (0 2 4 6)

;-----------------------------------------
(empty? [])
; => true

(empty? ["no!"])
; => false
;---------------------------------------------

;INTO to cast
(map identity {:sunlight-reaction "Glitter!"})
; => ([:sunlight-reaction "Glitter!"])

(into {} (map identity {:sunlight-reaction "Glitter!"}))
; => {:sunlight-reaction "Glitter!"}

(map identity [:garlic :sesame-oil :fried-eggs])
; => (:garlic :sesame-oil :fried-eggs)

(into [] (map identity [:garlic :sesame-oil :fried-eggs]))
; => [:garlic :sesame-oil :fried-eggs]

;Use of set and cast back to {}
(map identity [:garlic-clove :garlic-clove])
; => (:garlic-clove :garlic-clove)

(into #{} (map identity [:garlic-clove :garlic-clove]))
; => #{:garlic-clove}

(into {:favorite-emotion "gloomy"} [[:sunlight-reaction "Glitter!"]])
; => {:favorite-emotion "gloomy" :sunlight-reaction "Glitter!"}

(into ["cherry"] '("pine" "spruce"))
; => ["cherry" "pine" "spruce"]

(into {:favorite-animal "kitty"} {:least-favorite-smell "dog"
                                  :relationship-with-teenager "creepy"})
#_ ; =>
{:favorite-animal "kitty"
:relationship-with-teenager "creepy"
:least-favorite-smell "dog"}

;--------------------------------------

;CONJ
(conj [0] [1])
; => [0 [1]]

(into [0] [1])
; => [0 1]

(conj [0] 1)
; => [0 1]

(conj [0] 1 2 3 4)
; => [0 1 2 3 4]

(conj {:time "midnight"} [:place "ye olde cemetarium"])
; => {:time "midnight", :place "ye olde cemetarium"}

(defn my-conj
  [target & additions]
  (into target additions))

(my-conj [0] 1 2 3)
; => [0 1 2 3]

;-------------------------------------------------

;FUNCTIONS FUNCTIONS
(max 0 1 2)
; => 2

(max [0 1 2])
; => [0 1 2]

(apply max [0 1 2])
; => 2

(defn my-into
  [target additions]
  (apply conj target additions))

(my-into [0] [1 2 3])
; => [0 1 2 3]

;--------------------------------------

;PARTIAL
(def add10 (partial + 10))
(add10 3)
; => 13
(add10 5)
; => 15

(def add-missing-elements
  (partial conj ["water" "earth" "air"]))

(add-missing-elements "unobtainium" "adamantium")
; => ["water" "earth" "air" "unobtainium" "adamantium"]

(defn my-partial
  [partialized-fn & args]
  (fn [& more-args]
    (apply partialized-fn (into args more-args))))
#_ (fn [& more-args]
     (apply + (into [20] more-args)))

(def add20 (my-partial + 20))
(add20 3)
; => 23


(defn lousy-logger
  [log-level message]
  (condp = log-level
    :warn (clojure.string/lower-case message)
    :emergency (clojure.string/upper-case message)))

(def warn (partial lousy-logger :warn))

(warn "Red light ahead")
; => "red light ahead"

;-----------------

;COMPLEMENT
(defn identify-humans
  [social-security-numbers]
  (filter #(not (vampire? %))
          (map vampire-related-details social-security-numbers)))

(def not-vampire? (complement vampire?))
(defn identify-humans
  [social-security-numbers]
  (filter not-vampire?
          (map vampire-related-details social-security-numbers)))

;----------
(defn my-complement
  [fun]
  (fn [& args]
    (not (apply fun args))))

(def my-pos? (complement neg?))
(my-pos? 1)
; => true

(my-pos? -1)
; => false

;---------------------------------------------------------------------

;"BELOW THE SUPOSE .CSV FILE"
;Edward Cullen,10
;Bella Swan,0
;Charlie Swan,0
;Jacob Black,3
;Carlisle Cullen,6


;(ns fwpd.core)
(def filename "suspects.csv")

(slurp filename)
; => "Edward Cullen,10\nBella Swan,0\nCharlie Swan,0\nJacob Black,3\nCarlisle Cullen,6"

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(convert :glitter-index "3")
; => 3

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(parse (slurp filename))
; => (["Edward Cullen" "10"] ["Bella Swan" "0"] ["Charlie Swan" "0"]
;           ["Jacob Black" "3"] ["Carlisle Cullen" "6"])

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(first (mapify (parse (slurp filename))))
; => {:glitter-index 10, :name "Edward Cullen"}

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(glitter-filter 3 (mapify (parse (slurp filename))))
#_ ; =>
({:name "Edward Cullen", :glitter-index 10}
 {:name "Jacob Black", :glitter-index 3}
 {:name "Carlisle Cullen", :glitter-index 6})










