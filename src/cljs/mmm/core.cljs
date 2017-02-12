(ns mmm.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [re-frisk.core :refer [enable-re-frisk!]]
            [mmm.events]
            [mmm.subs]
            [mmm.routes :as routes]
            [mmm.views :as views]
            [mmm.config :as config]
            [mmm.components.logo :refer [GSAnimationSeq]]))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (enable-re-frisk!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  ;(GSAnimationSeq) ;; draw mmm logo
  (reagent/render [views/main-view]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
