(ns mmm.events
    (:require [re-frame.core :as re-frame]
              [mmm.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 :set-active-view
 (fn [db [_ active-view]]
   (assoc db :active-view active-view)))
