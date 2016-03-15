(ns fourclojure.core)

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
