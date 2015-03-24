;; gorilla-repl.fileformat = 1

;; **
;;; # Understanding Quoting and Macros
;;; 
;;; 2015 RoomKey Spring Technical Conference
;; **

;; @@
(ns fuscia-thorns
  (:require [clojure.pprint :as pprint :refer [pprint]]))
;; @@

;; @@
(def fruit "Bananas")
;; @@

;; **
;;; ## Quote
;; **

;; @@
(def quoted
  '(1 :a \b "Apples" fruit inc pprint/pprint #"[0-9]+" [1 2] (java.util.Date.) {:name "Billy"} #{:a :b}))
;; @@

;; @@
(type quoted)
;; @@

;; @@
(pprint (map type quoted))
;; @@

;; @@
quoted
;; @@

;; **
;;; ### Notes
;;; * Quote is recursive.
;;; * Symbols and lists are treated specially.
;;; * Anything can be quoted.
;; **

;; **
;;; ## Syntax Quote
;; **

;; @@
(def squoted
  `(1 :a \b "Apples" fruit inc pprint/pprint #"[0-9]+" [1 2] (java.util.Date.) {:name "Billy"} #{:a :b}))
;; @@

;; @@
(type squoted)
;; @@

;; @@
squoted
;; @@

;; **
;;; ### Notes
;;; * Syntax quote is recursive.
;;; * Symbols and lists are treated specially.
;;; * Anything can be syntax quoted.
;;; * Symbols are prefixed with their fully qualified namespace.
;; **

;; **
;;; ### Unquote
;; **

;; @@
(def x 3)
;; @@

;; @@
`(inc x)
;; @@

;; @@
`(inc ~x)
;; @@

;; @@
`(inc ~(+ 2 3))
;; @@

;; @@
~x
;; @@

;; **
;;; ### Unquote splicing
;; **

;; @@
(def fruits ["Apples" "Bananas" "Oranges"])
;; @@

;; @@
`(str fruits)
;; @@

;; @@
`(str ~fruits)
;; @@

;; @@
`(str ~@fruits)
;; @@

;; @@
`[~@#{:a :b :c}]
;; @@

;; @@
`[~@"hi"]
;; @@

;; @@
`[~@(range 5)]
;; @@

;; @@
~@[1 2]
;; @@

;; **
;;; ### Hygiene and gensyms
;; **

;; @@
`(let [n 3] n)
;; @@

;; @@
(defmacro bad-hygiene []
  `(let [n 3] n))
;; @@

;; @@
(bad-hygiene)
;; @@

;; @@
`(let [n# 3] n#)
;; @@

;; @@
(defmacro good-hygiene [num]
  `(let [n# 3] (+ n# ~num)))
;; @@

;; @@
(let [n 2]
  (good-hygiene n))
;; @@

;; @@
(macroexpand-1 '(good-hygiene n))
;; @@

;; @@
'(clojure.core/let [n 3] (clojure.core/+ n n))
;; @@

;; @@
(gensym)
;; @@

;; @@
(let [sym (gensym)]
  `(let [~sym 3] ~sym))
;; @@

;; @@
(list 'do `(let [n# 3] n#) `(let [n# 4] n#))
;; @@

;; @@
(let [sym (gensym)]
  (list 'do `(let [~sym 3] ~sym) `(let [~sym 4] ~sym)))
;; @@

;; **
;;; ## Macros
;;; * Macros are functions that run at macro-expansion time and are passed quoted forms.
;;; * Unlike functions, macros are not values, and cannot be passed around.
;; **

;; @@
(defn and-not [& forms]
  `(and ~@(map (fn [form] `(not ~form)) forms)))
;; @@

;; @@
(meta (var and-not))
;; @@

;; @@
(and-not 'false '(< 1 0))
;; @@

;; @@
(defmacro and-not [& forms]
  `(and ~@(map (fn [form] `(not ~form)) forms)))
;; @@

;; @@
(macroexpand-1 '(and-not false (< 1 0)))
;; @@

;; @@
(and-not false (< 1 0))
;; @@

;; @@
(meta (var and-not))
;; @@

;; **
;;; ## Tips & Tricks
;; **

;; **
;;; * Do the bulk of your macro work in a separate function (see rk.annotate.fns).
;;; * Macros should be pure, though you can run arbitrary code at macro-expansion time.
;; **

;; **
;;; ### Bypassing Hygiene
;; **

;; @@
`(require '[clojure.string :as string])
;; @@

;; @@
`(require '[clojure.string :as ~'string])
;; @@

;; @@
(let [x '[clojure.string :as string]]
  `(require ~x))
;; @@

;; @@
(let [x '[clojure.string :as string]]
  `(require '~x))
;; @@

;; @@
`(require 'my.namespace)
;; @@

;; @@
`(require 'my-namespace)
;; @@

;; @@
`(require '~'my-namespace)
;; @@

;; **
;;; ## Next Steps
;; **

;; **
;;; * Download this talk (https://github.com/dubiousdavid/macros-talk).
;;; * Play around with the examples.
;;; * Understand the `and-not` macro.
;;; * Understand the macros in rk-annotate.
;;; * Resist the temptation to look at the core.async macros (e.g., go blocks) :)
;; **
