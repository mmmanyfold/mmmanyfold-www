(ns mmm.components.projects
  (:require [re-com.core :as re-com :refer-macros [handler-fn]]
            [re-frame.core :as re-frame]
            [cljsjs.photoswipe]
            [cljsjs.photoswipe-ui-default]
            [reagent.core :as reagent :refer [atom]]))

(defn project [id title img summary info size]
  [:figure.gallery-item
   {:item-prop  "associatedMedia"
    :item-scope "true"
    :item-type  "http://schema.org/ImageObject"}
   [:a {:item-prop "contentUrl"
        :data-size size}
    [:img {:src img :item-prop "thumbnail"}]]
   [:figcaption {:item-prop "caption description"}
    [:div.label title]]])

(defn full-screen-gallery []
  [:div.activity-image-gallery-wrap
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
       [:div.pswp__caption__center]]]]]])

(defn projects-component []
  (reagent/create-class
    {:component-did-mount
     #(js/initPhotoSwipeFromDOM ".project-gallery")
     :reagent-render
     (fn []
       [:div.gallery-wrap

        [:div.gallery-section

         [:h2 [:mark "Our Work"]]

         [:div.project-gallery
          {:item-scope "true"
           :item-type  "http://schema.org/ImageGallery"}

          [re-com/h-box
           :width "100%"
           :class "gallery-row"
           :children [
                      [project :1
                       "Playground Coffee Shop"
                       "img/gallery/playground.jpg"
                       "Custom website for Brooklyn coffee shop, venue, and consignment store."
                       [:div
                        [:p [:i "mmmanyfold API, Clojure(script), re-frame, Mailgun, Contentful, AWS"]]
                        [:p [:a {:href "http://playgroundcoffeeshop.com"} "playgroundcoffeeshop.com"]]
                        "600x800"]]

                      [project :2
                       "Sporting Life"
                       "img/gallery/sporting-life.gif"
                       "Custom website for NYC producer Sporting Life."
                       [:div
                        [:p [:i "Github Pages"]]
                        [:p [:a {:href "http://sportinglife.nyc"} "sportinglife.nyc"]]
                        "600x800"]]

                      [project :3
                       "Princess Nokia"
                       "img/gallery/princess-nokia.jpg"
                       "Custom website for NYC artist Princess Nokia."
                       [:div
                        [:p [:i "mmmanyfold API, Clojure, Node.js, React.js, AWS"]]
                        [:p [:a {:href "http://princessnokia.org"} "princessnokia.org"]]
                        "600x800"]]

                      [project :4
                       "Dizzy Magazine"
                       "img/gallery/dizzy.jpg"
                       "Custom website for Dizzy Magazine."
                       [:div
                        [:p [:i "mmmanyfold API, Clojure(script), re-frame, AWS"]]
                        [:p [:b [:i "Coming Soon"]]]
                        "600x800"]]

                      [project :7
                       "OWLET"
                       "img/gallery/owlet.png"
                       "Web platform designed for middle schoolers to explore creative tech via self-guided activities."
                       [:div
                        [:p [:i "Owlet API, Clojure(script), re-frame, PostgreSQL, Contentful, Auth0, Firebase, AWS"]]
                        [:p [:a {:href "http://owlet.codefordenver.org"} "owlet.codefordenver.org"]]
                        "600x800"]]

                      [project :5
                       "Picture Room"
                       "img/gallery/picture-room.png"
                       "Custom website/online shop for Picture Room."
                       [:div
                        [:p [:i "Lightspeed eCom, Github Pages, mmmanyfold API, Facebook Graph API"]]
                        [:p [:a {:href "http://pictureroom.mcnallyjacksonstore.com"} "pictureroom.mcnallyjacksonstore.com"]]
                        "600x800"]]

                      [project :6
                       "Letter Racer"
                       "img/gallery/letter-racer.gif"
                       "Custom website/online shop for the NYC music + art collective."
                       [:div
                        [:p [:i "Shopify, Cart.js, Jekyll, Github Pages, AWS"]]
                        [:p [:a {:href "http://letterracer.com"} "letterracer.com"]]
                        "600x800"]]]]

          (full-screen-gallery)]]])}))


                ; [project :8
                ;  "Denver Art Museum"
                ;  "img/gallery/design-challenge.gif"
                ;  "A design summer camp for ages 9-12, reimagined each year."
                ;  [:div
                ;   [:p [:a {:href "design-challenge.html"} "Design Challenge 2016"]]]]
                ;
                ; [project :9
                ;  "Girls in STEM"
                ;  "img/gallery/girls-in-stem.jpg"
                ;  "Denver area middle school after-school program introducing girls to women in STEM."
                ;  [:div
                ;   [:p [:a {:href "http://gstemnams.weebly.com/"} "GSTEM @ North Arvada"]]]]]]]
