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
  (testing "f-dedupe"
    (is (= '() (f-dedupe '())))
    (is (= '(1 2 4 3 2 6) (f-dedupe '(1 2 4 3 2 6))))
    (is (= '(1 2 4 3 2 6) (f-dedupe '(1 2 2 2 4 3 2 6))))
    (is (= '(1 2 4 3 2 6) (f-dedupe '(1 2 2 2 4 3 2 2 6))))
    (is (= '(1 2 4 3 2 6) (f-dedupe '(1 1 2 2 4 3 2 6))))
    (is (= '(1 2 4 3 2 6) (f-dedupe '(1 2 4 3 3 2 6 6 6)))))
  (testing "r-dedupe"
    (is (= '() (r-dedupe '())))
    (is (= '(1 2 4 3 2 6) (r-dedupe '(1 2 4 3 2 6))))
    (is (= '(1 2 4 3 2 6) (r-dedupe '(1 2 2 2 4 3 2 6))))
    (is (= '(1 2 4 3 2 6) (r-dedupe '(1 2 2 2 4 3 2 2 6))))
    (is (= '(1 2 4 3 2 6) (r-dedupe '(1 1 2 2 4 3 2 6))))
    (is (= '(1 2 4 3 2 6) (r-dedupe '(1 2 4 3 3 2 6 6 6))))))

(deftest p09
  "Pack consecutive duplicates into sublists"
  (testing "r-pack"
    (is (= '() (r-pack '())))
    (is (= '((2)) (r-pack '(2))))
    (is (= '((2 2 2)) (r-pack '(2 2 2))))
    (is (= '((1) (2) (4) (3) (2) (6)) (r-pack '(1 2 4 3 2 6))))
    (is (= '((1) (2 2 2) (4) (3) (2) (6)) (r-pack '(1 2 2 2 4 3 2 6))))
    (is (=
      '((1) (2 2 2) (4) (3) (2 2) (6))
      (r-pack '(1 2 2 2 4 3 2 2 6))))
    (is (= '((1 1) (2 2) (4) (3) (2) (6)) (r-pack '(1 1 2 2 4 3 2 6))))
    (is (= 
      '((1) (2) (4) (3 3) (2) (6 6 6))
      (r-pack '(1 2 4 3 3 2 6 6 6))))
    (is (=
      '((1 1 1 1) (2) (3 3) (1 1) (4) (5 5 5 5))
      (r-pack '(1 1 1 1 2 3 3 1 1 4 5 5 5 5)))))
  (testing "f-pack"
    (is (= '() (f-pack '())))
    (is (= '((2)) (f-pack '(2))))
    (is (= '((2 2 2)) (f-pack '(2 2 2))))
    (is (= '((1) (2) (4) (3) (2) (6)) (f-pack '(1 2 4 3 2 6))))
    (is (= '((1) (2 2 2) (4) (3) (2) (6)) (f-pack '(1 2 2 2 4 3 2 6))))
    (is (=
      '((1) (2 2 2) (4) (3) (2 2) (6))
      (f-pack '(1 2 2 2 4 3 2 2 6))))
    (is (= '((1 1) (2 2) (4) (3) (2) (6)) (f-pack '(1 1 2 2 4 3 2 6))))
    (is (= 
      '((1) (2) (4) (3 3) (2) (6 6 6))
      (f-pack '(1 2 4 3 3 2 6 6 6))))
    (is (=
      '((1 1 1 1) (2) (3 3) (1 1) (4) (5 5 5 5))
      (f-pack '(1 1 1 1 2 3 3 1 1 4 5 5 5 5))))))

(deftest p10
  "Run-length encode a list."
  (testing "rl-encode"
    (is (= '() (rl-encode '())))
    (is (= '((1 \a)) (rl-encode '(\a))))
    (is (= 
      '((4 \a) (1 \b) (2 \c) (2 \a) (1 \d) (4 \e))
      (rl-encode '(\a \a \a \a \b \c \c \a \a \d \e \e \e \e))))))

(deftest p11
  "Modified run-length encode a list."
  (testing "mrl-encode"
    (is (= '() (mrl-encode '())))
    (is (= '(\a) (mrl-encode '(\a))))
    (is (= 
      '((4 \a) \b (2 \c) (2 \a) \d (4 \e))
      (mrl-encode '(\a \a \a \a \b \c \c \a \a \d \e \e \e \e))))))
      
(deftest p12
  "Decode a modified run-length encoded list."
  (testing "mrl-decode"
    (is (= '() (mrl-decode '())))
    (is (= '(\a) (mrl-decode '(\a))))
    (is (= 
      '(\a \a \a \a \b \c \c \a \a \d \e \e \e \e)
      (mrl-decode '((4 \a) \b (2 \c) (2 \a) \d (4 \e)))))))

(deftest p13
  "Direct modified run-length encode a list."
  (testing "dmrl-encode"
    (is (= '() (dmrl-encode '())))
    (is (= '(\a) (dmrl-encode '(\a))))
    (is (= 
      '((4 \a) \b (2 \c) (2 \a) \d (4 \e))
      (dmrl-encode '(\a \a \a \a \b \c \c \a \a \d \e \e \e \e))))))

(deftest p14
  "Duplicate the elements in a list."
  (testing "duplicate"
    (is (= '() (duplicate '())))
    (is (= '(1 1) (duplicate '(1))))
    (is (= '(1 1 2 2 3 3 4 4) (duplicate '(1 2 3 4))))))
      
(deftest p15
  "Replicate the elements of a list a given number of times."
  (testing "my-replicate"
    (is (= '() (my-replicate 3 '())))
    (is (= '() (my-replicate 0 '(1 2 3 4))))
    (is (= '(1 1 1 2 2 2 3 3 3 4 4 4) (my-replicate 3 '(1 2 3 4))))))

(deftest p16
  "Drop every kth element of a list (1-base index)."
  (testing "f-drop-every"
    (is (= '() (f-drop-every 3 '())))
    (is (= '(1 2 3 4) (f-drop-every 7 '(1 2 3 4))))
    (is (= '(1 2 4 5 7 8) (f-drop-every 3 '(1 2 3 4 5 6 7 8 9))))
    (is (= '(1 2 4 5 7 8) (f-drop-every 3 '(1 2 3 4 5 6 7 8 ))))
    (is (=
      '(\a \b \d \e \g \h)
      (f-drop-every 3 '(\a \b \c \d \e \f \g \h))))
    (is (= '() (f-drop-every 1 '(1 2 3 4 5 6 7 8 )))))
  (testing "r-drop-every"
    (is (= '() (r-drop-every 3 '())))
    (is (= '(1 2 3 4) (r-drop-every 7 '(1 2 3 4))))
    (is (= '(1 2 4 5 7 8) (r-drop-every 3 '(1 2 3 4 5 6 7 8 9))))
    (is (= '(1 2 4 5 7 8) (r-drop-every 3 '(1 2 3 4 5 6 7 8 ))))
    (is (=
      '(\a \b \d \e \g \h)
      (r-drop-every 3 '(\a \b \c \d \e \f \g \h))))
    (is (= '() (r-drop-every 1 '(1 2 3 4 5 6 7 8 ))))))
