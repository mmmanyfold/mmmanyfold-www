(ns mmm.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
  :view-title
  (fn [db]
    (:view-title db)))

(rf/reg-sub
  :active-view
  (fn [db _]
    (:active-view db)))

(rf/reg-sub
  :project-list
  (fn [db _]
    (get-in db [:profiles :davm :projects])))
