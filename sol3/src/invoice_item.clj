(ns invoice-item)

(defn- discount-factor [{:invoice-item/keys [discount-rate]
                         :or                {discount-rate 0}}]
  (- 1 (/ discount-rate 100.0)))

(defn subtotal
  [{:invoice-item/keys [precise-quantity precise-price discount-rate]
    :as                item
    :or                {discount-rate 0}}]
  (* precise-price precise-quantity (discount-factor item)))

;-----------------------------------------------------------------
;Test
(use 'clojure.test)

(deftest Transparency
  (is (= (subtotal {
                    :invoice-item/precise-quantity  46
                    :invoice-item/precise-price     59.999
                    :invoice-item/discount-rate     6
                    }) 2594.35676) "Same 0")
  (is (= (subtotal {
                    :invoice-item/precise-quantity  46
                    :invoice-item/precise-price     59.999
                    :invoice-item/discount-rate     6
                    }) 2594.35676) "same 1")
  (is (= (subtotal {
                    :invoice-item/precise-quantity  46
                    :invoice-item/precise-price     59.999
                    :invoice-item/discount-rate     6
                    }) 2594.35676) "same 2")
  (is (= (subtotal {
                    :invoice-item/precise-quantity  46
                    :invoice-item/precise-price     59.999
                    :invoice-item/discount-rate     6
                    }) 2594.35676) "same 3")
)

(deftest Diversity
  (is (= (subtotal {
                    :invoice-item/precise-quantity  1685
                    :invoice-item/precise-price     0.123
                    :invoice-item/discount-rate     50
                    }) 103.6275) "Diversity 0")
  (is (= (subtotal {
                    :invoice-item/precise-quantity  99.89
                    :invoice-item/precise-price     85.13
                    :invoice-item/discount-rate     0.5
                    }) 8461.1175215) "Diversity 1")
  (is (= (subtotal {
                    :invoice-item/precise-quantity  0.005
                    :invoice-item/precise-price     96
                    :invoice-item/discount-rate     95.2
                    }) 0.02304) "Diversity 2")
  (is (= (subtotal {
                    :invoice-item/precise-quantity  1752437
                    :invoice-item/precise-price     752541528
                    :invoice-item/discount-rate     3.2752
                    }) 1275588882160703.238528) "Diversity 3")
  )

(deftest Crazy
  (is (= (subtotal {
                    :invoice-item/precise-quantity  -85
                    :invoice-item/precise-price     29.23
                    :invoice-item/discount-rate     0
                    }) -2484.55) "Crazy 0")
  (is (= (subtotal {
                    :invoice-item/precise-quantity  9
                    :invoice-item/precise-price     31.25
                    :invoice-item/discount-rate     -1.5
                    }) 285.46875) "crazy 1")
  (is (= (subtotal {
                    :invoice-item/precise-quantity  0.005
                    :invoice-item/precise-price     96
                    :invoice-item/discount-rate     95.2
                    }) 0.02304) "Crazy 2")
  (is (= (subtotal {
                    :invoice-item/precise-quantity  1752437
                    :invoice-item/precise-price     752541528
                    :invoice-item/discount-rate     3.2752
                    }) 1275588882160703.238528) "Crazy 3")
  (is (= (subtotal {
                    :invoice-item/precise-quantity  6
                    :invoice-item/precise-price     8
                    :invoice-item/discount-rate     nil
                    }) 48) "Crazy 4")
  (is (= (subtotal {
                    :invoice-item/precise-quantity  "6"
                    :invoice-item/precise-price     8
                    :invoice-item/discount-rate     5
                    }) 48) "Crazy 5")
  )

(run-tests 'invoice-item)