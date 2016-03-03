(ns clojure-99.core-test
  (:require [clojure.test :refer :all]
            [clojure-99.core :refer :all]))

(deftest p01
  "Find the last element of a list."
  (testing "my-last"
    (is (= 7 (my-last '(1 2 4 98 7))))
    (is (= 3 (my-last '(3))))
    (nil? (my-last '())))
  (testing "r-last"
    (is (= 7 (r-last '(1 2 4 98 7))))
    (is (= 3 (r-last '(3))))
    (nil? (r-last '())))
  (testing "last"
    (is (= 7 (last '(1 2 4 98 7))))
    (is (= 3 (last '(3))))
    (nil? (last '()))))

(deftest p02
  "Find the last 2 elements of a list."
  (testing "my-but-last"
    (is (= '(98 7) (my-but-last '(1 2 4 98 7))))
    (is (= '(3) (my-but-last '(3))))
    (nil? (my-but-last '()))))

(deftest p03
  "Find the kth element of a list"
  (testing "element-at"
    (is (= 4 (element-at 3 '(1 2 4 98 7))))
    (is (= nil (element-at 3 '(1 2))))
    (is (= nil (element-at 3 '())))
    (is (= nil (element-at 10 '(1 2 4 98 7))))))

(deftest p04
  "Find the number of elements in a list."
  (testing "my-count"
    (is (= 5 (my-count '(1 2 4 98 7))))
    (is (= 2 (my-count '(1 2))))
    (is (= 1 (my-count '(2))))
    (is (= 0 (my-count '()))))
  (testing "count"
    (is (= 5 (count '(1 2 4 98 7))))
    (is (= 2 (count '(1 2))))
    (is (= 1 (count '(2))))
    (is (= 0 (count '())))))
    
