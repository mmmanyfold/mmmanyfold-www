(ns mmm.components.projects
  (:require [re-com.core :as re-com :refer-macros [handler-fn]]
            [re-frame.core :as re-frame]
            [cljsjs.photoswipe]
            [cljsjs.photoswipe-ui-default]
            [reagent.core :as reagent :refer [atom]]))

(defn project [title img summary info width height]
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
     [:span [:h2.title title]
            [:p.summary summary]
            info]]])


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
         [:h2.section-label [:mark "Our Work"]]
         [:div.project-gallery
           [:div.gallery-row
             {:item-scope "true"
              :item-type  "http://schema.org/ImageGallery"}
             [project
              "Playground Coffee Shop"
              "img/gallery/playground.jpg"
              "Custom website for Brooklyn coffee shop, venue, and consignment store."
              [:div.info
               [:a {:href "http://playgroundcoffeeshop.com"} "playgroundcoffeeshop.com"]
               [:p [:i "mmmanyfold API, Clojure(script), re-frame, Mailgun, Contentful, AWS"]]]
              "553"
              "400"]

             [project
              "Sporting Life"
              "img/gallery/sporting-life.gif"
              "Custom website for NYC producer Sporting Life."
              [:div.info
               [:a {:href "http://sportinglife.nyc"} "sportinglife.nyc"]
               [:p [:i "Github Pages"]]]
              "640"
              "429"]

             [project
              "Princess Nokia"
              "img/gallery/princess-nokia.jpg"
              "Custom website for NYC artist Princess Nokia."
              [:div.info
               [:a {:href "http://princessnokia.org"} "princessnokia.org"]
               [:p [:i "mmmanyfold API, Clojure, Node.js, React.js, AWS"]]]
              "425"
              "405"]

             [project
              "Dizzy Magazine"
              "img/gallery/dizzy.jpg"
              "Custom website for Dizzy Magazine."
              [:div.info
               [:a {:href "http://www.dizzymagazine.com"} "dizzymagazine.com"]
               [:p [:i "mmmanyfold API, Clojure(script), re-frame, AWS"]]]
              "221"
              "286"]

             [project
              "OWLET"
              "img/gallery/owlet.png"
              "Web platform designed for middle schoolers to explore creative tech via self-guided activities."
              [:div.info
               [:a {:href "http://owlet.codefordenver.org"} "owlet.codefordenver.org"]
               [:p [:i "Owlet API, Clojure(script), re-frame, PostgreSQL, Contentful, Auth0, Firebase, AWS"]]]
              "588"
              "588"]

             [project
              "Picture Room"
              "img/gallery/picture-room.png"
              "Custom website/online shop for Picture Room."
              [:div.info
               [:a {:href "https://pictureroom.shop"} "pictureroom.shop"]
               [:p [:i "Lightspeed eCom, Github Pages, mmmanyfold API, Facebook Graph API"]]]
              "665"
              "525"]

             [project
              "Letter Racer"
              "img/gallery/letter-racer.gif"
              "Custom website/online shop for the NYC music + art collective."
              [:div.info
               [:a {:href "http://letterracer.com"} "letterracer.com"]
               [:p [:i "Shopify, Cart.js, Jekyll, Github Pages, AWS"]]]
              "640"
              "500"]]

           (full-screen-gallery)]])}))


; [project :8
;  "Denver Art Museum"
;  "img/gallery/design-challenge.gif"
;  "A design summer camp for ages 9-12, reimagined each year."
;  [:div.info
;   [:a {:href "design-challenge.html"} "Design Challenge 2016"]]]]
;
; [project :9
;  "Girls in STEM"
;  "img/gallery/girls-in-stem.jpg"
;  "Denver area middle school after-school program introducing girls to women in STEM."
;  [:div.info
;   [:a {:href "http://gstemnams.weebly.com/"} "GSTEM @ North Arvada"]]]]]]]
