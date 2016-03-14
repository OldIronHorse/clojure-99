(ns clojure-99.core-test
  (:require [clojure.test :refer :all]
            [clojure-99.core :refer :all]))

;Lists

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

(deftest p17
  "Split a list into 2 parts. The length of the first part is specified."
  (testing "my-split"
    (is (= '(() ()) (my-split 3 '())))
    (is (= '((\a \b) ()) (my-split 3 '(\a \b))))
    (is (= '((\a \b \c) ()) (my-split 3 '(\a \b \c))))
    (is (= '((\a \b \c) (\d)) (my-split 3 '(\a \b \c \d))))
    (is (= '(() (\a \b \c)) (my-split 0 '(\a \b \c))))))

(deftest p18
  "Extract a slice from a list (inclusive, 1-based indicies)."
  (testing "slice"
    (is (= '() (slice 2 5 '())))
    (is (= '() (slice 4 5 '(\a \b \c))))
    (is (= '(\b \c) (slice 2 5 '(\a \b \c))))
    (is (=
      '(\c \d \e \f \g) 
      (slice 3 7 '(\a \b \c \d \e \f \g \h \i \k))))))

(deftest p19
  "Rotate a list n places to the left."
  (testing "rotate"
    (is (= '() (rotate 3 '())))
    (is (= '(\a) (rotate 3 '(\a))))
    (is (= '(\a \b) (rotate 3 '(\a \b))))
    (is (= '(\a \b \c) (rotate 3 '(\a \b \c))))
    (is (= '(\d \a \b \c) (rotate 3 '(\a \b \c \d))))
    (is (= 
      '(\d \e \f \g \h \a \b \c)
      (rotate 3 '(\a \b \c \d \e \f \g \h))))
    (is (=
      '(\g \h \a \b \c \d \e \f)
      (rotate -2 '(\a \b \c \d \e \f \g \h))))))

(deftest p20
  "Remove the kth element from a list (1-based index)."
  (testing "remove-at"
    (is (= '() (remove-at 3 '())))
    (is (= '(\a \c \d) (remove-at 2 '(\a \b \c \d))))
    (is (= '(\b \c \d) (remove-at 1 '(\a \b \c \d))))
    (is (= '(\a \b \c \d) (remove-at 0 '(\a \b \c \d))))
    (is (= '(\a \b \c \d) (remove-at 6 '(\a \b \c \d))))))

(deftest p21
  "Insert an element at the specified (1-based) index."
  (testing "insert-at"
    (is (= '(\n) (insert-at 5 \n '())))
    (is (= '(\a \n) (insert-at 5 \n '(\a))))
    (is (= '(\a \b \n \c \d) (insert-at 3 \n '(\a \b \c \d))))
    (is (= '(\n \a \b \c \d) (insert-at 1 \n '(\a \b \c \d))))))

(deftest p22
  "Generate all the integers between 2 values (inclusive)."
  (testing "my-range"
    (is (= '(4 5 6 7 8 9) (my-range 4 9)))
    (is (= '(9 8 7 6 5 4) (my-range 9 4)))))

(deftest p26
  "Generate the list of combinations of n items from a list"
  (testing "combinations"
    (is (= '() (combinations 2 '())))
    (is (= '((\a) (\b) (\c) (\d)) (combinations 1 '(\a \b \c \d))))
    (is (=
      '((\a \b) (\a \c) (\a \d) (\b \c) (\b \d) (\c \d))
      (combinations 2 '(\a \b \c \d))))
    (is (=
      '((\a \b \c) (\a \b \d) (\a \c \d) (\b \c \d))
      (combinations 3 '(\a \b \c \d))))))

(deftest p28a
  "Sort a list according to the length of its sublists."
  (testing "lsort"
    (is (= '() (lsort '())))
    (is (=
      '((\o) (\d \e) (\d \e) (\m \n) (\a \b \c) (\f \g \h) (\i \j \k \l))
      (lsort '((\a \b \c) (\d \e) (\f \g \h) (\d \e) (\i \j \k \l)
                    (\m \n) (\o)))))))

(deftest p28b
  "Sort a list according to the frequency of the lenghts of its sublists."
  (testing "flsort"
    (is (= '() (flsort '())))
    (is (=
      '((\i \j \k \l) (\o) (\a \b \c) (\f \g \h) (\d \e) (\d \e) (\m \n))
      (flsort '((\a \b \c) (\d \e) (\f \g \h) (\d \e) (\i \j \k \l)
                    (\m \n) (\o)))))))
    
;Arithmetic

(deftest p31
  "Test if a number is prime."
  (testing "prime?"
    (is (prime? 1))
    (is (prime? 2))
    (is (prime? 3))
    (is (prime? 5))
    (is (prime? 7))
    (is (prime? 11))
    (is (prime? 13))
    (is (prime? 17))
    (is (prime? 19))
    (is (prime? 23))
    (is (prime? 31))
    (is (not (prime? 4)))
    (is (not (prime? 6)))
    (is (not (prime? 8)))
    (is (not (prime? 9)))
    (is (not (prime? 10)))
    (is (not (prime? 12)))
    (is (not (prime? 14)))
    (is (not (prime? 15)))
    (is (not (prime? 16)))
    (is (not (prime? 18)))))

(deftest p32
  "Find greatest common divisor"
  (testing "gcd"
    (is (= 9 (gcd 36 63)))))

(deftest p33
  "Test if 2 numbers are coprime"
  (testing "coprime"
    (is (not (coprime? 36 63)))
    (is (coprime? 35 64))))

(deftest p34
  "Calculate Euler's totient function (phi)."
  (testing "phi"
    (is (= 1 (phi 1)))
    (is (= 6 (phi 9)))
    (is (= 4 (phi 10)))
    (is (= 16 (phi 34)))
    (is (= 60 (phi 77)))))

(deftest p35
  "Generate a list of a number's prime factors in ascening order."
  (testing "prime-factors"
    (is (= '(31) (prime-factors 31)))
    (is (= '(3 3 5 7) (prime-factors 315)))
    (is (= '(2 2 2 3 3 5) (prime-factors 360)))
    (is (= '(2 2 2 2 2 3 3 3) (prime-factors 864)))))

(deftest p36
  "Generate a number's prime factors as a list of factor, multiplicity pairs"
  (testing "prime-factors-mult"
    (is (= '((31 1)) (prime-factors-mult 31)))
    (is (= '((3 2) (5 1) (7 1)) (prime-factors-mult 315)))
    (is (= '((2 3) (3 2) (5 1)) (prime-factors-mult 360)))
    (is (= '((2 5) (3 3)) (prime-factors-mult 864)))))

(deftest p37
  "Calculate Euler's totient function (phi) using efficent algorithm."
  (testing "phi'"
    (is (= 1 (phi' 1)))
    (is (= 6 (phi' 9)))
    (is (= 4 (phi' 10)))
    (is (= 16 (phi' 34)))
    (is (= 60 (phi' 77)))))

(deftest p39
  "Generate a list of prime numbers within a specified range."
  (testing "primes"
    (is (= '() (primes 24 29)))
    (is (=
      '(199 211 223 227 229 233 239 241 251 257 263 269 271 277 281 283 293 307
        311 313 317 331 337 347 349 353 359 367 373 379 383 389 397 401 409 419
        421 431 433 439 443)
      (primes 198 445)))))

(deftest p40
  "Find a goldbach composition for a given number."
  (testing "goldbach"
    (is (= '(5 23) (goldbach 28)))))

(deftest p41
  "Generate Goldbach compositions for all the even integers in a specified range."
  (testing "goldbach-list"
    (is (=
      '((10 (3 7)) (12 (5 7)) (14 (3 11)) (16 (3 13)) (18 (5 13)) (20 (3 17)))
      (goldbach-list 9 20)))))

;Logic and Codes

(deftest p46
  "Truth tables for binary logic functions."
  (testing "truth-table"
    (is (=
      '(((true true) true)
        ((true false) true)
        ((false true) false)
        ((false false) false))
      (truth-table (fn [a b] (and a (or a b))))))))

(deftest p48
  "Truth tables for boolean functions of arbitry arity."
  (testing "truth-table"
    (is (=
      '(((true true true) true)
        ((true true false) true)
        ((true false true) true)
        ((true false false) true)
        ((false true true) true)
        ((false true false) true)
        ((false false true) true)
        ((false false false) true))
      (truth-table 
        3
        #(= (and %1 (or %2 %3)) (or (and %1 %2) (and %1 %3))))))))
      
(deftest p49
  "Generate Gray codes (strings)"
  (testing "gray-code"
    (is (= '("0" "1")) (gray-code 1)))
    (is (= '("00" "01" "11" "10") (gray-code 2)))
    (is (=
      '("000" "001" "011" "010" "110" "111" "101" "100")
      (gray-code 3))))

(deftest p49a
  "Generate Gray codes (binary)"
  (testing "binary-gray-code"
    (is (= '(2r0 2r1)) (binary-gray-code 1)))
    (is (= '(2r00 2r01 2r11 2r10) (binary-gray-code 2)))
    (is (=
      '(2r000 2r001 2r011 2r010 2r110 2r111 2r101 2r100)
      (binary-gray-code 3))))

(deftest p50
  "Generate the Huffman code table for a give list of symbol frequencies."
  (testing "huffman-code-table"
    (is (= 
      {\a '(0), \b '(1 0 1), \c '(1 0 0), \d '(1 1 1), \e '(1 1 0 1),
       \f '(1 1 0 0)}
      (huffman-code-table
        '((\a 45) (\b 13) (\c 12) (\d 16) (\e 9) (\f 5)))))))

;Binary Trees

(deftest p54a
  "Test if an argument is a binary tree node of the form (v l r)."
  (testing "tree?"
    (is (not (tree? '())))
    (is (not (tree? '(\a))))
    (is (not (tree? '(nil))))
    (is (not (tree? '(\a nil))))
    (is (not (tree? '(nil nil))))
    (is (not (tree? '(\a \b))))
    (is (not (tree? '(\a \b nil))))
    (is (not (tree? '(\a nil \b))))
    (is (tree? '(\a nil nil)))
    (is (tree? '(\a (\b nil nil) nil)))
    (is (tree? '(\a (\b nil nil) (\c nil nil))))
    (is (not (tree? '(\a (\b nil nil) (\c nil)))))
    (is (tree? '(\a nil (\b nil nil))))))

(comment
  "In practice I think it might be better to use a 
  {:value v, :left l, :right r} form."
(deftest p54b
  "Test if an argument is a binary tree node of the form 
  {:left l, :right r}."
  (testing "map-tree?"
    (is (not (map-tree? {})))
    (is (not (map-tree? {:left \a})))
    (is (not (map-tree? {:left nil})))
    (is (not (map-tree? {:right \a})))
    (is (not (map-tree? {:right nil})))
    (is (not (map-tree? {:left \a, :right nil})))
    (is (map-tree? {:left nil, :right nil}))
    (is (not (map-tree? {:value \a, :left \b})))
    (is (not (map-tree? {:value \a, :left \b, :right nil})))
    (is (not (map-tree? {:value \a, :left nil, :right \b})))
    (is (map-tree? {:value \a, :left nil, :right nil}))
    (is (map-tree? {:value \a, 
                :left {:value \b, :left nil, :right nil},
                :right nil}))
    (is (map-tree? {:value \a,
                :left {:value \b, :left nil, :right nil},
                :right {:value \c, :left nil, :right nil}}))
    (is (not (map-tree? {:value \a, 
                     :left {:value \b, :left nil, :right nil}
                     :right {:value \c, :left nil}})))
    (is (map-tree? {:value \a,
                :left nil, 
                :right {:value \b, :left nil, :right nil}}))))
)
