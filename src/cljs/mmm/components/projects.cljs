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
     [:a.filter-title.rainbow {:on-click #(reset! filter-by title)}
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
         [:h2.gallery-label "dev work"]
         [:div.gallery-filter.flex-row
          "filter by: "
          [filter-button :nonprofit]
          [filter-button :business]
          [filter-button :online-shop "online shop"]
          [filter-button :music-art "music/art"]
          [filter-button :education]
          [filter-button :mobile "mobile"]
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
               [:p.tech "Tech: Github Pages"
                [:br] "Video by Georgia Studio"]]
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
               [:p.tech "Tech: Lightspeed eCom, mmmanyfold API, Facebook Graph API"
                [:br] "Graphic design by Benjamin Critton"]]
              "543"
              "429"
              #{:business :online-shop}]

            [project
              "Tony Seltzer"
              "img/gallery/tony-seltzer.gif"
              [:h2
               [:p "Custom website for Tony Seltzer's \"Remember the Memories\" tape release."]
               [:a {:class "rabbit rainbow"
                    :href "http://tonyseltzer.letterracer.com"} "tonyseltzer.letterracer.com"]
               [:p.tech "Tech: jQuery, three.js, Github Pages"
                [:br] "Art by NSP"]]
              "480"
              "412"
              #{:music-art}]

            [project
              "This Machine Has A Soul"
              "img/gallery/future-machine.png"
              [:h2
               [:p "Custom website for Denver participatory budgeting pilot + community art project organized by Warm Cookies of the Revolution."]
               [:a {:class "rabbit rainbow"
                    :href "http://thismachinehasasoul.com"} "thismachinehasasoul.com"]
               [:p.tech "Tech: Clojure(script), re-frame, Node.js, GraphQL, AWS, Contentful"
                [:br] "Logo by MATTER studio"]]
              "680"
              "381"
              #{:nonprofit :music-art}]

            [project
              "OWLET"
              "img/gallery/owlet.png"
              [:h2
               [:p "Web platform designed for middle schoolers to explore creative tech via self-guided activities. Made in collaboration with Code for Denver."]
               [:a {:class "rabbit rainbow"
                    :href "http://owlet.codefordenver.org"} "owlet.codefordenver.org"]
               [:p.tech "Tech: Owlet API, Clojure(script), re-frame, PostgreSQL, Contentful, Auth0, Firebase, AWS"]]
              "429"
              "429"
              #{:education :nonprofit}]

            [project
              "Equalizer"
              "img/gallery/equalizer3.png"
              [:h2
               [:p "A mobile app that helps new fathers understand and achieve equally shared parenting."]
               [:a {:class "rabbit rainbow"
                    :href "https://github.com/mmmanyfold/equalizer/blob/master/README.md"} "See demo"]
               [:p.tech "Tech: React Native, Expo"
                [:br] "Concept design by Rocío Almanza Guien"
                [:br] "Graphic design by Sabrina Almanza Guien"]]
              "1134"
              "680"
              #{:mobile :education}]

            [project
              "SMS Onboarding"
              "img/gallery/copa.gif"
              [:h2
               [:p "SMS onboarding service for text alerts from "
                [:a {:class "rabbit rainbow"
                     :href "https://coloradopeoplesalliance.org"} "Colorado People's Alliance"]
                " (COPA) and "
                [:a {:class "rabbit rainbow"
                     :href "https://coloradopeoplesaction.org"} "Colorado People's Action"]
                " (CPA). With automatic user exports and archiving features."]
               [:p.tech "Tech: Twilio, AWS Lambda, Node.js, Mailgun"]]
              "384"
              "406"
              #{:nonprofit :mobile}]

            [project
              "Letter Racer"
              "img/gallery/letterracer.png"
              [:h2
               [:p "Custom website/online shop for NYC music + art collective."]
               [:a {:class "rabbit rainbow"
                    :href "http://letterracer.com/archive/"} "letterracer.com"]
               [:p.tech "Tech: Shopify, Cart.js, Jekyll, Github Pages, AWS"
                [:br] "Graphic design by Arvid Logan & Reuben Sinder"]]
              "810"
              "771"
              #{:music-art :online-shop :business}]

            [project
              "Playground Coffee Shop"
              "img/gallery/playground.jpg"
              [:h2
               [:p "Custom website for Brooklyn coffee shop, venue, and consignment store."]
               [:a {:class "rabbit rainbow"
                    :href "http://playgroundcoffeeshop.com"} "playgroundcoffeeshop.com"]
               [:p.tech "Tech: mmmanyfold API, Clojure(script), re-frame, Mailgun, Contentful, AWS"]]
              "553"
              "400"
              #{:business}]]

          (full-screen-gallery)]])}))
