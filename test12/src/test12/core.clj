(ns test12.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(foo "Abdel")

(+ 2 8 1)

(if true
  (do (println "Success!")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's trident!"))

(nil? 1)
; => false

(when true
  (println "Success!")
  "abra cadabra")

(or false nil :large_I_mean_venti :why_cant_I_just_say_large)

(or (= 0 1) (= "yes" "no"))

(or nil)
:----------------------
(def failed-protagonist-names
  ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])

failed-protagonist-names

;--------------------------------
(def severity :mild)
(def error-message "OH GOD! IT'S A DISASTER! WE'RE ")
(if (= severity :mild)
  (def error-message (str error-message "MILDLY INCONVENIENCED!"))
  (def error-message (str error-message "DOOOOOOOMED!")))


(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOOOOOMED!")))

(error-message :mild)
; => "OH GOD! IT'S A DISASTER! WE'RE MILDLY INCONVENIENCED!"
;-------------------------------------

(def name "Chewbacca")
(str "\"Uggllglglglglglglglll\" - " name)
; => "Uggllglglglglglglglll" - Chewbacca

(def name "Chewbacca")
(str "\"Uggllglglglglglglglll\" - " name)
; => "Uggllglglglglglglglll" - Chewbacca

;-------------------------------------

{"string-key" +}

;-----------------------------------------------

(hash-map :a 1 :b 2)

;-------------------------------------------

(get {:a 0 :b {:c "ho hum"}} :b)
; => {:c "ho hum"}

;----------------------------------------------

(get {:a 0 :b 1} :c)
; => nil

(get {:a 0 :b 1} :c "unicorns?")
; => "unicorns?"

;-----------------------------------------------

(get-in {:a 0 :b {:c "ho hum"}} [:b :c])
; => "ho hum"

({:name "The Human Coffeepot"} :name)
; => "The Human Coffeepot"

(:a {:a 1 :b 2 :c 3})
; => 1

(:d {:a 1 :b 2 :c 3} "No gnome knows homes like Noah knows")
; => "No gnome knows homes like Noah knows"

;----------------------------------------------

(get [3 2 1] 0)
; => 3

(get ["a" {:name "Pugsley Winterbottom"} "c"] 1)
; => {:name "Pugsley Winterbottom"}

(vector "creepy" "full" "moon")
; => ["creepy" "full" "moon"]

(conj [1 2 3] 4)
; => [1 2 3 4]

;----------------------------------------------

;#LIST

'(1 2 3 4)
; => (1 2 3 4)

(nth '(:a :b :c) 0)
; => :a

(list 1 "two" {3 4})
; => (1 "two" {3 4})

(conj '(1 2 3) 4)
; => (4 1 2 3)

;----------------------------------------------

;#SETS 'UNIQUE VALUES'

#{"kurt vonnegut" 20 :icicle}

(hash-set 1 1 2 2)
; => #{1 2}

(conj #{:a :b} :b)
; => #{:a :b}

(set [3 3 3 4 4])
; => #{3 4}

(contains? #{:a :b} :a)
; => true

(contains? #{:a :b} 3)
; => false

(contains? #{nil} nil)
; => true

(:a #{:a :b})
; => :a

(get #{:a :b} :a)
; => :a

(get #{:a nil} nil)
; => nil

(get #{:a :b} "kurt vonnegut")
; => nil

;------------------------------------

;#Funtions

(+ 1 2 3 4)
(* 1 2 3 4)
(first [1 2 3 4])

((or + -) 1 2 3)

(= 1 1)

((and (= 1 1) +) 1 2 3)
; => 6

((first [+ 0]) 1 2 3)
; => 6

(inc 1.1)
; => 2.1

(map inc [0 1 2 3])
; => (1 2 3 4)

;--------------------------------------------

(defn too-enthusiastic
  "Return a cheer that might be a bit too enthusiastic"
  [name]
  (str "OH. MY. GOD! " name " YOU ARE MOST DEFINITELY LIKE THE BEST "
       "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"))

(too-enthusiastic "Zelda")

;=> "OH. MY. GOD! Zelda YOU ARE MOST DEFINITELY LIKE THE BEST MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"

(defn no-params
  []
  "I take no parameters!")
(defn one-param
  [x]
  (str "I take one parameter: " x))
(defn two-params
  [x y]
  (str "Two parameters! That's nothing! Pah! I will smoosh them "
       "together to spite you! " x y))

;------------------------------------------
;Kinds 3 funtions on 1 depends on the amount of parameters you send to iy
#_
        (defn multi-arity
          ;; 3-arity arguments and body
          ([first-arg second-arg third-arg]
           (do-things first-arg second-arg third-arg))
          ;; 2-arity arguments and body
          ([first-arg second-arg]
           (do-things first-arg second-arg))
          ;; 1-arity arguments and body
          ([first-arg]
           (do-things first-arg)))

(defn x-chop
  "Describe the kind of chop you're inflicting on someone"
  ([name chop-type]
   (str "I " chop-type " chop " name "! Take that!"))
  ([name]
   (x-chop name "karate")))

(x-chop "Kanye West" "slap")
; => "I slap chop Kanye West! Take that!"

(x-chop "Kanye East")
; => "I karate chop Kanye East! Take that!"

;----------------------------------------------------

(defn weird-arity
  ([]
   "HEY PUT A NUMBER!!!")
  ([number]
   (inc number)))

(weird-arity 0)
;(weird-arity "fOL")
(weird-arity)

;------------------------------------------------

(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(codger "Billy" "Anne-Marie" "The Incredible Bulk")

;=> ("Get off my lawn, Billy!!!" "Get off my lawn, Anne-Marie!!!" "Get off my lawn, The Incredible Bulk!!!")


(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))

(favorite-things "Doreen" "gum" "shoes" "kara-te")
; => "Hi, Doreen, here are my favorite things: gum, shoes, kara-te"

;-------------------------------------------------------

;; Return the first element of a collection
(defn my-first
  [[first-thing]] ; Notice that first-thing is within a vector
  first-thing)

(my-first ["oven" "bike" "war-axe"])
; => "oven"

;--------------------------------------------------
;Destructing

;; Return the first element of a collection
(defn my-first
  [[first-thing]] ; Notice that first-thing is within a vector
  first-thing)

(my-first ["oven" "bike" "war-axe"])
; => "oven"


(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " unimportant-choices))))

(chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquaman"])
; => Your first choice is: Marmalade
; => Your second choice is: Handsome Jack
; => We're ignoring the rest of your choices. Here they are in case \
;you need to cry over them: Pigpen, Aquaman

;------------------------------------------
;DESTROYING MAPS 'dealing with jason files'

(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location {:lat 28.22 :lng 81.33})
; => Treasure lat: 28.22
; => Treasure lng: 81.33


(defn announce-treasure-location
  [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

;---------------------------------------
;Function Body

(defn illustrative-function
  []
  (+ 1 304)
  30
  "joe")

(illustrative-function)
; => "joe"

;------------------------------------------

(defn number-comment
  [x]
  (if (> x 6)
    "Oh my gosh! What a big number!"
    "That number's OK, I guess"))

(number-comment 5)
; => "That number's OK, I guess"

(number-comment 7)
; => "Oh my gosh! What a big number!"

;-------------------------------------------------
;Anonymous Functions
#_
        (fn [param-list]
          function body)

(map (fn [name] (str "Hi, " name))
     ["Darth Vader" "Mr. Magoo"])
; => ("Hi, Darth Vader" "Hi, Mr. Magoo")

((fn [x] (* x 3)) 8)
; => 24

(#(* % 3) 8)

(map #(str "Hi, " %)
     ["Darth Vader" "Mr. Magoo"])
; => ("Hi, Darth Vader" "Hi, Mr. Magoo")

;; Function call
(* 8 3)

;; Anonymous function
#(* % 3)

(#(str %1 " and " %2) "cornbread" "butter beans")
; => "cornbread and butter beans"

(#(identity %&) 1 "blarg" :yip)
; => (1 "blarg" :yip)

;---------------------------------------------
;Returning funtions

(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 7)
; => 10

;----------------------------------------------
;The Shire’s Next Top Model

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

(symmetrize-body-parts asym-hobbit-body-parts)

;-------
(let [x 3]
  x)
; => 3

(def dalmatian-list
  ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
(let [dalmatians (take 2 dalmatian-list)]
  dalmatians)
; => ("Pongo" "Perdita")

(def x 0)
(let [x 1] x)
(let [x (inc x)] x)
;pongo = first element of dalmatian
;dalmatians = rest of the dalmatian
(let [[pongo & dalmatians] dalmatian-list]
  [pongo dalmatians])
; => ["Pongo" ("Perdita" "Puppy 1" "Puppy 2")]
;-------

#_
        (let [[part & remaining] remaining-asym-parts]
          (recur remaining
                 (into final-body-parts
                       (set [part (matching-part part)]))))

(loop [iteration 0]
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration))))
; => Iteration 0
; => Iteration 1
; => Iteration 2
; => Iteration 3
; => Iteration 4
; => Goodbye!

;------------------------------------

;;Regular Expressions
(re-find #"^left-" "left-eye")
; => "left-"

(re-find #"^left-" "cleft-chin")
; => nil

(re-find #"^left-" "wongleblart")
; => nil

:--------------------------

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])


(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
    "Expects a seq of maps that have a :name and :size"
    [asym-body-parts]
    (loop [remaining-asym-parts asym-body-parts
               final-body-parts []]
               (if (empty? remaining-asym-parts)
                  final-body-parts
                        (let [[part & remaining] remaining-asym-parts]
                                    (recur remaining
                                             (into final-body-parts
                                                   (set [part (matching-part part)])))))))

;------------------------------------------
;; sum with reduce
(reduce + [1 2 3 4])
; => 10

;-------------------------------------------

(reduce + 15 [1 2 3 4])
; => 25

;-----------------------

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))

;---------------------

(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
            accumulated-size (:size part)]
       (if (> accumulated-size target)
         part
         (recur remaining (+ accumulated-size (:size (first remaining))))))))

(hit asym-hobbit-body-parts)
; => {:name "right-upper-arm", :size 3}

(hit asym-hobbit-body-parts)
; => {:name "chest", :size 10}

(hit asym-hobbit-body-parts)
; => {:name "left-eye", :size 1}

;----------------------------------
;EXERCISES
;#1

(def try ", Are you still there?")
(defn Hi [& x] (str "Hola " x try ))
(Hi "Karol" "Luisa" "Danny" "Julieth")
; => "Hola (\"Karol\" \"Luisa\" \"Danny\" \"Julieth\"), Are you still there?"

(def my-first-vector ["a" 3 "Hi"])
(defn show [[x b t]] t)
(show my-first-vector)
; => "Hi"

(def my-first-list '("a" 3 "Hi" '(4 7 9)))
(defn show [[x b t l]] l)
(show my-first-list)
; => (quote (4 7 9))

;hash-map has the form {}
(def H-m (hash-map :a 1 :b 2 :u "The sh*t"))
(get H-m :u)
; => "The sh*t"

(def h-s (hash-set 1 1 2 2 "-F" "I see you" "-F"))
(get h-s :3)
; => nil
(get h-s "-F")
; => "-F"


;#2
(defn adds100 "This adds a 100 to the number" [sum] (+ sum 100))
(adds100 5)
; => 105
(adds100 624)
; => 724

;#3
(defn deccre "This decrements with a custom number" [G] (- G 9))
(deccre 10)
; => 1

(dec 5)
; => 4


;#4
(defn mapset "Become a  set the result" [f x] (set (map f x)))
(mapset inc [1 1 2 2])
; => #{3 2}


;#5





