(ns clojure-99.core
  (:require [clojure.math.numeric-tower :as math]
            [clojure.math.combinatorics :as combo]))

(defn my-last
  "Find the last element of a list."
  [l]
  (if (seq (rest l))
    (recur (rest l))
    (first l)))

(defn r-last
  "Find the last element of a list."
  [l]
  (first (reverse l)))

(defn my-but-last
  "Find the last 2 elements of a list."
  [l]
  (take-last 2 l))

(defn element-at
  "Find the kth element of a list."
  [k l]
  (first (drop (dec k) l)))

(defn my-count
  "Find the number of elements in a list"
  [l]
  (loop
    [c 0
     l' l]
    (if
      (nil? (first l'))
      c
      (recur (inc c) (rest l')))))

(defn r-reverse
  "Reverse a list (recursive implementation)."
  [l]
  (loop
    [l' l
     rl '()]
    (if
      (empty? l')
      rl
      (recur (rest l') (cons (first l') rl)))))

(defn f-reverse
  "Reverse a list (functional implementation)."
  [l]
  (reduce (fn [acc x] (cons x acc)) '() l))

(defn palindrome?
  "Test if a list is a palindrome."
  [l]
  (= l (reverse l)))

(defn r-palindrome?
  "Test if a list is a palindrome (recursive implementation)."
  [l]
  (if
    (empty? l)
    true
    (if
      (= (first l) (last l))
      (recur (drop-last (rest l)))
      false)))

(defn f-palindrome?
  "Test if a list is a palindrome (functional implementation)."
  [l]
  (let
    [[front back] (split-at (/ (count l) 2) l)
     zl (map vector front (reverse back))]
    (empty?
      (drop-while
        (fn [[a b]] (= a b))
        zl))))

(defn do-r-flatten
  [l fl]
  (if
    (empty? l)
    fl
    (if
      (seq? (first l))
      (recur (rest l) (concat (do-r-flatten (first l) fl)))
      (recur (rest l) (cons (first l) fl)))))

(defn r-flatten
  "Flatten a nested list (recursive implementation)."
  [l]
  (reverse (do-r-flatten l '())))

(defn do-f-flatten
  [l]
  (reduce
    (fn
      [fl x]
      (if
        (seq? x)
        (concat (do-f-flatten x) fl)
        (cons x fl)))
    '()
    l))

(defn f-flatten
  "Flatten a nested list (functional implementation)."
  [l]
  (reverse (do-f-flatten l)))

(defn r-dedupe
  "Remove consecutive duplicates from a list (recursive implementation)."
  [l]
  (loop
    [l' l
     acc '()]
    (if
      (empty? l')
      (reverse acc)
      (if
        (= (first acc) (first l'))
        (recur (rest l') acc)
        (recur (rest l') (cons (first l') acc))))))

(defn f-dedupe
  "Remove consecutive duplicates from a list (functional impementation)."
  [l]
  (reverse
    (reduce
      (fn [acc x] (if (= x (first acc)) acc (cons x acc)))
      '()
      l)))

(defn r-pack
  "Pack consecutive duplicates into sublists (recursive implementation)."
  [l]
  (loop
    [l' l
     acc '()]
    (if
      (empty? l')
      (reverse acc)
      (if
        (= (first l') (ffirst acc))
        (recur (rest l') (cons (cons (first l') (first acc)) (rest acc))) 
        (recur (rest l') (cons (list (first l')) acc))))))

(defn f-pack
  "Pack consecutive duplicates into sublists (functional implementation)."
  [l]
  (reverse
    (reduce
      (fn [acc x]
        (if 
          (= (ffirst acc) x)
          (cons (cons x (first acc)) (rest acc))
          (cons (list x) acc)))
      '()
      l)))

(defn rl-encode
  "Run-length encode a list."
  [l]
  (map #(list (count %1) (first %1)) (f-pack l)))

(defn mrl-encode
  "Modified run-length encode a list/"
  [l]
  (map (fn [[n x]] (if (= 1 n) x (list n x))) (rl-encode l)))

(defn mrl-decode
  "Decode a modified run-length encoded list."
  [l]
  (flatten
    (map #(if (seq? %1) (repeat (first %1) (last %1)) %1) l)))

(defn dmrl-encode
  "Directly modified run-length encode a list."
  [l]
  (map
    (fn [[n x]] (if (= 1 n) x (list n x)))
    (reverse
      (reduce
        (fn [acc x]
          (if 
            (= x (last (first acc)))
            (cons (list (inc (ffirst acc)) x) (rest acc))
            (cons (list 1 x) acc)))
        '()
        l))))

(defn duplicate
  "Duplicate the elements in a list."
  [l]
  (reduce (fn [acc x] (concat acc (repeat 2 x))) '() l))

(defn my-replicate
  "Replicate the elements of list a given number of times."
  [n l]
  (reduce (fn [acc x] (concat acc (repeat n x))) '() l))

(defn r-drop-every
  "Drop every kth element from a list (1-based index)."
  [k l]
  (loop
    [i 1
     l' l
     acc '()]
    (if 
      (empty? l')
      (reverse acc)
      (if
        (zero? (rem i k))
        (recur (inc i) (rest l') acc)
        (recur (inc i) (rest l') (cons (first l') acc))))))

(defn f-drop-every
  "Drop every kth element from a list (1-based index)."
  [k l]
  (map
    last
    (filter
      #(not (zero? (rem (first %1) k)))
      (map list (rest (range)) l))))

(defn my-split
  "Split a list into 2 parts. The length of the first part is specified."
  [n l]
  (list (take n l) (drop n l)))

(defn slice
  "Extract a slice from a list (inclusive, 1-based indicies)."
  [start end l]
  (take (inc (- end start)) (drop (dec start)l)))

(defn rotate
  "Rotate a list n places to the left."
  [n l]
  (if
    (neg? n)
    (concat (take-last (math/abs n) l) (drop-last (math/abs n) l))
    (concat (drop n l) (take n l))))

(defn remove-at
  "Remove the kth element from a list."
  [k l]
  (concat (take (dec k) l) (drop k l)))

(defn insert-at
  "Insert an element at the kth index of a list."
  [k v l]
  (concat (take (dec k) l) (cons v (drop (dec k) l))))

(defn my-range
  "Enumerate the integers between start and end (inclusive)."
  [start end]
  (if
    (< start end)
    (range start (inc end))
    (range start (dec end) -1)))

(defn combinations
  "Enumerate the combinations of n elements from a list."
  [n l]
  (if 
    (= 1 n)
    (map list l)
    (let
      [tails (take-while
              #(>= (count %1) (dec n))
              (iterate rest (rest l)))
       cs (map
            (fn [t] (combinations (dec n) t))
            tails)]
      (mapcat
        (fn [h ts] (map #(cons h %1) ts))
        l cs))))

(defn lsort
  "Sort a list of lists by the length of the sublists."
  [l]
  (sort-by count l))

(defn flsort
  "Sort a list of lists by the frequency of the lengths of the sublists."
  [l]
  (loop
    [sorted-by-length (sort-by count l)
     grouped-by-length '()]
    (let
      [[next-group  rump] (split-with
                          #(= (count %1) (count (first sorted-by-length)))
                          sorted-by-length)]
      (if
        (empty? sorted-by-length)
        ;(concat (sort-by count grouped-by-length))
        (reduce concat (reverse (reduce #(cons %2 %1) '() (sort-by count grouped-by-length))))
        (recur rump (cons next-group grouped-by-length))))))

(defn prime?
  "Is this a prime number?"
  [n]
  (empty? (drop-while #(not (zero? (rem n %1))) (range 2 (inc (/ n 2))))))

(defn gcd
  "Find the greatest common divisor of 2 numbers (Euclid's Algorithm)."
  [a b]
  (if
    (zero? b)
    a
    (recur b (mod a b))))

(defn coprime?
  "Test if 2 numbers are coprime."
  [a b]
  (= 1 (gcd a b)))

(defn phi
  "Calculate Euler's totient function (phi) for n."
  [n]
  (if
    (= 1 n)
    1
    (count (filter #(coprime? n %1) (range 1 n)))))

(defn prime-factors
  "Generate the list of prime factors for n in ascending order."
  [n]
  (loop
    [n' n
     factors '()]
    (if
      (prime? n')
      (sort (cons n' factors))
      (let
        [primes (filter prime? (drop 2 (range)))
         factor (first (drop-while #(not (zero? (rem n' %1))) primes))]
        (recur (/ n' factor) (cons factor factors))))))

(defn prime-factors-mult
  "Generate a numbers prime factors as a list of factor, multiplicity pairs."
  [n]
  (map
    #(list (first %1) (count %1))
    (f-pack (prime-factors n))))

(defn phi'
  "Calculate Euler's totient function (phi) using a more efficient algorithm."
  [n]
  (if
    (= 1 n)
    1
    (reduce
      *
      n
      (map
        (fn[p] (- 1 (/ 1 p)))
        (distinct (prime-factors n))))))

(defn primes
  "Generate the list of prime numbers between i and j."
  [i j]
  (filter prime? (range i j)))

(defn goldbach
  "Generate a Goldbach composition for the specified number."
  [n]
  (let
    [a (first (drop-while #(not (prime? (- n %1))) (primes 2 (inc (/ n 2)))))]
    (list a (- n a))))

(defn goldbach-list
  "Generate a list of Goldbach compositions for all the even numbers in a given
  range."
  [a b]
  (map #(list %1 (goldbach %1)) (filter even? (range a (inc b)))))
  
(defn truth-table
  "Generate the truth table for a binary boolean function."
  [f]
  (map
    (fn [[a b]] (list (list a b) (f a b)))
    (combo/selections '(true false) 2)))
