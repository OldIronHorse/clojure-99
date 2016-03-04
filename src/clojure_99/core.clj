(ns clojure-99.core)

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
  (first (drop (- k 1) l)))

(defn my-count
  "Find the number of elements in a list"
  [l]
  (loop
    [c 0
     l' l]
    (if
      (nil? (first l'))
      c
      (recur (+ c 1) (rest l')))))

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

(defn do-flatten
  [l fl]
  (if
    (empty? l)
    fl
    (if
      (seq? (first l))
      (recur (rest l) (concat (do-flatten (first l) fl)))
      (recur (rest l) (cons (first l) fl)))))

(defn r-flatten
  [l]
  (reverse (do-flatten l '())))

(defn f-flatten
  [l]
  ;(reduce (fn [fl x]
  l)
