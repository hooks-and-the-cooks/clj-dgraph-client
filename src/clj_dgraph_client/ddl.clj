(ns clj-dgraph-client.ddl
  (:require [clojure.tools.logging :as log])
  (:import (io.dgraph DgraphProto$Operation)))

(defn set-schema [dgraph-client schema]
  (try
    (->> (-> (DgraphProto$Operation/newBuilder)
             (.setSchema schema)
             (.build))
         (.alter dgraph-client))
    (catch Exception e
      (log/error "Not able to set the schema")
      (throw e))))
