(ns mmm.views
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]))

;; home

(defn link-to-about-page []
  [re-com/hyperlink-href
   :label "go to About Page"
   :href "#/about"])

(defn home-panel []
  [re-com/v-box
   :gap "1em"
   :children [[link-to-about-page]]])
;; about

(defn about-title []
  [re-com/title
   :label "This is the About Page."
   :level :level1])

(defn link-to-home-page []
  [re-com/hyperlink-href
   :label "go to Home Page"
   :href "#/"])

(defn about-panel []
  [re-com/v-box
   :gap "1em"
   :children [[about-title] [link-to-home-page]]])


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

;; [:h3 [:mark "UX DESIGN/DEVELOPMENT BY MICHELLE LIM + DAVID VIRAMONTES-MARTINEZ"]]

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [:div.content
       [:div.header
        [:div
         [:a {:href "/"}
          [:img.logo {:src "img/logo-graydient.png"}]]
         [:nav
          [:span "Gallery"]
          "/"
          [:span "About"]
          "/"
          [:span "Contact"]
          "/"
          [:span "Blog"]]]
        [:h1 "mmmanyfold"]]
       [re-com/v-box
        :height "100%"
        :children [[panels @active-panel]]]])))
