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

(deftest p65-identify-collection
  (is (= :map (coll-type {:a 1, :b 2})))
  (is (= :list (coll-type (range (rand-int 20)))))
  (is (= :vector (coll-type [1 2 3 4 5 6])))
  (is (= :set (coll-type #{10 (rand-int 5)})))
  (is (= [:map :set :vector :list] (map coll-type [{} #{} [] ()]))))

(deftest p67-n-prime-numbers
  (is (= [2 3] (primes 2)))
  (is (= [2 3 5 7 11] (primes 5)))
  (is (= 541 (last (primes 100)))))

(deftest p69-merge-with
  (is (= {:a 4, :b 6, :c 20} 
    (my-merge-with * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})))
  (is (= {1 7, 2 10, 3 15} (my-merge-with - {1 10, 2 20} {1 3, 2 10, 3 15})))
  (is (=
    {:a [3 4 5], :b [6 7], :c [8 9]}
    (my-merge-with concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})))
  (is (=
    {:a [3 4 5], :b [6 7], :c [8 9]}
    (my-merge-with concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]}))))
    
(deftest p70-word-sorting
  (is (= ["a" "day" "Have" "nice"] (sort-words "Have a nice day.")))
  (is (=
    ["a" "Clojure" "fun" "is" "language"]
    (sort-words "Clojure is a fun language!")))
  (is (=
    ["fall" "follies" "foolish" "Fools" "for"]
    (sort-words "Fools fall for foolish follies."))))

(deftest p73-tic-tac-toe
  (is (= nil (tic-tac-toe-winner [[:e :e :e]
                                  [:e :e :e]
                                  [:e :e :e]])))

  (is (= :x (tic-tac-toe-winner [[:x :e :o]
                                 [:x :e :e]
                                 [:x :e :o]])))

  (is (= :o (tic-tac-toe-winner [[:e :x :e]
                                 [:o :o :o]
                                 [:x :e :x]])))

  (is (= nil (tic-tac-toe-winner [[:x :e :o]
                                  [:x :x :e]
                                  [:o :x :o]])))

  (is (= :x (tic-tac-toe-winner [[:x :e :e]
                                 [:o :x :e]
                                 [:o :e :x]])))

  (is (= :o (tic-tac-toe-winner [[:x :e :o]
                                 [:x :o :e]
                                 [:o :e :x]])))

  (is (= nil (tic-tac-toe-winner [[:x :o :x]
                                  [:x :o :x]
                                  [:o :x :o]]))))

(deftest p75-euler's-totient
  (is (= 1 (phi 1)))
  (is (= (count '(1 3 7 9)) 4 (phi 10)))
  (is (= 16 (phi 40)))
  (is (= 60 (phi 99))))

(deftest p75-euler's-totient2
  (is (= 1 (phi2 1)))
  (is (= (count '(1 3 7 9)) 4 (phi2 10)))
  (is (= 16 (phi2 40)))
  (is (= 60 (phi2 99))))

(deftest p77-anagram-finder
  (is (=
    #{#{"meat" "team" "mate"}}
    (anagrams ["meat" "mat" "team" "mate" "eat"])))
  (is (= 
    #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}}
    (anagrams ["veer" "lake" "item" "kale" "mite" "ever"]))))

(deftest p79-reimplement-trampoline
  (is (=
    82
    (letfn
      [(triple [x] #(sub-two (* 3 x)))
       (sub-two [x] #(stop?(- x 2)))
       (stop? [x] (if (> x 50) x #(triple x)))]
      (my-trampoline triple 2))))
  (is (=
    [true false true false true false]
    (letfn
      [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
       (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
      (map (partial my-trampoline my-even?) (range 6))))))

(deftest p80-mininal-triangle-path
  (is (=
    7
    (minimal-triangle-path '([1]
                            [2 4]
                           [5 1 4]
                          [2 3 4 5])))) ; 1->2->1->3
  (is (=
    20
    (minimal-triangle-path '([3]
                            [2 4]
                           [1 9 3]
                          [9 9 2 4]
                         [4 6 6 7 8]
                        [5 7 3 5 1 4]))))) ; 3->4->3->2->7->1
