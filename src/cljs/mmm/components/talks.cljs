(ns mmm.components.talks
  (:require [re-com.core :as re-com :refer-macros [handler-fn]]
            [re-com.popover]
            [re-frame.core :as re-frame]
            [reagent.core :as reagent :refer [atom]]))

(defonce og-popover-states (reduce #(assoc %1 (keyword (str %2)) false) {} (range 1 14)))

(def popover-states (reagent/atom og-popover-states))

(defn set-popover-state [id]
  (reset! popover-states og-popover-states)
  (swap! popover-states update-in [id] not))

(defn project [id title img summary info]
  (let [showing? (reagent/atom (id @popover-states))]
    [re-com/popover-anchor-wrapper
     :showing? showing?
     :position :above-right
     :anchor [:div.gallery-item
              [:a {:on-click (handler-fn (set-popover-state id))}
               [:img {:src img}]
               [:div.label title]]]
     :popover [re-com/popover-content-wrapper
               :width "320px"
               :title (str "project : " title)
               :body [:div
                      [:p.summary summary]
                      info]]]))

(defn talks-component []
  [:div.gallery-wrap

   [:div.gallery-section
    [:h2 [:mark "Talks + Performances"]]

    [re-com/h-box
     :width "100%"
     :class "gallery-row"
     :children [
                [project :10
                 "MoonConf"
                 "img/gallery/moonconf.jpg"
                 "Presented at MoonConf 2016 by Shelby Switzer & David A. Viramontes"
                 [:div
                  [:p "Watch: " [:a {:href "https://youtu.be/tnKPuoWniTg?list=PLWYAdh9hA7geMRCgwDomlHAdwlsno32LU"} "Hacking Web Apps with Clojure(Script) @ Code for Denver"]]
                  [:p "We ♥ " [:a {:href "http://moonconf.org/"} "MoonConf"]]]]

                [project :11
                 "Denver Public Schools"
                 "img/gallery/north-hs.gif"
                 [:span
                  [:a {:href "http://blog.mmmanyfold.com/tag/denver-public-schools/"}
                   "Code art demos "]
                  "in Denver area public schools."]
                 [:div
                  [:p [:i "Clojure, mmmanyfold API, Giphy API, Facebook API, Indico, VVVV"]]]]

                [project :12
                 "OWL Tech Fair"
                 "img/gallery/owl-tech-fair.jpg"
                 "Audio/visual code art performance + workshop for Denver youth."
                 [:div
                  [:p [:i "Clojure, Shadertone, Sonic Pi"]]
                  [:p [:a {:href "http://blog.mmmanyfold.com/2017/02/06/sonic-pi-shadertone-owl-tech-fair/"} "Photos"]]]]

                [project :13
                 "Signal-to-Noise Media Lab"
                 "img/gallery/signal-to-noise.gif"
                 "Audio/visual code art performance for Denver Startup Week 2016."
                 [:div
                  [:p [:i "Clojure, Shadertone, Sonic Pi"]]
                  [:p [:a {:href "https://www.denverstartupweek.org/panel-picker/2399-an-amalgamation-of-art-science-and-technology"} "An Amalgamation of Art, Science, and Technology @ Signal-to-Noise"]]]]]]]])
