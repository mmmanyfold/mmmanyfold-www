(ns mmm.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
  :view-titles
  (fn [db]
    (:view-titles db)))

(rf/reg-sub
  :active-view
  (fn [db _]
    (:active-view db)))

(rf/reg-sub
  :project-in-view
  (fn [db _]
    (:project-in-view db)))

(rf/reg-sub
  :project-list
  (fn [db _]
    (get-in db [:profiles :davm :projects])))

(rf/reg-sub
  :content-css-class
  (fn [db _]
      (get-in db [:content-css-class])))
