(ns clj-dgraph-client.integration.creation
  (:require [clojure.test :refer :all]
            [clj-dgraph-client.core :as core]))

; Run Dgraph Before running; will fix it further
(deftest creation-integration-test
  (testing "should be able to create client and check the dgraph version using it"
    (let [client (core/create-client "localhost" 9080)
          version (.checkVersion client)]
      (is (not (nil? version)))
      (is (not (nil? client))))))
