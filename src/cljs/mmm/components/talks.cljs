(ns mmm.components.talks
  (:require [re-com.core :as re-com :refer-macros [handler-fn]]
            [re-frame.core :as re-frame]
            [cljsjs.photoswipe]
            [cljsjs.photoswipe-ui-default]
            [reagent.core :as reagent :refer [atom]]))

(defn talk [title img summary width height]
 [:figure.gallery-item
  {:item-prop  "associatedMedia"
   :item-scope "true"
   :item-type  "http://schema.org/ImageObject"}
  [:a {:href img
       :item-prop "contentUrl"
       :data-size (str width "x" height)}
   [:img {:src img :item-prop "thumbnail"}]
   [:div.image-label title]]
  [:figcaption {:item-prop "caption description"
                :width (str width "px")}
   [:div [:h1.title title]
         summary]]])


(defn full-screen-gallery []
 [:div.pswp {:tabIndex "-1" :role "dialog" :aria-hidden "true"}
  [:div.pswp__bg]
  [:div.pswp__scroll-wrap
   [:div.pswp__container
    [:div.pswp__item]
    [:div.pswp__item]
    [:div.pswp__item]]
   [:div.pswp__ui.pswp__ui--hidden
    [:div.pswp__top-bar
     [:div.pswp__counter]
     [:button.pswp__button.pswp__button--close {:title "Close (Esc)"}]
     [:button.pswp__button.pswp__button--share {:title "Share"}]
     [:button.pswp__button.pswp__button--fs {:title "Toggle fullscreen"}]
     [:button.pswp__button.pswp__button--zoom {:title "Zoom in/out"}]
     [:div.pswp__preloader
      [:div.pswp__preloader__icn
       [:div.pswp__preloader__cut
        [:div.pswp__preloader__donut]]]]]
    [:div.pswp__share-modal.pswp__share-modal--hidden.pswp__single-tap
     [:div.pswp__share-tooltip]]
    [:button.pswp__button.pswp__button--arrow--left {:title "Previous (arrow left)"}]
    [:button.pswp__button.pswp__button--arrow--right {:title "Next (arrow right)"}]
    [:div.pswp__caption
     [:div.pswp__caption__center]]]]])

(defn talks-component []
  (reagent/create-class
    {:component-did-mount
     #(js/initPhotoSwipeFromDOM ".talks-gallery")
     :reagent-render
     (fn []
       [:div#style-3.scrolling-gallery
         [:h2.gallery-label "talks + performances"]
         [:div.talks-gallery
           [:div.gallery-row
             {:item-scope "true"
              :item-type  "http://schema.org/ImageGallery"}
            [talk
             "OWL Tech Fair"
             "img/gallery/owl-tech-fair.jpg"
             [:h2
              [:p "Audio/visual code art performance + workshop for Denver youth."]
              [:a {:class "rabbit"
                   :href "https://blog.mmmanyfold.com/2016/04/26/sonic-pi-shadertone-owl-tech-fair/"}
                "Photos"]
              [:p.tech "Clojure, Shadertone, Sonic Pi"]]
             "400"
             "430"]
            [talk
             "MoonConf"
             "img/gallery/moonconf.png"
             [:h2
              [:p "Watch: " [:a {:class "rabbit"
                                 :href "https://youtu.be/tnKPuoWniTg?list=PLWYAdh9hA7geMRCgwDomlHAdwlsno32LU"} "Hacking Web Apps with Clojure(Script) @ Code for Denver"]]
              [:p "Presented at MoonConf 2016 by Shelby Switzer & David A. Viramontes"]
              [:p "We ♥ " [:a {:class "rabbit"
                               :href "http://moonconf.org/"} "MoonConf"]]]
             "277"
             "163"]
            [talk
             "Code for Denver"
             "img/gallery/lisp.png"
             [:h2
              [:p [:a {:class "rabbit"
                       :href "https://codefordenver.github.io/tutorials/intro-to-cljs/"}
                    "Intro to Clojure(script)"]]
              [:p "Presented at Code for Denver by Michelle Lim & Tyler Perkins"]]
             "252"
             "211"]
            [talk
             "Denver Public Schools"
             "img/gallery/north-hs.gif"
             [:h2
              [:p [:a {:class "rabbit"
                       :href "http://blog.mmmanyfold.com/tag/denver-public-schools/"}
                   "Code art demos "]
                 "in Denver area public schools."]
              [:p.tech "Clojure, mmmanyfold API, Giphy API, Facebook API, Indico, VVVV"]]
             "268"
             "298"]
            [talk
             "Signal-to-Noise Media Lab"
             "img/gallery/signal-to-noise.gif"
             [:h2
              [:p "Audio/visual code art performance during Denver Startup Week 2016."]
              [:a {:class "rabbit"
                   :href "https://www.denverstartupweek.org/panel-picker/2399-an-amalgamation-of-art-science-and-technology"}
                "Event details"]
              [:p.tech "Clojure, Shadertone, Sonic Pi"]]
             "640"
             "454"]
            [talk
             "She Started It!"
             "img/gallery/she-started-it.jpg"
             [:h2
              [:p "Panel discussion with Shelly Sousa, Maria Popo, and Michelle Lim about women in tech leadership after a screening of "
               [:a {:class "rabbit"
                    :href "http://www.shestarteditfilm.com/"} "She Started It!"]]
              [:p.tech "Hosted by Women in Cable Telecommunications and Girls in STEM"]]
             "507"
             "500"]]

           (full-screen-gallery)]])}))
