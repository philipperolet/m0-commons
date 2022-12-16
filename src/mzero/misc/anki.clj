(ns mzero.misc.anki
  (:require [clojure.string :as str])
  (:gen-class))

(def separator "|")

(defn- remove-empty-lines [lines]
  (remove #(re-matches #"[\w]*" %) lines))

(defn- translate-line [entry-suffix index line]
  (cond
    (zero? index) ;; first line, should be a question
    (-> (str/replace line #"^- " "")
        (str separator))
    
    (re-find #"^- " line) ;; line is a question
    (-> (str/replace line #"^- " entry-suffix) ;; add suffix to previous entry
        (str separator))

    (re-find #"^<img" line) ;; line is an image
    (str "<br/>" line "<br/>")

    :else line))

(defn anki-trans [filename entry-tags]
  (let [lines 
        (-> (slurp filename)
            (str/split #"\n"))
        entry-suffix (str separator entry-tags "\n")
        add-suffix #(str % entry-suffix)]
    (->> lines
         remove-empty-lines
         (map-indexed (partial translate-line entry-suffix))         
         (apply str)
         add-suffix)))

(defn anki-trans! [filename question-prefix]
  (spit (str filename "-anki.txt") (anki-trans filename question-prefix)))

(defn -main [filename question-prefix]
  (anki-trans! filename question-prefix))
