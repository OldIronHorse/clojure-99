(ns clojure-99.core-test
  (:require [clojure.test :refer :all]
            [clojure-99.core :refer :all]))

(deftest P01
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
