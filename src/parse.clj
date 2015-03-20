(ns parse
  (:require [instaparse.core :as insta]
            [clojure.walk :refer [postwalk]]))

(def gorilla
  (insta/parser
   "S = Format (Markdown | Code)*
    LineEnd = '\n'
    ContentPrefix = ';;; '
    EmptyContent = ';;; '
    Content = #'.+'
    FormatNumber = #'[0-9]+'
    Format = ';; gorilla-repl.fileformat = ' FormatNumber LineEnd+
    MarkdownStart = ';; **' LineEnd
    MarkdownEnd = ';; **' LineEnd+
    MarkdownContent = ((ContentPrefix Content LineEnd) | (EmptyContent LineEnd))+
    Markdown = MarkdownStart MarkdownContent MarkdownEnd
    ConsoleStart = ';; ->' LineEnd
    ConsoleEnd = ';; <-' LineEnd+
    ConsoleContent = ((ContentPrefix Content LineEnd) | (EmptyContent LineEnd))+
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

(defn remove-output [tree]
  (postwalk (fn [node]
              (if (vector? node)
                (let [[tag] node]
                  (when-not (#{:Output :Console} tag)
                    node))
                node))
            tree))

(defn save-file [file tree]
  (->> tree
       flatten
       (remove keyword?)
       (apply str)
       (spit file)))

(defn clear-output [file]
  (->> file parse-file remove-output (save-file file)))
