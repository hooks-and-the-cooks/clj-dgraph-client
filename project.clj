(defproject clj-dgraph-client "0.1.0"
  :description "Clojure Dgraph Client"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [io.dgraph/dgraph4j "21.03.1"]
                 [cambium/cambium.core "1.1.0"]
                 [cambium/cambium.codec-simple "1.0.0"]
                 [cambium/cambium.logback.core "0.4.4"]]
  :repl-options {:init-ns clj-dgraph-client.core})
