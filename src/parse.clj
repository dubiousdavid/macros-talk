(ns parse
  (:require [instaparse.core :as insta]
            [clojure.walk :refer [postwalk]]))

(def gorilla
  (insta/parser
   "S = Format (Markdown | Code)*
    LineEnd = '\n'
    ContentPrefix = ';;; '
    Content = #'.+'
    FormatNumber = #'[0-9]+'
    Format = ';; gorilla-repl.fileformat = ' FormatNumber LineEnd+
    MarkdownStart = ';; **' LineEnd
    MarkdownEnd = ';; **' LineEnd+
    MarkdownContent = ((ContentPrefix Content LineEnd) | (ContentPrefix LineEnd))+
    Markdown = MarkdownStart MarkdownContent MarkdownEnd
    ConsoleStart = ';; ->' LineEnd
    ConsoleEnd = ';; <-' LineEnd+
    ConsoleContent = ((ContentPrefix Content LineEnd) | (ContentPrefix LineEnd))+
    Console = ConsoleStart ConsoleContent ConsoleEnd
    OutputStart = ';; =>' LineEnd
    OutputEnd = ';; <=' LineEnd+
    OutputContent = (ContentPrefix Content LineEnd)+
    Output = OutputStart OutputContent OutputEnd
    CodeStart = ';; @@' LineEnd
    CodeEnd = ';; @@' LineEnd+
    CodeContent = (Content LineEnd)+
    Code = CodeStart CodeContent CodeEnd Console? Output?"))

(defn parse-file [file]
  (gorilla (slurp file)))

(defn remove-tags [tags tree]
  (postwalk (fn [node]
              (if (vector? node)
                (let [[tag] node]
                  (when-not (tags tag)
                    node))
                node))
            tree))

(defn filter-tags [tags tree]
  (postwalk (fn [node]
              (if (vector? node)
                (let [[tag] node]
                  (when (tags tag)
                    node))
                node))
            tree))

(defn remove-output [tree]
  (remove-tags #{:Output :Console} tree))

(defn stringify-tree [tree]
  (->> tree
       flatten
       (remove keyword?)
       (apply str)))

(defn save-file [file tree]
  (->> tree
       stringify-tree
       (spit file)))

(defn clear-output [file]
  (->> file parse-file remove-output (save-file file)))
