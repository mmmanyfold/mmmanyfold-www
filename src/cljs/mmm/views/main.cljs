(ns mmm.views.main
  (:require [re-frame.core :as rf]
            [re-com.core :as re-com]
            [mmm.views.home :refer [home-view]]
            [mmm.views.davm :refer [davm-view]]
            [mmm.views.contact :refer [contact-view]]))

(defn- views [view-name]
       (case view-name
             :home-view [home-view]
             :contact-view [contact-view]
             :davm-view [davm-view]
             [:div]))

(defn view []
  (let [{:keys [title subtitle]} @(rf/subscribe [:view-titles])]
       [:div.content
        [:div.header
         [:div
          [:a.logo {:href "/"}
           [:img {:src "img/logo-graydient.png"}]]
          [:nav
           [:span [:a {:href "http://blog.mmmanyfold.com" :target "_blank"} "Blog"]]]]
         [:h1 title]
         [:p.subtitle subtitle]]
        [:div.page
         [views @(rf/subscribe [:active-view])]]]))
