(ns clj-dgraph-client.ddl
  (:import (io.dgraph DgraphProto$Operation)))

(defn set-schema [dgraph-client schema]
  (->> (-> (DgraphProto$Operation/newBuilder)
           (.setSchema schema)
           (.build))
       (.alter dgraph-client)))
