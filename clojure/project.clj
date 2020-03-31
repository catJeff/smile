(defproject org.clojars.haifengl/smile "2.3.0"
  :description "Smile - Statistical Machine Intelligence and Learning Engine"
  :url "https://haifengl.github.io"
  :license {:name "GNU Lesser General Public License, Version 3"
            :url "https://opensource.org/licenses/LGPL-3.0"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.github.haifengl/smile-core "2.3.0"]
                 [com.github.haifengl/smile-io "2.3.0"]]
  :plugins [[lein-codox "0.10.7"]]
  :repl-options {:init-ns smile.ai})
