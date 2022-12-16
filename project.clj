(defproject org.clojars.philipperolet/m0-commons "0.1"
  :description "Commons for Machine Zero clojure code"
  :url "https://github.com/philipperolet/m0-talk"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.758"]
                 [org.clojure/tools.logging "1.2.4"]]
  :source-paths ["src"]

  :jvm-opts ["-Dclojure.tools.logging.factory=clojure.tools.logging.impl/jul-factory"]
  :main mzero.misc.anki
  :profiles {:dev {:dependencies [[com.bhauman/figwheel-main "0.2.18"]
                                  [com.bhauman/rebel-readline-cljs "0.1.4"]]}})
