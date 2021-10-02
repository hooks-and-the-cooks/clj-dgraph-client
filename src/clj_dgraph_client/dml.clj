(ns clj-dgraph-client.dml
  (:require [clojure.tools.logging :as log])
  (:import (com.google.gson Gson)
           (io.dgraph DgraphProto$Mutation)
           (com.google.protobuf ByteString)))

(defn mutate [dgraph-client object]
  (let [gson-object (new Gson)
        transaction (.newTransaction dgraph-client)]
    (try
      (let [json-string (.toJson gson-object object)
            mutation    (-> (DgraphProto$Mutation/newBuilder)
                            (.setSetJson (ByteString/copyFromUtf8 (.toString json-string)))
                            (.build))]
        (.mutate transaction mutation)
        (.commit transaction))
      (catch Exception e
        (log/error "Not able to run the mutations")
        (.discard transaction)
        (throw e)))))

(defn query [dgraph-client query class-ref]
  (try
    (let [gson-object (new Gson)
          transaction (.newTransaction dgraph-client)
          response    (.query transaction query)]
      (.fromJson gson-object (.getJson response) class-ref))
    (catch Exception e
      (log/error "Not able to run the query")
      (throw e))))
