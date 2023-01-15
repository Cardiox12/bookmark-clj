(ns bookmark)

(defn -get-pages-per-day
  [total duration]
  (int (/ total duration)))

(defn -get-bookmarks-list
  [begin end duration]
  (let [pages-per-day (-get-pages-per-day (- end begin) duration)]
    (loop [current (+ begin pages-per-day)
           bookmarks []]
      (if (> (+ current pages-per-day) end)
        (conj bookmarks end)
        (recur (+ current pages-per-day)
               (conj bookmarks current))))))
 
(defn -get-bookmarks-statistics
  "Returns the statistics map composed of : bookmarks, pages per day, total pages"
  [begin end duration]
  (let [total-pages (- end begin)]
    {:bookmarks (-get-bookmarks-list begin end duration)
     :pages-per-day (-get-pages-per-day total-pages duration)
     :total-pages total-pages}))

(defn pretty-print-bookmarks
  [stats])

(defn bookmark
  "Returns a map with statistics about bookmarking a book specific duration"
  [begin end duration]
  (-get-bookmarks-statistics begin end duration))
