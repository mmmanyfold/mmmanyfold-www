(ns mmm.views.gallery
  (:require [re-frame.core :as re-frame]
            [reagent.core :as reagent :refer [atom]]))

(def visibility (reagent/atom "hidden"))

(defn gallery-view []
  [:div.gallery-wrap

    [:div.gallery-section
      [:h2 [:mark "Client Work"]]

      [:div.gallery-row
        [:div.gallery-item
         [:a {:href "http://playgroundcoffeeshop.com"}
          [:img {:src "img/gallery/playground.jpg"}]
          [:div.label "Playground Coffee Shop"]]]
        [:div.gallery-item
         [:a {:href "http://sportinglife.nyc"}
          [:img {:src "img/gallery/sporting-life.gif"}]
          [:div.label "Sporting Life"]]]
        [:div.gallery-item
         [:a {:href "http://princessnokia.org"}
          [:img {:src "img/gallery/princess-nokia.jpg"}]
          [:div.label "Princess Nokia"]]]
        [:div.gallery-item {:on-mouse-enter #(reset! visibility "visible")
                            :on-mouse-leave #(reset! visibility "hidden")}
          [:div.soon {:style {:visibility @visibility}}
           "Coming Soon"]
          [:img {:src "img/gallery/dizzy.jpg"}]
          [:div.label "Dizzy Magazine"]]
        [:div.gallery-item
         [:a {:href "http://pictureroom.mcnallyjacksonstore.com"}
          [:img {:src "img/gallery/picture-room.png"}]
          [:div.label "Picture Room"]]]
        [:div.gallery-item
         [:a {:href "http://letterracer.com"}
          [:img {:src "img/gallery/letter-racer.gif"}]
          [:div.label "Letter Racer"]]]]]

    [:div.gallery-section
      [:h2 [:mark "Educational Design"]]

      [:div.gallery-row
        [:div.gallery-item
         [:a {:href "http://owlet.codefordenver.org"}
          [:img {:src "img/gallery/owlet.png"}]
          [:div.label "OWLET"]]]
        [:div.gallery-item
         [:a {:href "http://www.mmmanyfold.com/design-challenge.html"}
          [:img {:src "img/gallery/design-challenge.gif"}]
          [:div.label "Denver Art Museum"]]]
        [:div.gallery-item
         [:a {:href "http://gstemnams.weebly.com/"}
          [:img {:src "img/gallery/girls-in-stem.jpg"}]
          [:div.label "Girls in STEM"]]]]]

    [:div.gallery-section
      [:h2 [:mark "Talks + Performances"]]

      [:div.gallery-row
        [:div.gallery-item
         [:a {:href "https://youtu.be/tnKPuoWniTg?list=PLWYAdh9hA7geMRCgwDomlHAdwlsno32LU"}
          [:img {:src "img/gallery/moonconf.jpg"}]
          [:div.label "MoonConf"]]]
        [:div.gallery-item
         [:a {:href "https://mmmanyfold.wordpress.com/tag/denver-public-schools/"}
          [:img {:src "img/gallery/north-hs.gif"}]
          [:div.label "Denver Public Schools"]]]
        [:div.gallery-item
         [:a {:href "https://mmmanyfold.wordpress.com/2017/02/06/sonic-pi-shadertone-owl-tech-fair/"}
          [:img {:src "img/gallery/owl-tech-fair.jpg"}]
          [:div.label "OWL Tech Fair"]]]
        [:div.gallery-item
         [:a {:href "https://www.instagram.com/p/BKb_PKsBio4/?taken-by=eemshi"}
          [:img {:src "img/gallery/signal-to-noise.gif"}]
          [:div.label "Signal-to-Noise Studios"]]]]]])
