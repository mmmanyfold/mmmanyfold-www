(ns mmm.views.home
  (:require [re-frame.core :as rf]
            [mmm.components.projects :refer [projects-component]]
            [mmm.components.talks :refer [talks-component]]))

(defn home-view []
  (fn []
    (rf/dispatch [:set-css-content-class "default-content"])
    (rf/dispatch [:set-titles "mmmanyfold" "web + app dev studio"])
    [:div.home-wrap
     ; [:div.greet
     ;  [:h1 "Got a project idea?"]
     ;  [:p "say " [:a {:href "mailto:hello@mmmanyfold.com"} "hello@mmmanyfold.com"]]]
     [projects-component]
     [:div.flex-row.footer
       [:div.definition
        [:h1 "man·y·fold"]
        [:h2 "ˈmenēˌfōld/"]
        [:i "adverb"]
        [:ol
          [:li "by many times."]
          [:li "\"the impact would be multiplied manyfold\""]]
        [:i "adjective"]
        [:ol
         [:li "involving multiplication by many times."]
         [:li "\"the manyfold increase in ideas\""]]]
       [:div.about
        [:h1 "we build websites, apps, and learning experiences"]
        [:h2 "Our mission is to turn ideas into well-crafted digital products through creative collaboration."]
        [:h2 "Say " [:a.rainbow {:href "mailto:hello@mmmanyfold.com"} "hello@mmmanyfold.com"] " !"]]]
        ; [:h2 "mmmanyfold is "
        ;  [:a.rainbow {:href "https://github.com/eemshi" :target "_blank"} "MSL"]
        ;  " + "
        ;  [:a.rainbow {:href "/david"} "DAVM"]
        ;  " + all of our collaborators"]]]
     [talks-component]]))
