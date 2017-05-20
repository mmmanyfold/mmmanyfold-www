(ns mmm.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
  :view-title
  (fn [db]
    (:view-title db)))

(re-frame/reg-sub
  :active-view
  (fn [db _]
    (:active-view db)))
