(ns mmm-clj.env
  (:require [environ.core :refer [env]]
            [adzerk.env :as env]))

(env/def mmm-contentful-server-space-id (System/getenv "MMM_CONTENTFUL_SERVER_SPACE_ID"))

(env/def mmm-contentful-server-access-token (System/getenv "MMM_CONTENTFUL_SERVER_ACCESS_TOKEN"))

(defmacro get-env [k] (get env k))