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

(deftest q44
  "rotate a list"
  (testing "rotate left"
    (is (= '(3 4 5 1 2) (rotate 2 '(1 2 3 4 5)))))
  (testing "rotate right"
    (is (= '(4 5 1 2 3) (rotate -2 '(1 2 3 4 5)))))
  (testing "rotate more that length"
    (is (= '(2 3 4 5 1) (rotate 6 '(1 2 3 4 5)))))
  (testing "symbols"
    (is (= '(:b :c :a) (rotate 1 '(:a :b :c)))))
  (testing "over rotate right"
    (is (= '(:c :a :b) (rotate -4 '(:a :b :c))))))
