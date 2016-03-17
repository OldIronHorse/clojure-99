(ns fourclojure.core-test
  (:require [clojure.test :refer :all]
            [fourclojure.core :refer :all]))

(deftest q28
  "Flatten a sequence"
  (testing "nested lists and vectors"
    (is (= 
      '(1 2 3 4 5 6)
      (my-flatten '((1 2) 3 [4 [5 6]])))))
  (testing "strings"
    (is (=
    '("a" "b" "c")
    (my-flatten ["a" ["b"] "c"]))))
  (testing "symbol"
    (is (= '(:a) (my-flatten '((((:a)))))))))


(deftest q43
  "Reverse interleave"
  (testing "vector"
    (is (=
      '((1 3 5) (2 4 6))
      (reverse-interleave [1 2 3 4 5 6] 2))))
  (testing "range"
    (is (=
      '((0 3 6) (1 4 7) (2 5 8))
      (reverse-interleave (range 9) 3))))
  (testing "range again"
    (is (=
      '((0 5) (1 6) (2 7) (3 8) (4 9))
      (reverse-interleave (range 10) 5)))))

(deftest q44
  "rotate a list"
  (testing "rotate left"
    (is (= '(3 4 5 1 2) (rotate 2 '(1 2 3 4 5)))))
  (testing "rotate right"
    (is (= '(4 5 1 2 3) (rotate -2 '(1 2 3 4 5)))))
  (testing "rotate more that length"
    (is (= '(2 3 4 5 1) (rotate 6 '(1 2 3 4 5)))))
  (testing "symbols"
    (is (= '(:b :c :a) (rotate 1 '(:a :b :c)))))
  (testing "over rotate right"
    (is (= '(:c :a :b) (rotate -4 '(:a :b :c))))))

(deftest q46
  "flip arguments"
  (testing "2 arguments"
    (is (= 3 ((flip nth) 2 [1 2 3 4 5])))
    (is (= true ((flip >) 7 8)))
    (is (= 4 ((flip quot) 2 8)))
    (is (= [1 2 3] ((flip take) [1 2 3 4 5] 3)))))

(deftest q49
  "split sequence at index"
  (testing "vector"
    (is (= [[1 2 3] [4 5 6]] (my-split-at 3 [1 2 3 4 5 6])))
    (is (= [[:a] [:b :c :d]] (my-split-at 1 [:a :b :c :d]))))
  (testing "vector of vectors"
    (is (=
      [[[1 2] [3 4]] [[5 6]]]
      (my-split-at 2 [[1 2] [3 4] [5 6]])))))

(deftest p50
  "split-by type"
  (testing "integer and symbol"
    (is (=
      #{[1 2 3] [:a :b :c]}
      (split-by-type [1 :a 2 :b 3 :c]))))
  (testing "symbol and string"
    (is (=
      #{[:a :b] ["foo" "bar"]}
      (split-by-type [:a "foo" "bar" :b]))))
  (testing "list, integer and symbol"
    (is (=
      #{[[1 2] [3 4]] [:a :b] [5 6]}
      (split-by-type [[1 2] :a [3 4] 5 6 :b])))))

(deftest p53
  "longest increasing sub sequence"
  (testing "longest-sebseq"
    (is (= [0 1 2 3] (longest-subseq [1 0 1 2 3 0 4 5 6])))
    (is (= [5 6] (longest-subseq [5 6 1 3 2 7])))
    (is (= [3 4 5] (longest-subseq [2 3 3 4 5])))
    (is (= [] (longest-subseq [7 6 5 4])))))

(deftest p54
  "partition a sequence"
  (testing "empty sequence"
    (is (= '() (my-partition 3 '()))))
  (testing "sequence too short"
    (is (= '() (my-partition 3 '(1 2)))))
  (testing "exact fit"
    (is (= '((0 1 2) (3 4 5) (6 7 8)) (my-partition 3 (range 9))))
    (is (= '((0 1) (2 3) (4 5) (6 7)) (my-partition 2 (range 8)))))
  (testing "trailing elements"
    (is (= '((0 1 2) (3 4 5)) (my-partition 3 (range 8))))))

(deftest p55
  "Count occurences"
  (testing "empty sequence"
    (is (= {} (occurences []))))
  (testing "numbers"
    (is (= {1 4, 2 2, 3 1} (occurences [1 1 2 3 2 1 1]))))
  (testing "keys"
    (is (= {:a 2, :b 3} (occurences [:b :a :b :a :b]))))
  (testing "subvectors"
    (is (= {[1 2] 1, [1 3] 2} (occurences '([1 2] [1 3] [1 3]))))))

(deftest p56
  "find distinct items (maintain ordering)"
  (testing "empty sequence"
    (is (= '() (my-distinct []))))
  (testing "numbers"
    (is (= (range 50) (my-distinct (range 50))))
    (is (= [1 2 3 4] (my-distinct [1 2 1 3 1 2 4]))))
  (testing "keys"
    (is (= [:a :b :c] (my-distinct [:a :a :b :b :c :c]))))
  (testing "sub vectors"
    (is (= '([2 4] [1 2] [1 3]) (my-distinct '([2 4] [1 2] [1 3] [1 3]))))))

(deftest p58
  "function composition"
  (is (= [3 2 1] ((my-comp rest reverse) [1 2 3 4])))
  (is (= 5 ((my-comp (partial + 3) second) [1 2 3 4])))
  (is (= true ((my-comp zero? #(mod % 8) +) 3 5 7 9)))
  (is (=
    "HELLO"
    ((my-comp #(.toUpperCase %) #(apply str %) take) 5 "hello world"))))

(deftest p59-juxtaposition
  "function juxtaposition"
  (testing "my-juxt"
    (is (= [21 6 1] ((my-juxt + max min) 2 3 5 1 6 4)))
    (is (= ["HELLO" 5] ((my-juxt #(.toUpperCase %) count) "hello")))
    (is (= [2 6 4] ((my-juxt :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10})))))

(deftest p60-sequence-reductions
  (testing "my-reductions"
    (is (= [0 1 3 6 10] (take 5 (my-reductions + (range)))))
    (is (= [[1] [1 2] [1 2 3] [1 2 3 4]] (my-reductions conj [1] [2 3 4])))
    (is (= 120 (reduce * 2 [3 4 5]) (last (my-reductions * 2 [3 4 5]))))))

(deftest p62-reimplement-iterate
  (is (= (take 5 (my-iterate #(* 2 %) 1)) [1 2 4 8 16]))
  (is (= (take 5 (my-iterate #(* 2 %) 1)) [1 2 4 8 16]))
  (is (= (take 100 (my-iterate inc 0)) (take 100 (range))))
  (is (= (take 9 (my-iterate #(inc (mod % 3)) 1)) (take 9 (cycle [1 2 3])))))

(deftest p63-group-by
  (is (= (my-group-by #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]}))
  (is (= (my-group-by #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]}))
  (is (= (my-group-by #(apply / %) [[1 2] [2 4] [4 6] [3 6]]))
    {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})
  (is (= (my-group-by count [[1] [1 2] [3] [1 2 3] [2 3]])
    {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]}))
  (is (= (my-group-by count [[1] [1 2] [3] [1 2 3] [2 3]])
    {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]})))
