(ns main
  (:gen-class)
  (:require bookmark
            [clojure.tools.cli :refer [parse-opts]]))

(def parse-int #(Integer/parseInt %))

(def cli-options
  [[nil"--begin BEGIN" "Beginning page"
    :parse-fn parse-int]
   [nil "--end END" "Ending page"
    :parse-fn parse-int]
   [nil "--duration DURATION" "Reading duration in days, default is 7 days"
    :parse-fn parse-int
    :default 7]])

(defn validate-args
  [args]
  (let [parsed-opts (parse-opts args cli-options)
        {options :options, :as options-map} parsed-opts]
    (and (contains? options :begin)
         (contains? options :end)
         options-map)))

(defn error-missing-args
  []
  (.println *err* "Missing option --begin or --end")
  (System/exit 1))

(defn -main
  [& args]
  (if-let [parsed-opts (validate-args args)]
    (let [{options :options} parsed-opts
          {:keys [begin end duration]} options]
      (println parsed-opts)
      (println (bookmark/bookmark begin end duration)))
    (error-missing-args)))
