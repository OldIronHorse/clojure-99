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
