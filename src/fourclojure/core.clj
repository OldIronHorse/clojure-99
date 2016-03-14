(ns fourclojure.core)

(defn my-flatten
  [l]
  (filter (complement sequential?) (tree-seq sequential? seq l)))

(defn reverse-interleave
  [l n]
  (apply map list
        (loop
          [l' l
           acc (list l)]
          (if (empty? (drop n l'))
            (reverse acc)
            (recur (drop n l') (cons (drop n l') acc))))))
