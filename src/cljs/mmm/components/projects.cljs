(ns mmm.components.projects
  (:require [re-com.core :as re-com :refer-macros [handler-fn]]
            [re-frame.core :as re-frame]
            [cljsjs.photoswipe]
            [cljsjs.photoswipe-ui-default]
            [reagent.core :as reagent :refer [atom]]))

(def filter-by (atom :all))

(defn filter-button [title & [display-name]]
  (let [n (if display-name display-name (name title))]
    [:div.filter-button
     [:a.filter-title {:on-click #(reset! filter-by title)}
      [:p (str "[ " n " ]")]]]))

(defn project [title img summary width height filters]
 (let [filters (conj filters :all)]
  (when (contains? filters @filter-by)
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
           summary]]])))

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
       [:div#style-3.scrolling-gallery
         [:h2.gallery-label "our work"]
         [:div.gallery-filter.flex-row
          "filter by: "
          [filter-button :nonprofit]
          [filter-button :business]
          [filter-button :online-shop "online shop"]
          [filter-button :music-art "music/art"]
          [filter-button :education]
          [filter-button :all]]
         [:div.project-gallery
           [:div.gallery-row
             {:item-scope "true"
              :item-type  "http://schema.org/ImageGallery"}

            [project
              "Sporting Life"
              "img/gallery/sporting-life.gif"
              [:h2
               [:p "Custom website for NYC producer Sporting Life."]
               [:a {:class "rabbit rainbow"
                    :href "http://sportinglife.nyc"} "sportinglife.nyc"]
               [:p.tech "Github Pages"
                [:br] "Video by Georgia Studio."]]
              "640"
              "429"
              #{:music-art}]

            [project
              "Picture Room"
              "img/gallery/picture-room.png"
              [:h2
               [:p "Custom website/online shop for Picture Room."]
               [:a {:class "rabbit rainbow"
                    :href "https://www.pictureroom.shop"} "pictureroom.shop"]
               [:p.tech "Lightspeed eCom, mmmanyfold API, Facebook Graph API"
                [:br] "Graphic design by Benjamin Critton."]]
              "543"
              "429"
              #{:business :online-shop}]

            [project
              "Secret Circle"
              "img/gallery/secret-circle.gif"
              [:h2
               [:p "Custom launchpad for the Secret Circle."]
               [:a {:class "rabbit rainbow"
                    :href "http://thesecretcircle.net"} "thesecretcircle.net"]
               [:p.tech "jQuery, Github Pages"
                [:br] "Art by Brian Blomerth."]]
              "500"
              "400"
             #{:music-art}]

            [project
             "Playground Coffee Shop"
             "img/gallery/playground.jpg"
             [:h2
              [:p "Custom website for Brooklyn coffee shop, venue, and consignment store."]
              [:a {:class "rabbit rainbow"
                   :href "http://playgroundcoffeeshop.com"} "playgroundcoffeeshop.com"]
              [:p.tech "mmmanyfold API, Clojure(script), re-frame, Mailgun, Contentful, AWS"]]
             "553"
             "400"
             #{:business}]

            [project
              "Tony Seltzer"
              "img/gallery/tony-seltzer.gif"
              [:h2
               [:p "Custom website for Tony Seltzer's \"Remember the Memories\" tape release."]
               [:a {:class "rabbit rainbow"
                    :href "http://tonyseltzer.letterracer.com"} "tonyseltzer.letterracer.com"]
               [:p.tech "jQuery, three.js, Github Pages"]]
              "480"
              "412"
             #{:music-art}]

            [project
              "OWLET"
              "img/gallery/owlet.png"
              [:h2
               [:p "Web platform designed for middle schoolers to explore creative tech via self-guided activities. Made in collaboration with Code for Denver."]
               [:a {:class "rabbit rainbow"
                    :href "http://owlet.codefordenver.org"} "owlet.codefordenver.org"]
               [:p.tech "Owlet API, Clojure(script), re-frame, PostgreSQL, Contentful, Auth0, Firebase, AWS"]]
              "429"
              "429"
             #{:education :nonprofit}]

            [project
              "Princess Nokia"
              "img/gallery/princess-nokia.jpg"
              [:h2
               [:p "Custom website for NYC artist Princess Nokia."]
               [:a {:class "rabbit rainbow"
                    :href "http://princessnokia.org"} "princessnokia.org"]
               [:p.tech "mmmanyfold API, Clojure, Node.js, React.js, AWS"
                [:br] "Art by Destiny Frasqueri."]]
              "425"
              "405"
             #{:music-art}]

            [project
              "COPA-SMS"
              "img/gallery/copa.gif"
              [:h2
               [:p "SMS onboarding service for "
                [:a {:class "rabbit rainbow"
                     :href "https://coloradopeoplesalliance.org/"} "Colorado People's Alliance"]
                " (COPA) text alerts. With automatic user exports and archiving features."]
               [:p.tech "Twilio, AWS Lambda, Node.js, Mailgun"]]
              "384"
              "406"
             #{:nonprofit}]

            [project
              "Girls in STEM"
              "img/gallery/gstem.png"
              [:h2
               [:p "Custom Squarespace site for Girls in STEM Denver."]
               [:a {:class "rabbit rainbow"
                    :href "http://gstemdenver.org"} "gstemdenver.org"]
               [:p.tech "Squarespace, mmmanyfold API"]]
              "300"
              "294"
             #{:nonprofit :education}]

            [project
              "Letter Racer"
              "img/gallery/letter-racer.gif"
              [:h2
               [:p "Custom website/online shop for the NYC music + art collective."]
               [:a {:class "rabbit rainbow"
                    :href "http://letterracer.com"} "letterracer.com"]
               [:p.tech "Shopify, Cart.js, Jekyll, Github Pages, AWS"
                [:br] "Graphic design by Arvid Logan and Reuben Sinder."]]
              "549"
              "429"
             #{:music-art :online-shop :business}]

            [project
              "Dizzy Magazine"
              "img/gallery/dizzy.jpg"
              [:h2
               [:p "Custom website for Dizzy Magazine."]
               [:a {:class "rabbit rainbow"
                    :href "http://www.dizzymagazine.com"} "dizzymagazine.com"]
               [:p.tech "mmmanyfold API, Clojure(script), re-frame, AWS"
                [:br] "Graphic design by Arvid Logan and Milah Libin."]]
              "221"
              "286"
             #{:music-art}]]

          (full-screen-gallery)]])}))
