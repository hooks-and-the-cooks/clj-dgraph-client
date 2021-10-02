(ns clj-dgraph-client.core
  (:require [clojure.tools.logging :as log])
  (:import (io.grpc ManagedChannelBuilder)
           (io.dgraph DgraphGrpc DgraphClient DgraphGrpc$DgraphStub)))

(defn create-client [host-address host-port]
  (try
    (let [grpc-channel (-> (ManagedChannelBuilder/forAddress host-address host-port)
                           (.usePlaintext)
                           (.build))
          dgraph-stub (DgraphGrpc/newStub grpc-channel)
          dgraph-client (DgraphClient. (into-array DgraphGrpc$DgraphStub [dgraph-stub]))]
      dgraph-client)
    (catch Exception e
      (log/error "Not able to start the dgraph client")
      (throw e))))