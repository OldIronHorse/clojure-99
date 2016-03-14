(ns fourclojure.core-test
  (:require [clojure.test :refer :all]
            [fourclojure.core :refer :all]))

(deftest q28
  "Flatten a sequence"
  (testing "nested lists and vectors"
    (is (= 
      '(1 2 3 4 5 6)
      (my-flatten '((1 2) 3 [4 [5 6]])))))
  (testing "strings"
    (is (=
    '("a" "b" "c")
    (my-flatten ["a" ["b"] "c"]))))
  (testing "symbol"
    (is (= '(:a) (my-flatten '((((:a)))))))))


(deftest q43
  "Reverse interleave"
  (testing "vector"
    (is (=
      '((1 3 5) (2 4 6))
      (reverse-interleave [1 2 3 4 5 6] 2))))
  (testing "range"
    (is (=
      '((0 3 6) (1 4 7) (2 5 8))
      (reverse-interleave (range 9) 3))))
  (testing "range again"
    (is (=
      '((0 5) (1 6) (2 7) (3 8) (4 9))
      (reverse-interleave (range 10) 5)))))
