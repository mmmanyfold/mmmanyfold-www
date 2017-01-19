(ns mmm.views.gallery
  (:require [re-frame.core :as re-frame]))

(defn gallery-view []
  [:div.gallery-wrap

    [:div.gallery-section
      [:h2 [:mark "Client Work"]]

      [:div.gallery-row
        [:div.gallery-item
          [:img {:src "img/gallery/playground.jpg"}]
          [:div.label "Playground Coffee Shop"]]
        [:div.gallery-item
          [:img {:src "img/gallery/sporting-life.gif"}]
          [:div.label "Sporting Life"]]
        [:div.gallery-item
          [:img {:src "img/gallery/princess-nokia.jpg"}]
          [:div.label "Princess Nokia"]]
        [:div.gallery-item
          [:img {:src "img/gallery/dizzy.jpg"}]
          [:div.label "Dizzy Magazine"]]
        [:div.gallery-item
          [:img {:src "img/gallery/picture-room.png"}]
          [:div.label "Picture Room"]]
        [:div.gallery-item
          [:img {:src "img/gallery/letter-racer.gif"}]
          [:div.label "Letter Racer"]]]]

    [:div.gallery-section
      [:h2 [:mark "Educational Design"]]

      [:div.gallery-row
        [:div.gallery-item
          [:img {:src "img/gallery/owlet.png"}]
          [:div.label "OWLET"]]
        [:div.gallery-item
          [:img {:src "img/gallery/design-challenge.gif"}]
          [:div.label "Denver Art Museum"]]
        [:div.gallery-item
          [:img {:src "img/gallery/girls-in-stem.jpg"}]
          [:div.label "Girls in STEM"]]]]

    [:div.gallery-section
      [:h2 [:mark "Talks + Performances"]]

      [:div.gallery-row
        [:div.gallery-item
          [:img {:src "img/gallery/moonconf.jpg"}]
          [:div.label "MoonConf"]]
        [:div.gallery-item
          [:img {:src "img/gallery/north-hs.gif"}]
          [:div.label "Denver Public Schools"]]
        [:div.gallery-item
          [:img {:src "img/gallery/owl-tech-fair.jpg"}]
          [:div.label "OWL Tech Fair"]]
        [:div.gallery-item
          [:img {:src "img/gallery/signal-to-noise.gif"}]
          [:div.label "Signal-to-Noise Studios"]]]]])
