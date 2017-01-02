(ns mmm.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [mmm.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
