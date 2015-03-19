;; gorilla-repl.fileformat = 1

;; **
;;; # Understanding Quoting and Macros
;; **

;; @@
(ns fuscia-thorns
  (:require [clojure.pprint :as pprint :refer [pprint]]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(def fruit "Bananas")
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;fuscia-thorns/fruit</span>","value":"#'fuscia-thorns/fruit"}
;; <=

;; **
;;; ## Quote
;; **

;; @@
(def quoted
  '(1 :a \b "Apples" fruit inc pprint/pprint #"[0-9]+" [1 2] (java.util.Date.) {:name "Billy"} #{:a :b}))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;fuscia-thorns/quoted</span>","value":"#'fuscia-thorns/quoted"}
;; <=

;; @@
(pprint (map type quoted))
;; @@
;; ->
;;; (java.lang.Long
;;;  clojure.lang.Keyword
;;;  java.lang.Character
;;;  java.lang.String
;;;  clojure.lang.Symbol
;;;  clojure.lang.Symbol
;;;  clojure.lang.Symbol
;;;  java.util.regex.Pattern
;;;  clojure.lang.PersistentVector
;;;  clojure.lang.PersistentList
;;;  clojure.lang.PersistentArrayMap
;;;  clojure.lang.PersistentHashSet)
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
quoted
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-keyword'>:a</span>","value":":a"},{"type":"html","content":"<span class='clj-char'>\\b</span>","value":"\\b"},{"type":"html","content":"<span class='clj-string'>&quot;Apples&quot;</span>","value":"\"Apples\""},{"type":"html","content":"<span class='clj-symbol'>fruit</span>","value":"fruit"},{"type":"html","content":"<span class='clj-symbol'>inc</span>","value":"inc"},{"type":"html","content":"<span class='clj-symbol'>pprint/pprint</span>","value":"pprint/pprint"},{"type":"html","content":"<span class='clj-unkown'>#&quot;[0-9]+&quot;</span>","value":"#\"[0-9]+\""},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"}],"value":"[1 2]"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>java.util.Date.</span>","value":"java.util.Date."}],"value":"(java.util.Date.)"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:name</span>","value":":name"},{"type":"html","content":"<span class='clj-string'>&quot;Billy&quot;</span>","value":"\"Billy\""}],"value":"[:name \"Billy\"]"}],"value":"{:name \"Billy\"}"},{"type":"list-like","open":"<span class='clj-set'>#{</span>","close":"<span class='clj-set'>}</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:b</span>","value":":b"},{"type":"html","content":"<span class='clj-keyword'>:a</span>","value":":a"}],"value":"#{:b :a}"}],"value":"(1 :a \\b \"Apples\" fruit inc pprint/pprint #\"[0-9]+\" [1 2] (java.util.Date.) {:name \"Billy\"} #{:b :a})"}
;; <=

;; @@
(type quoted)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-class'>clojure.lang.PersistentList</span>","value":"clojure.lang.PersistentList"}
;; <=

;; **
;;; ### Notes
;;; * Quote is recursive.
;;; * Anything can be quoted.
;; **

;; **
;;; ## Syntax Quote
;; **

;; @@
(def squoted
  `(1 :a \b "Apples" fruit inc pprint/pprint #"[0-9]+" [1 2] (java.util.Date.) {:name "Billy"} #{:a :b}))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;fuscia-thorns/squoted</span>","value":"#'fuscia-thorns/squoted"}
;; <=

;; @@
squoted
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-keyword'>:a</span>","value":":a"},{"type":"html","content":"<span class='clj-char'>\\b</span>","value":"\\b"},{"type":"html","content":"<span class='clj-string'>&quot;Apples&quot;</span>","value":"\"Apples\""},{"type":"html","content":"<span class='clj-symbol'>fuscia-thorns/fruit</span>","value":"fuscia-thorns/fruit"},{"type":"html","content":"<span class='clj-symbol'>clojure.core/inc</span>","value":"clojure.core/inc"},{"type":"html","content":"<span class='clj-symbol'>clojure.pprint/pprint</span>","value":"clojure.pprint/pprint"},{"type":"html","content":"<span class='clj-unkown'>#&quot;[0-9]+&quot;</span>","value":"#\"[0-9]+\""},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"}],"value":"[1 2]"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>java.util.Date.</span>","value":"java.util.Date."}],"value":"(java.util.Date.)"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:name</span>","value":":name"},{"type":"html","content":"<span class='clj-string'>&quot;Billy&quot;</span>","value":"\"Billy\""}],"value":"[:name \"Billy\"]"}],"value":"{:name \"Billy\"}"},{"type":"list-like","open":"<span class='clj-set'>#{</span>","close":"<span class='clj-set'>}</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:b</span>","value":":b"},{"type":"html","content":"<span class='clj-keyword'>:a</span>","value":":a"}],"value":"#{:b :a}"}],"value":"(1 :a \\b \"Apples\" fuscia-thorns/fruit clojure.core/inc clojure.pprint/pprint #\"[0-9]+\" [1 2] (java.util.Date.) {:name \"Billy\"} #{:b :a})"}
;; <=

;; @@
(type squoted)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-class'>clojure.lang.Cons</span>","value":"clojure.lang.Cons"}
;; <=

;; **
;;; ### Notes
;;; * Syntax quote is recursive.
;;; * Anything can be syntax quoted.
;;; * Symbols are prefixed with their fully qualified namespace.
;; **

;; **
;;; ### Unquote
;; **

;; @@
(def x 3)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;fuscia-thorns/x</span>","value":"#'fuscia-thorns/x"}
;; <=

;; @@
`(inc x)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/inc</span>","value":"clojure.core/inc"},{"type":"html","content":"<span class='clj-symbol'>fuscia-thorns/x</span>","value":"fuscia-thorns/x"}],"value":"(clojure.core/inc fuscia-thorns/x)"}
;; <=

;; @@
`(inc ~x)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/inc</span>","value":"clojure.core/inc"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"(clojure.core/inc 3)"}
;; <=

;; @@
~x
;; @@

;; **
;;; ### Unquote splicing
;; **

;; @@
(def fruits ["Apples" "Bananas" "Oranges"])
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;fuscia-thorns/fruits</span>","value":"#'fuscia-thorns/fruits"}
;; <=

;; @@
`(str fruits)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/str</span>","value":"clojure.core/str"},{"type":"html","content":"<span class='clj-symbol'>fuscia-thorns/fruits</span>","value":"fuscia-thorns/fruits"}],"value":"(clojure.core/str fuscia-thorns/fruits)"}
;; <=

;; @@
`(str ~fruits)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/str</span>","value":"clojure.core/str"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-string'>&quot;apples&quot;</span>","value":"\"apples\""},{"type":"html","content":"<span class='clj-string'>&quot;bananas&quot;</span>","value":"\"bananas\""},{"type":"html","content":"<span class='clj-string'>&quot;oranges&quot;</span>","value":"\"oranges\""}],"value":"[\"apples\" \"bananas\" \"oranges\"]"}],"value":"(clojure.core/str [\"apples\" \"bananas\" \"oranges\"])"}
;; <=

;; @@
`(str ~@fruits)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/str</span>","value":"clojure.core/str"},{"type":"html","content":"<span class='clj-string'>&quot;apples&quot;</span>","value":"\"apples\""},{"type":"html","content":"<span class='clj-string'>&quot;bananas&quot;</span>","value":"\"bananas\""},{"type":"html","content":"<span class='clj-string'>&quot;oranges&quot;</span>","value":"\"oranges\""}],"value":"(clojure.core/str \"apples\" \"bananas\" \"oranges\")"}
;; <=

;; @@
`[~@#{:a :b :c}]
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:c</span>","value":":c"},{"type":"html","content":"<span class='clj-keyword'>:b</span>","value":":b"},{"type":"html","content":"<span class='clj-keyword'>:a</span>","value":":a"}],"value":"[:c :b :a]"}
;; <=

;; @@
`[~@"hi"]
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-char'>\\h</span>","value":"\\h"},{"type":"html","content":"<span class='clj-char'>\\i</span>","value":"\\i"}],"value":"[\\h \\i]"}
;; <=

;; @@
`[~@(range 5)]
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>0</span>","value":"0"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"},{"type":"html","content":"<span class='clj-long'>4</span>","value":"4"}],"value":"[0 1 2 3 4]"}
;; <=

;; @@
~@[1 2]
;; @@

;; **
;;; ### Hygiene and gensyms
;; **

;; @@
`(let [n 3] n)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/let</span>","value":"clojure.core/let"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>fuscia-thorns/n</span>","value":"fuscia-thorns/n"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"[fuscia-thorns/n 3]"},{"type":"html","content":"<span class='clj-symbol'>fuscia-thorns/n</span>","value":"fuscia-thorns/n"}],"value":"(clojure.core/let [fuscia-thorns/n 3] fuscia-thorns/n)"}
;; <=

;; @@
(defmacro bad-hygiene []
  `(let [n 3] n))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;fuscia-thorns/bad-hygiene</span>","value":"#'fuscia-thorns/bad-hygiene"}
;; <=

;; @@
(bad-hygiene)
;; @@

;; @@
`(let [n# 3] n#)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/let</span>","value":"clojure.core/let"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>n__6206__auto__</span>","value":"n__6206__auto__"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"[n__6206__auto__ 3]"},{"type":"html","content":"<span class='clj-symbol'>n__6206__auto__</span>","value":"n__6206__auto__"}],"value":"(clojure.core/let [n__6206__auto__ 3] n__6206__auto__)"}
;; <=

;; @@
(defmacro good-hygiene [num]
  `(let [n# 3] (+ n# ~num)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;fuscia-thorns/good-hygiene</span>","value":"#'fuscia-thorns/good-hygiene"}
;; <=

;; @@
(let [n 2]
  (good-hygiene n))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>5</span>","value":"5"}
;; <=

;; @@
(gensym)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-symbol'>G__6226</span>","value":"G__6226"}
;; <=

;; @@
(let [sym (gensym)]
  `(let [~sym 3] ~sym))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/let</span>","value":"clojure.core/let"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>G__6232</span>","value":"G__6232"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"[G__6232 3]"},{"type":"html","content":"<span class='clj-symbol'>G__6232</span>","value":"G__6232"}],"value":"(clojure.core/let [G__6232 3] G__6232)"}
;; <=

;; @@
(list 'do `(let [n# 3] n#) `(let [n# 4] n#))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>do</span>","value":"do"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/let</span>","value":"clojure.core/let"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>n__6244__auto__</span>","value":"n__6244__auto__"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"[n__6244__auto__ 3]"},{"type":"html","content":"<span class='clj-symbol'>n__6244__auto__</span>","value":"n__6244__auto__"}],"value":"(clojure.core/let [n__6244__auto__ 3] n__6244__auto__)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/let</span>","value":"clojure.core/let"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>n__6245__auto__</span>","value":"n__6245__auto__"},{"type":"html","content":"<span class='clj-long'>4</span>","value":"4"}],"value":"[n__6245__auto__ 4]"},{"type":"html","content":"<span class='clj-symbol'>n__6245__auto__</span>","value":"n__6245__auto__"}],"value":"(clojure.core/let [n__6245__auto__ 4] n__6245__auto__)"}],"value":"(do (clojure.core/let [n__6244__auto__ 3] n__6244__auto__) (clojure.core/let [n__6245__auto__ 4] n__6245__auto__))"}
;; <=

;; @@
(let [sym (gensym)]
  (list 'do `(let [~sym 3] ~sym) `(let [~sym 4] ~sym)))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>do</span>","value":"do"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/let</span>","value":"clojure.core/let"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>G__6250</span>","value":"G__6250"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"[G__6250 3]"},{"type":"html","content":"<span class='clj-symbol'>G__6250</span>","value":"G__6250"}],"value":"(clojure.core/let [G__6250 3] G__6250)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/let</span>","value":"clojure.core/let"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>G__6250</span>","value":"G__6250"},{"type":"html","content":"<span class='clj-long'>4</span>","value":"4"}],"value":"[G__6250 4]"},{"type":"html","content":"<span class='clj-symbol'>G__6250</span>","value":"G__6250"}],"value":"(clojure.core/let [G__6250 4] G__6250)"}],"value":"(do (clojure.core/let [G__6250 3] G__6250) (clojure.core/let [G__6250 4] G__6250))"}
;; <=

;; **
;;; ## Macros
;;; * Macros are functions that run at macro-expansion time and are passed quoted forms.
;;; * Unlike functions, macros are not values, and cannot be passed around.
;; **

;; @@
(defn and-not [& forms]
  `(and ~@(map (fn [form] `(not ~form)) forms)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;fuscia-thorns/and-not</span>","value":"#'fuscia-thorns/and-not"}
;; <=

;; @@
(and-not 'false '(< 1 0))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/and</span>","value":"clojure.core/and"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/not</span>","value":"clojure.core/not"},{"type":"html","content":"<span class='clj-unkown'>false</span>","value":"false"}],"value":"(clojure.core/not false)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/not</span>","value":"clojure.core/not"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>&lt;</span>","value":"<"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>0</span>","value":"0"}],"value":"(< 1 0)"}],"value":"(clojure.core/not (< 1 0))"}],"value":"(clojure.core/and (clojure.core/not false) (clojure.core/not (< 1 0)))"}
;; <=

;; @@
(defmacro and-not [& forms]
  `(and ~@(map (fn [form] `(not ~form)) forms)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;fuscia-thorns/and-not</span>","value":"#'fuscia-thorns/and-not"}
;; <=

;; @@
(macroexpand-1 '(and-not false (< 1 0)))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/and</span>","value":"clojure.core/and"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/not</span>","value":"clojure.core/not"},{"type":"html","content":"<span class='clj-unkown'>false</span>","value":"false"}],"value":"(clojure.core/not false)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/not</span>","value":"clojure.core/not"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>&lt;</span>","value":"<"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>0</span>","value":"0"}],"value":"(< 1 0)"}],"value":"(clojure.core/not (< 1 0))"}],"value":"(clojure.core/and (clojure.core/not false) (clojure.core/not (< 1 0)))"}
;; <=

;; @@
(and-not false (< 1 0))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}
;; <=

;; @@
(meta (var and-not))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:ns</span>","value":":ns"},{"type":"html","content":"<span class='clj-unkown'>#&lt;Namespace fuscia-thorns&gt;</span>","value":"#<Namespace fuscia-thorns>"}],"value":"[:ns #<Namespace fuscia-thorns>]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:name</span>","value":":name"},{"type":"html","content":"<span class='clj-symbol'>and-not</span>","value":"and-not"}],"value":"[:name and-not]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:file</span>","value":":file"},{"type":"html","content":"<span class='clj-string'>&quot;/private/var/folders/_9/cnphhzmn0k3502kcyk235y580000gn/T/form-init5009639552726118117.clj&quot;</span>","value":"\"/private/var/folders/_9/cnphhzmn0k3502kcyk235y580000gn/T/form-init5009639552726118117.clj\""}],"value":"[:file \"/private/var/folders/_9/cnphhzmn0k3502kcyk235y580000gn/T/form-init5009639552726118117.clj\"]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:column</span>","value":":column"},{"type":"html","content":"<span class='clj-unkown'>1</span>","value":"1"}],"value":"[:column 1]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:line</span>","value":":line"},{"type":"html","content":"<span class='clj-unkown'>1</span>","value":"1"}],"value":"[:line 1]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:macro</span>","value":":macro"},{"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}],"value":"[:macro true]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:arglists</span>","value":":arglists"},{"type":"html","content":"<span class='clj-unkown'>([&amp; forms])</span>","value":"([& forms])"}],"value":"[:arglists ([& forms])]"}],"value":"{:ns #<Namespace fuscia-thorns>, :name and-not, :file \"/private/var/folders/_9/cnphhzmn0k3502kcyk235y580000gn/T/form-init5009639552726118117.clj\", :column 1, :line 1, :macro true, :arglists ([& forms])}"}
;; <=

;; **
;;; ## Tips & Tricks
;; **

;; **
;;; Do the bulk of your macro work in a separate function.
;; **

;; @@
`(require '[clojure.string :as string])
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/require</span>","value":"clojure.core/require"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>quote</span>","value":"quote"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.string</span>","value":"clojure.string"},{"type":"html","content":"<span class='clj-keyword'>:as</span>","value":":as"},{"type":"html","content":"<span class='clj-symbol'>fuscia-thorns/string</span>","value":"fuscia-thorns/string"}],"value":"[clojure.string :as fuscia-thorns/string]"}],"value":"(quote [clojure.string :as fuscia-thorns/string])"}],"value":"(clojure.core/require (quote [clojure.string :as fuscia-thorns/string]))"}
;; <=

;; @@
`(require '[clojure.string :as ~string])
;; @@

;; @@
`(require '[clojure.string :as ~'string])
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.core/require</span>","value":"clojure.core/require"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>quote</span>","value":"quote"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>clojure.string</span>","value":"clojure.string"},{"type":"html","content":"<span class='clj-keyword'>:as</span>","value":":as"},{"type":"html","content":"<span class='clj-symbol'>string</span>","value":"string"}],"value":"[clojure.string :as string]"}],"value":"(quote [clojure.string :as string])"}],"value":"(clojure.core/require (quote [clojure.string :as string]))"}
;; <=
