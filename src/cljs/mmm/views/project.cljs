(ns mmm.views.project
  (:require [re-frame.core :as rf]))

(defn project-view []
  (let [project @(rf/subscribe [:project-in-view])]
    [:div.project-view-wrap
     [:div.project-view-image
      [:div.img-bubble {:style {:width "100%"
                                :height "100%"
                                :background-size "100%"
                                :border-radius "50%"
                                :border-color "#b3cde3"
                                :background-image (str "url('" "img/projects/tony-seltzer.gif" "')")}}]]
     [:div.project-view-details
      [:h1 "Remember the Memories"]
      [:h2 "Website for Tony Seltzer's \"Remember the Memories\" tape release via Letter Racer."]
      [:h2 [:a {:href "http://tonyseltzer.letterracer.com/"} "tonyseltzer.letterracer.com"]]
      [:p "Tech: jQuery, three.js"]
      [:p "Album art by NSP"]]]))