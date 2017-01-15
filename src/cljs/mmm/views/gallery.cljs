(ns mmm.views.gallery
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]))

(defn gallery-view []
  [:div.gallery-wrap
    [:div.gallery-section
      [:h2 "Client Work"]
      [:div.flex-row
        [:div.flex-item
          [:img "img/logo-graydient.png"]]]]
    [:div.gallery-section
      [:h2 "Client Work"]
      [:div.flex-row
        [:div.flex-item
          [:img "img/logo-graydient.png"]]]]])
