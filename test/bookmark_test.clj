(ns bookmark-test
  (:require [clojure.test :refer :all]
            [bookmark :refer :all]))

(defmacro with-bookmark [begin end duration & body]
  `(let [{:keys ~'[pages-per-day bookmarks total-pages]} (bookmark/bookmark ~begin ~end ~duration)]
     (do ~@body)))

(deftest bookmark-test
  (testing "bookmark list length"
    (with-bookmark 0 119 7
      (is (= (count bookmarks) 7)))
    (with-bookmark 0 119 1
      (is (= (count bookmarks) 1))))
  (testing "total pages"
    (with-bookmark 0 119 7
      (is (= total-pages 119)))
    (with-bookmark 9 199 7
      (is (= total-pages 190)))))

