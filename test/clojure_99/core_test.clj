(ns clojure-99.core-test
  (:require [clojure.test :refer :all]
            [clojure-99.core :refer :all]))

(deftest p01
  "Find the last element of a list."
  (testing "my-last"
    (is (= 7 (my-last '(1 2 4 98 7))))
    (is (= 3 (my-last '(3))))
    (is (nil? (my-last '()))))
  (testing "r-last"
    (is (= 7 (r-last '(1 2 4 98 7))))
    (is (= 3 (r-last '(3))))
    (is (nil? (r-last '()))))
  (testing "last"
    (is (= 7 (last '(1 2 4 98 7))))
    (is (= 3 (last '(3))))
    (is (nil? (last '())))))

(deftest p02
  "Find the last 2 elements of a list."
  (testing "my-but-last"
    (is (= '(98 7) (my-but-last '(1 2 4 98 7))))
    (is (= '(3) (my-but-last '(3))))
    (is (nil? (my-but-last '())))))

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

(deftest p05
  "Reverse a list"
  (testing "reverse"
    (is (= '() (reverse '())))
    (is (= '(5) (reverse '(5))))
    (is (= '(2 1) (reverse '(1 2))))
    (is (= '(3 2 1) (reverse '(1 2 3)))))
  (testing "r-reverse")
    (is (= '() (r-reverse '())))
    (is (= '(5) (r-reverse '(5))))
    (is (= '(2 1) (r-reverse '(1 2))))
    (is (= '(3 2 1) (r-reverse '(1 2 3))))
  (testing "f-reverse")
    (is (= '() (f-reverse '())))
    (is (= '(5) (f-reverse '(5))))
    (is (= '(2 1) (f-reverse '(1 2))))
    (is (= '(3 2 1) (f-reverse '(1 2 3)))))

(deftest p06
  "Test for a palindrome"
  (testing "palindrome?"
    (is (palindrome? '()))
    (is (palindrome? '(1)))
    (is (palindrome? '(1 1)))
    (is (palindrome? '(1 2 1)))
    (is (not (palindrome? '(1 2))))
    (is (not (palindrome? '(1 1 2))))
    (is (not (palindrome? '(1 2 3 1)))))
  (testing "r-palindrome?"
    (is (r-palindrome? '()))
    (is (r-palindrome? '(1)))
    (is (r-palindrome? '(1 1)))
    (is (r-palindrome? '(1 2 1)))
    (is (not (r-palindrome? '(1 2))))
    (is (not (r-palindrome? '(1 1 2))))
    (is (not (r-palindrome? '(1 2 3 1)))))
  (testing "f-palindrome?"
    (is (f-palindrome? '()))
    (is (f-palindrome? '(1)))
    (is (f-palindrome? '(1 1)))
    (is (f-palindrome? '(1 2 1)))
    (is (not (f-palindrome? '(1 2))))
    (is (not (f-palindrome? '(1 1 2))))
    (is (not (f-palindrome? '(1 2 3 1))))))

(deftest p07
  "Flatten a nested list."
  (testing "flatten"
    (is (= '() (flatten '())))
    (is (= '(1) (flatten '(1))))
    (is (= '(1 2 3 4) (flatten '(1 2 3 4))))
    (is (= '(1 2 3 4) (flatten '((1) 2 3 4))))
    (is (= '(1 2 3 4) (flatten '((1 2) 3 4))))
    (is (= '(1 2 3 4) (flatten '(1 (2 3) 4))))
    (is (= '(1 2 3 4) (flatten '(1 2 (3 4)))))
    (is (= '(1 2 3 4) (flatten '(1 (2 3 4)))))
    (is (= '(1 2 3 4) (flatten '((1 2 3) 4))))
    (is (= '(1 2 3 4) (flatten '(1 (2 (3 4))))))
    (is (= '(1 2 3 4) (flatten '(1 ((2 3) 4))))))
  (testing "r-flatten"
    (is (= '() (r-flatten '())))
    (is (= '(1) (r-flatten '(1))))
    (is (= '(1 2 3 4) (r-flatten '(1 2 3 4))))
    (is (= '(1 2 3 4) (r-flatten '((1) 2 3 4))))
    (is (= '(1 2 3 4) (r-flatten '((1 2) 3 4))))
    (is (= '(1 2 3 4) (r-flatten '(1 (2 3) 4))))
    (is (= '(1 2 3 4) (r-flatten '(1 2 (3 4)))))
    (is (= '(1 2 3 4) (r-flatten '(1 (2 3 4)))))
    (is (= '(1 2 3 4) (r-flatten '((1 2 3) 4))))
    (is (= '(1 2 3 4) (r-flatten '(1 (2 (3 4))))))
    (is (= '(1 2 3 4) (r-flatten '(1 ((2 3) 4))))))
  (testing "f-flatten"
    (is (= '() (f-flatten '())))
    (is (= '(1) (f-flatten '(1))))
    (is (= '(1 2 3 4) (f-flatten '(1 2 3 4))))
    (is (= '(1 2 3 4) (f-flatten '((1) 2 3 4))))
    (is (= '(1 2 3 4) (f-flatten '((1 2) 3 4))))
    (is (= '(1 2 3 4) (f-flatten '(1 (2 3) 4))))
    (is (= '(1 2 3 4) (f-flatten '(1 2 (3 4)))))
    (is (= '(1 2 3 4) (f-flatten '(1 (2 3 4)))))
    (is (= '(1 2 3 4) (f-flatten '((1 2 3) 4))))
    (is (= '(1 2 3 4) (f-flatten '(1 (2 (3 4))))))
    (is (= '(1 2 3 4) (f-flatten '(1 ((2 3) 4)))))))

(deftest p08
  "Eliminate consecutive duplicates"
  (testing "dedupe"
    (is (= '() (dedupe '())))
    (is (= '(1 2 4 3 2 6) (dedupe '(1 2 4 3 2 6))))
    (is (= '(1 2 4 3 2 6) (dedupe '(1 2 2 2 4 3 2 6))))
    (is (= '(1 2 4 3 2 6) (dedupe '(1 2 2 2 4 3 2 2 6))))
    (is (= '(1 2 4 3 2 6) (dedupe '(1 1 2 2 4 3 2 6))))
    (is (= '(1 2 4 3 2 6) (dedupe '(1 2 4 3 3 2 6 6 6)))))
  (testing "r-dedupe"
    (is (= '() (r-dedupe '())))
    (is (= '(1 2 4 3 2 6) (r-dedupe '(1 2 4 3 2 6))))
    (is (= '(1 2 4 3 2 6) (r-dedupe '(1 2 2 2 4 3 2 6))))
    (is (= '(1 2 4 3 2 6) (r-dedupe '(1 2 2 2 4 3 2 2 6))))
    (is (= '(1 2 4 3 2 6) (r-dedupe '(1 1 2 2 4 3 2 6))))
    (is (= '(1 2 4 3 2 6) (r-dedupe '(1 2 4 3 3 2 6 6 6))))))
