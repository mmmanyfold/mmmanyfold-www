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
       [:div {:class @(rf/subscribe [:content-css-class])}
        [:div.nav
         [:div
           ; [:span [:a {:href "/services" :target "_blank"} "Services"]]
           ; [:span [:a {:href "/portfolio" :target "_blank"} "Portfolio"]]
           ; [:span [:a {:href "/about" :target "_blank"} "About"]]
           [:span [:a {:href "http://blog.mmmanyfold.com" :target "_blank"} "Blog"]]]]
        [:header
         [:div.img-wrapper
          [:a.logo {:href "/"}
           [:img {:src "img/logo-graydient-invert.png"}]]]
         [:h1 title]
         [:p.subtitle subtitle]]
        [:div.page
         [views @(rf/subscribe [:active-view])]]]))
