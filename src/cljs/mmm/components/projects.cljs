(ns mmm.components.projects
  (:require [re-com.core :as re-com :refer-macros [handler-fn]]
            [re-frame.core :as re-frame]
            [cljsjs.photoswipe]
            [cljsjs.photoswipe-ui-default]
            [reagent.core :as reagent :refer [atom]]))

(defn project [title img summary width height]
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

(defn projects-component []
  (reagent/create-class
    {:component-did-mount
     #(js/initPhotoSwipeFromDOM ".project-gallery")
     :reagent-render
     (fn []
       [:div.scrolling-gallery
         [:h2.section-label "our work"]
         [:div.project-gallery
           [:div.gallery-row
             {:item-scope "true"
              :item-type  "http://schema.org/ImageGallery"}

             [project
              "Sporting Life"
              "img/gallery/sporting-life.gif"
              [:h2
               [:p "Custom website for NYC producer Sporting Life."]
               [:a {:class "rabbit"
                    :href "http://sportinglife.nyc"} "sportinglife.nyc"]
               [:p.tech "Github Pages"
                [:br] "Video by Georgia Studio."]]
              "640"
              "429"]

             [project
              "Picture Room"
              "img/gallery/picture-room.png"
              [:h2
               [:p "Custom website/online shop for Picture Room."]
               [:a {:class "rabbit"
                    :href "https://pictureroom.shop"} "pictureroom.shop"]
               [:p.tech "Lightspeed eCom, mmmanyfold API, Facebook Graph API"
                [:br] "Graphic design by Benjamin Critton."]]
              "665"
              "525"]

             [project
              "Playground Coffee Shop"
              "img/gallery/playground.jpg"
              [:h2
               [:p "Custom website for Brooklyn coffee shop, venue, and consignment store."]
               [:a {:class "rabbit"
                    :href "http://playgroundcoffeeshop.com"} "playgroundcoffeeshop.com"]
               [:p.tech "mmmanyfold API, Clojure(script), re-frame, Mailgun, Contentful, AWS"]]
              "553"
              "400"]

             [project
              "Princess Nokia"
              "img/gallery/princess-nokia.jpg"
              [:h2
               [:p "Custom website for NYC artist Princess Nokia."]
               [:a {:class "rabbit"
                    :href "http://princessnokia.org"} "princessnokia.org"]
               [:p.tech "mmmanyfold API, Clojure, Node.js, React.js, AWS"
                [:br] "Art by Destiny Frasqueri."]]
              "425"
              "405"]

             [project
              "OWLET"
              "img/gallery/owlet.png"
              [:h2
               [:p "Web platform designed for middle schoolers to explore creative tech via self-guided activities. Made in collaboration with Code for Denver."]
               [:a {:class "rabbit"
                    :href "http://owlet.codefordenver.org"} "owlet.codefordenver.org"]
               [:p.tech "Owlet API, Clojure(script), re-frame, PostgreSQL, Contentful, Auth0, Firebase, AWS"]]
              "588"
              "588"]

             [project
              "Dizzy Magazine"
              "img/gallery/dizzy.jpg"
              [:h2
               [:p "Custom website for Dizzy Magazine."]
               [:a {:class "rabbit"
                    :href "http://www.dizzymagazine.com"} "dizzymagazine.com"]
               [:p.tech "mmmanyfold API, Clojure(script), re-frame, AWS"
                [:br] "Graphic design by Arvid Logan and Milah Libin."]]
              "221"
              "286"]

             [project
              "Letter Racer"
              "img/gallery/letter-racer.gif"
              [:h2
               [:p "Custom website/online shop for the NYC music + art collective."]
               [:a {:class "rabbit"
                    :href "http://letterracer.com"} "letterracer.com"]
               [:p.tech "Shopify, Cart.js, Jekyll, Github Pages, AWS"
                [:br] "Graphic design by Arvid Logan and Reuben Sinder."]]
              "640"
              "500"]

             [project
              "Secret Circle"
              "img/gallery/secret-circle.gif"
              [:h2
               [:p "Custom launchpad for the Secret Circle."]
               [:a {:class "rabbit"
                    :href "http://thesecretcircle.net"} "thesecretcircle.net"]
               [:p.tech "Github Pages, npm"
                [:br] "Art by Brian (Narwhals of Sound)."]]
              "500"
              "400"]

            [project
             "COPA-SMS"
             "img/gallery/copa.gif"
             [:h2
              [:p "SMS onboarding service for "
                [:a {:class "rabbit"
                     :href "https://coloradopeoplesalliance.org/"} "Colorado People's Alliance"]
                " (COPA) text alerts. With automatic user exports and archiving features."]
              [:p.tech "Twilio, AWS Lambda, Node.js, Mailgun"]]
             "384"
             "406"]]

           (full-screen-gallery)]])}))
