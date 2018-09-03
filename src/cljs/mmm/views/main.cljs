(ns mmm.views.main
  (:require [re-frame.core :as rf]
            [re-com.core :as re-com]
            [mmm.views.home :refer [home-view]]
            [mmm.views.davm :refer [davm-view]]
            [mmm.views.contact :refer [contact-view]]
            [mmm.views.project :refer [project-view]]))

(defn- views [view-name]
       (case view-name
             :home-view [home-view]
             :contact-view [contact-view]
             :davm-view [davm-view]
             :project-view [project-view]
             [:div]))

(defn view []
  [:div.nav
   [:span [:a {:href "http://blog.mmmanyfold.com" :target "_blank"} "Blog"]]]
  [:div.page
   [views @(rf/subscribe [:active-view])]])