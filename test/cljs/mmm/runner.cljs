(ns mmm.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [mmm.core-test]))

(doo-tests 'mmm.core-test)
