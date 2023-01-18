(in-ns 'invoice-spec)
;(ns soll) ---INIT
;(require '[clojure.data.json :as json])                     ---INIT
(use 'clojure.walk)

(defn my-value-reader [key value]
  (case key
    :issue_date (.parse (java.text.SimpleDateFormat. "dd/MM/yyyy") value)
    :payment_date (.parse (java.text.SimpleDateFormat. "dd/MM/yyyy") value)
    :tax_category :iva
    :tax_rate (double value)
    value))

;(defn testt [f g] (do (println f g) (my-value-reader f g)))

(defn final [url]
  (let [jsonFile (json/read-str (slurp url)
                             :key-fn keyword
                             :value-fn my-value-reader)]
    (->> (prewalk-replace {
                            :issue_date      :invoice/issue-date
                            :customer        :invoice/customer
                            :items           :invoice/items
                            :company_name    :customer/name
                            :email           :customer/email
                            :price           :invoice-item/price
                            :quantity        :invoice-item/quantity
                            :sku             :invoice-item/sku
                            :taxes           :invoice-item/taxes
                            :tax_category    :tax/category
                            :tax_rate        :tax/rate
                            } jsonFile) (:invoice)))
  )
;Prewalk and postwalk work well here if prefer use postwalk

(println (s/valid? ::invoice (final "C:\\Users\\Aensiles\\IdeaProjects\\sol2\\src\\invoice.json")))

(println (s/explain ::invoice (final "C:\\Users\\Aensiles\\IdeaProjects\\sol2\\src\\invoice.json")))
(println (final "C:\\Users\\Aensiles\\IdeaProjects\\sol2\\src\\invoice.json"))
(println (json/read-str (slurp "C:\\Users\\Aensiles\\IdeaProjects\\sol2\\src\\invoice.json")))

;(load-file "C:\\Users\\Aensiles\\IdeaProjects\\sol2\\src\\soll.clj")
;(load-file "C:\\Users\\Aensiles\\IdeaProjects\\sol2\\src\\invoice-spec.clj")

