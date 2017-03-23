(ns mmm.views.home
  (:require [re-frame.core :as re-frame]
            [reagent.core :as reagent :refer [atom]]
            [mmm.components.gallery :refer [gallery-component]]))

(defn home-view []
  [:div.home-wrap
   [:div.greet.white
    [:h1 "Let's collaborate."]
    [:p "say " [:a {:href "mailto:hello@mmmanyfold.com"} "hello@mmmanyfold.com"]]]
   [:div {:style {:height "250px"
                  :background-color "#594B4A"}}]
   [:div.values.flex-row.white
    [:div.text-box
     [:h2 "innovation"]
     [:p "We use languages, frameworks and processes that give us and our clients more power, freedom and flexibility"]]
    [:div [:img {:src "img/logo-plus.png"}]]
    [:div.text-box
     [:h2 "collaboration"]
     [:p "We’re inspired by creative people across geographic and sector boundaries"]]
    [:div [:img {:src "img/logo-equals.png"}]]
    [:div.text-box
     [:h2 "outcome"]
     [:p "We strive to make our customer and user experiences distinctly more meaningful, efficient – and enjoyable."]]]
  ;  [:div.process
  ;   [:h2 "The key to our approach is active listening: we pay close attention to people’s wants and needs, incorporating that into every phase of our creation cycle."]
  ;   [:div.graphic
  ;    [:img {:src "img/process-placeholder.png"}]]]
   [:div.definition.white
    [:h1 "man·y·fold"]
    [:h2 "ˈmenēˌfōld/"]
    [:i "adverb"]
    [:ol
      [:li "by many times."]
      [:li "\"the impact would be multiplied manyfold\""]]
    [:br]
    [:i "adjective"]
    [:ol
     [:li "involving multiplication by many times."]
     [:li "\"the manyfold increase in ideas\""]]]])
