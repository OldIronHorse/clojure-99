(ns fourclojure.core
  (:require [clojure.math.numeric-tower :as math]
            [clojure.math.combinatorics :as combo]))

(defn my-flatten
  [l]
  (remove sequential? (tree-seq sequential? seq l)))

(defn reverse-interleave
  [l n]
  (apply map list
        (loop
          [l' l
           acc (list l)]
          (if (empty? (drop n l'))
            (reverse acc)
            (recur (drop n l') (cons (drop n l') acc))))))

(defn rotate
  [n l]
  (let
    [n' (if (neg? n)
          (+ (count l) (rem n (count l)))
          (rem n (count l)))]
    (concat (drop n' l) (take n' l))))

(defn flip
  [f]
  #(f %2 %1))

(defn my-split-at
  [n l]
  (list (take n l) (drop n l)))

(defn split-by-type
  [l]
  (let
    [types (set (map type l))
     typed-list (map #(list % (type %)) l)]
    ;(into #{}
    (set
      (map
        (fn [t]
          (map
            (fn [[v' t'']] v')
            (filter (fn [[v t']] (= t t')) typed-list))) 
        types))))

(defn longest-subseq
  [[l & ls]]
  (reverse
    (first
      (reverse
        (sort-by 
          count
          (filter 
            #(> (count %) 1)
            (reduce
              (fn [[a & acc] x] (if (= (inc (first a)) x)
                            (cons (cons x  a) acc)
                            (cons (list x) (cons a acc))))
              [[l]]
              ls)))))))

(defn my-partition
  [n [l & ls]]
  (reverse
    (map
      reverse
      (filter
        #(= n (count %))
        (reduce
          (fn [[a & as] x] (if (= (count a) n)
            (cons [x] (cons a as))
            (cons (cons x a) as)))
          [[l]]
          ls)))))

(defn occurences
  [l]
  (reduce
    (fn [a x]
      (if (contains? a x)
        (update a x inc)
        (assoc a x 1)))
    {}
    l))

(defn my-distinct
  [l]
  (loop
    [l' l
     acc '()]
    (if (empty? l')
      (reverse acc)
      (recur
        (remove
          #(= % (first l'))
          (rest l'))
        (cons (first l') acc)))))

(defn my-comp
  [& fs]
  (fn
    [& args]
    (reduce 
      (fn [result f] (f result))
      (apply (last fs) args)
      (rest (reverse fs)))))

(defn my-juxt
  [& fns]
  (fn
    [& args]
    (map
      #(apply % args)
      fns)))

(defn my-reductions
  ([f [l & ls]]
    (my-reductions f l ls))
  ([f a [l & ls]]
    (if (nil? ls)
      (cons a (list (f a l)))
      (cons a (lazy-seq (my-reductions f (f a l) ls))))))

(defn my-iterate
  [f x]
  (cons x (lazy-seq (my-iterate f (f x)))))

(defn my-group-by
  [f l]
  (reduce
    (fn [acc [k v]]
      (assoc acc k (cons v (acc k))))
      ;(update acc k conj v)) ;Clojure 1.7+
    {}
    (reverse (map #(list (f %) %) l))))

(defn coll-type
  [c]
  (let
    [f [:some-key :some-value]
     c' (conj c f)]
    (cond
      (= c' (conj c' f)) (if (= f (get c' f))
        :set
        :map)
      (= (conj (conj c 1) 2) (concat c [1 2])) :vector
      (= (conj (conj c 1) 2) (concat [2 1] c)) :list)))

(defn primes
  [n]
  (take
    n
    (filter
      (fn [x]
        (empty?
          (drop-while 
            #(not (zero? (rem x %1)))
            (range 2 (inc (/ x 2))))))
      (drop 2 (range)))))

(defn my-merge-with
  [f & ms]
  (reduce
    (fn [acc m] 
      (reduce
        (fn [acc' [k v]]
          (if (contains? acc' k)
            (update acc' k f v)
            ;< Clojure 1.7(assoc acc' k (f (acc' k) v))
            (assoc acc' k v)))
        acc
        m))
    {}
    ms))

;(require '[clojure.string :as str])

(defn sort-words
  [s]
  (sort-by clojure.string/lower-case (clojure.string/split s #"[^a-zA-Z]")))

(defn tic-tac-toe-winner
  [rows]
  (let
    [lines (cons
            (list ((rows 0) 2) ((rows 1) 1) ((rows 2) 0))
            (cons
              (list ((rows 0) 0) ((rows 1) 1) ((rows 2) 2))
              (concat
                rows
                (apply mapv vector rows))))]
    (ffirst (filter #(not= :e (first %)) (filter #(apply = %) lines)))))

(defn gcd
  [a b]
  (if (zero? b)
    a
    (recur b (mod a b))))

(defn coprime?
  [a b]
  (= 1 (gcd a b)))

(defn phi
  [n]
  (if (= n 1)
    1
    (count (filter #(coprime? %  n) (range 1 n)))))

(defn phi2
  [n]
  (if (= n 1)
    1
    (count
      (filter
        #(=
          1
          ((fn [a b]
            (if (zero? b)
             a
             (recur b (mod a b)))) % n))
      (range 1 n)))))

(defn anagrams
  [words]
  (set
    (filter
      #(> (count %) 1)
      (vals
        (reduce
          (fn [acc [w ls]]
            (if (contains? acc ls)
              (update acc ls #(conj % w))
              (assoc acc ls (set [w]))))
          {}
          (map #(list % (sort %)) words))))))

(defn my-trampoline
  [f & args]
  (loop
    [f' (apply f args)]
    (if (fn? f')
      (recur (f'))
      f')))

;TODO non-recursive (or at least tail-recursive) implementation.
(defn triangle-minimal-path
  [triangle]
  (letfn
    [(all-routes [[r n] depth] 
      (if (= 1 depth)
        (list (list (list r n))) 
        (map
          #(conj % (list r n)) 
          (concat
            (all-routes [(inc r) n] (dec depth))
            (all-routes [(inc r) (inc n)] (dec depth))))))
     (cost [[r n]] (((vec triangle) r) n))
     (route-cost [route] (reduce + (map cost route)))]
    (let
      [routes (all-routes [0 0] (count triangle))
       route-costs (map route-cost routes)]
      (first (sort route-costs)))))
  
(defn perfect?
  [n]
  ( =
    n
    (reduce + (filter #(zero? (rem n %)) (range 1 (inc (/ n 2)))))))

(defn my-intersection
  [s1 s2]
  (reduce (fn [i e] (if (contains? s2 e) (conj i e) i)) #{} s1))

(defn differ-by-one?
  [w1 w2]
  (if (= (count w1) (count w2))
    (= 1 (count (filter false? (map = w1 w2))))
    (let
      [[w-short w-long] (sort-by count (list w1 w2))
       w-long-combos 
        (for
          [n (range 0 (count w-long))
           :let [w (concat (take n w-long) (drop (inc n) w-long))]]
          w)]
      (not (zero? (count (filter #(= (seq w-short) %) w-long-combos)))))))

(defn is-chain?
  [words]
  (reduce
    (fn [acc [w1 w2]] (and acc (differ-by-one? w1 w2)))
    true
    (map list words (drop 1 words))))

(defn word-chain?
  [words]
  (not (zero? (count (filter #(is-chain? %) (combo/permutations words))))))
