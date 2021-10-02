(ns clj-dgraph-client.core-test
  (:require [clojure.test :refer :all]
            [clj-dgraph-client.core :refer :all])
  (:import (io.dgraph DgraphClient)))

(deftest create-client-test
  (testing "should be able to get dgraph client instance"
    (let [client (create-client "localhost" 8000)]
      (is (instance? DgraphClient client))))

  (testing "should return nil and catch exception if port is not an integer"
    (is (thrown? Exception (create-client "localhost" "random-string-port")))))
