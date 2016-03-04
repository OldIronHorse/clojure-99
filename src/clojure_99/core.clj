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
