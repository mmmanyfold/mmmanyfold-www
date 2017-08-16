(ns mmm.views
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]
            [mmm.views.home :refer [home-view]]
            [mmm.views.about :refer [about-view]]
            [mmm.views.contact :refer [contact-view]]))

(defn- views [view-name]
  (case view-name
    :home-view [home-view]
    :about-view [about-view]
    :contact-view [contact-view]
    [:div]))

(defn main-view []
  (let [active-view (re-frame/subscribe [:active-view])]
    (fn []
      [:div.content
        [:div.header
          [:div
            [:a.logo {:href "#"}
              [:img {:src "img/logo-graydient.png"}]]
            [:nav
              [:span [:a {:href "https://blog.mmmanyfold.com" :target "_blank"} "Blog"]]]]
          [:h1 "mmmanyfold"]
          [:p.subtitle "web + app dev studio"]]
        [:div.page
          [views @active-view]]])))
