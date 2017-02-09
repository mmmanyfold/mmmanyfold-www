(ns mmm.views.gallery
  (:require [re-com.core :as re-com :refer-macros [handler-fn]]
            [re-com.popover]
            [re-frame.core :as re-frame]
            [reagent.core :as reagent :refer [atom]]))

(def visibility (reagent/atom "hidden"))

(defn gallery-view []
 (let [showing-1? (reagent/atom false)
       showing-2? (reagent/atom false)]
  [:div.gallery-wrap

    [:div.gallery-section
      [:h2 [:mark "Client Work"]]

      [:div.gallery-row

        [re-com/popover-anchor-wrapper
          :showing? showing-1?
          :position :above-right
          :anchor   [:div.gallery-item {:on-mouse-enter (handler-fn (reset! showing-1? true)
                                                                    (reset! showing-2? false))}
                      [:a {:href "http://playgroundcoffeeshop.com"}
                        [:img {:src "img/gallery/playground.jpg"}]
                        [:div.label "Playground Coffee Shop"]]]
          :popover  [re-com/popover-content-wrapper
                     :backdrop-opacity 0.3
                     :title "project : Playground Coffee Shop"
                     :body     [:div
                                [:p "Fully custom website for coffee shop / venue / consignment in Brookyln, NY."]
                                [:p [:a {:href "http://playgroundcoffeeshop.com"} "playgroundcoffeeshop.com"]]
                                [:p "Clojure(script), re-frame, Mailgun, Contentful"]]]]

        [re-com/popover-anchor-wrapper
          :showing? showing-2?
          :position :above-right
          :anchor   [:div.gallery-item {:on-mouse-enter (handler-fn (reset! showing-2? true)
                                                                    (reset! showing-1? false))}
                      [:a {:href "http://sportinglife.nyc"}
                        [:img {:src "img/gallery/sporting-life.gif"}]
                        [:div.label "Sporting Life"]]]
          :popover  [re-com/popover-content-wrapper
                     :backdrop-opacity 0.3
                     :title "project : Sporting Life"
                     :body     [:div
                                [:p "Fully custom website for coffee shop / venue / consignment in Brookyln, NY."]
                                [:p [:a {:href "http://sportinglife.nyc"} "sportinglife.nyc"]]
                                [:p "HTML/CSS, jQuery"]]]]

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
      [:h2 [:mark "Education Design"]]

      [:div.gallery-row
        [:div.gallery-item
         [:a {:href "http://owlet.codefordenver.org"}
          [:img {:src "img/gallery/owlet.png"}]
          [:div.label "OWLET"]]]
        [:div.gallery-item
         [:a {:href "design-challenge.html"}
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
         [:a {:href "http://blog.mmmanyfold.com/tag/denver-public-schools/"}
          [:img {:src "img/gallery/north-hs.gif"}]
          [:div.label "Denver Public Schools"]]]
        [:div.gallery-item
         [:a {:href "http://blog.mmmanyfold.com/2017/02/06/sonic-pi-shadertone-owl-tech-fair/"}
          [:img {:src "img/gallery/owl-tech-fair.jpg"}]
          [:div.label "OWL Tech Fair"]]]
        [:div.gallery-item
         [:a {:href "http://blog.mmmanyfold.com/2017/02/06/signal-to-noise/"}
          [:img {:src "img/gallery/signal-to-noise.gif"}]
          [:div.label "Signal-to-Noise Studios"]]]]]]))
