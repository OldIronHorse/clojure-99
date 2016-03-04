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
     acc '()
     curr '()]
    (if
      (empty? l')
      (reverse acc)
      (if
        (= (first l') (first curr))
        (recur (rest l') acc (cons (first l') curr))
        (recur (rest l') (cons curr acc) (list (first l')))))))
