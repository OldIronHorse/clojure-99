(ns fourclojure.core)

(defn my-flatten
  [l]
  (filter (complement sequential?) (tree-seq sequential? seq l)))
