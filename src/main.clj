(ns main
  (:gen-class)
  (:require bookmark))

(defn -main [& args]
  (when (not= (count args) 3)
    (throw (Exception. "Wrong number of arguments")))
  (let [[begin end duration] args]
    (println (bookmark/bookmark begin end duration))))
