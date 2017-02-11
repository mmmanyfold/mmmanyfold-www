(ns mmm.views.gallery
  (:require [re-com.core :as re-com :refer-macros [handler-fn]]
            [re-com.popover]
            [re-frame.core :as re-frame]
            [reagent.core :as reagent :refer [atom]]))

(def visibility (reagent/atom "hidden"))

(defonce og-popover-states (reduce #(assoc %1 (keyword (str %2)) false) {} (range 1 14)))

(def popover-states (reagent/atom og-popover-states))

(defn set-popover-state [id]
  (reset! popover-states og-popover-states)
  (swap! popover-states update-in [id] not))

(defn project [id url img title description]
  (let [showing? (reagent/atom (id @popover-states))]
    [re-com/popover-anchor-wrapper
      :showing? showing?
      :position :above-right
      :anchor   [:div.gallery-item
                 [:a {:href url
                      :on-mouse-enter (handler-fn (set-popover-state id))}
                  [:img {:src img}]
                  [:div.label title]]]
      :popover  [re-com/popover-content-wrapper
                 :width "320px"
                 :title (str "project : " title)
                 :body description]]))

(defn gallery-view []
  [:div.gallery-wrap

    [:div.gallery-section
      [:h2 [:mark "Client Work"]]

      [re-com/h-box
        :width "100%"
        :class "gallery-row"
        :children [
                    [project :1
                             "http://playgroundcoffeeshop.com"
                             "img/gallery/playground.jpg"
                             "Playground Coffee Shop"
                             [:div
                              [:p.summary "Custom web app for Brooklyn coffee shop / venue / consignment."]
                              [:p [:i "mmmanyfold API, Clojure(script), re-frame, Mailgun, Contentful, AWS"]]
                              [:p [:a {:href "http://playgroundcoffeeshop.com"} "playgroundcoffeeshop.com"]]
                              [:p [:b "created by Michelle + David"]]]]

                    [project :2
                             "http://sportinglife.nyc"
                             "img/gallery/sporting-life.gif"
                             "Sporting Life"
                             [:div
                              [:p.summary "Custom website for NYC producer Sporting Life."]
                              [:p [:i "Github Pages"]]
                              [:p [:a {:href "http://sportinglife.nyc"} "sportinglife.nyc"]]
                              [:p [:b "created by Michelle"]]]]

                    [project :3
                             "http://princessnokia.org"
                             "img/gallery/princess-nokia.jpg"
                             "Princess Nokia"
                             [:div
                              [:p.summary "Custom website for NYC artist Princess Nokia."]
                              [:p [:i "mmmanyfold API, Node.js, Clojure, React, AWS"]]
                              [:p [:a {:href "http://princessnokia.org"} "princessnokia.org"]]
                              [:p [:b "created by Michelle + David"]]]]

                    [project :4
                             ""
                             "img/gallery/dizzy.jpg"
                             "Dizzy Magazine"
                             [:div
                              [:p.summary "Custom website for Dizzy Magazine."]
                              [:p [:i "mmmanyfold API, Clojure(script), re-frame, AWS"]]
                              [:p [:b [:i "Coming Soon"]]]
                              [:p [:b "created by Michelle + David"]]]]

                    [project :5
                             "http://pictureroom.mcnallyjacksonstore.com"
                             "img/gallery/picture-room.png"
                             "Picture Room"
                             [:div
                              [:p.summary "Custom website/online shop for Picture Room, a McNally Jackson Store."]
                              [:p [:i "Lightspeed eCom, mmmanyfold API, Facebook Graph API, Github Pages"]]
                              [:p [:a {:href "http://pictureroom.mcnallyjacksonstore.com"} "pictureroom.mcnallyjacksonstore.com"]]
                              [:p [:b "created by Michelle + David"]]]]

                    [project :6
                             "http://letterracer.com"
                             "img/gallery/letter-racer.gif"
                             "Letter Racer"
                             [:div
                              [:p.summary "Custom website/online shop for NYC music + art collective."]
                              [:p [:i "Shopify, Cart.js, Jekyll, Github Pages, AWS"]]
                              [:p [:a {:href "http://letterracer.com"} "letterracer.com"]]
                              [:p [:b "created by Michelle"]]]]]]]

    [:div.gallery-section
      [:h2 [:mark "Education Design"]]

      [re-com/h-box
        :width "100%"
        :class "gallery-row"
        :children [
                    [project :7
                             "http://owlet.codefordenver.org"
                             "img/gallery/owlet.png"
                             "OWLET"
                             [:div
                              [:p.summary "Web platform designed for middle schoolers to explore creative tech via self-guided activities."]
                              [:p [:i "Owlet API, Clojure(script), re-frame, PostgreSQL, Contentful, Auth0, Firebase, AWS"]]
                              [:p [:a {:href "http://owlet.codefordenver.org"} "owlet.codefordenver.org"]]
                              [:p [:b "created by Michelle, David, and Code for Denver"]]]]

                    [project :8
                             "design-challenge.html"
                             "img/gallery/design-challenge.gif"
                             "Denver Art Museum"
                             [:div
                              [:p.summary "A design summer camp for ages 9-12, reimagined every year."]
                              [:p [:a {:href "design-challenge.html"} "Design Challenge 2016"]]
                              [:p [:b "created, designed, and facilitated by Michelle"]]]]

                    [project :9
                             "http://gstemnams.weebly.com/"
                             "img/gallery/girls-in-stem.jpg"
                             "Girls in STEM"
                             [:div
                              [:p.summary "Denver area middle school after-school program to get girls excited about STEM!"]
                              [:p [:a {:href "http://gstemnams.weebly.com/"} "GSTEM @ North Arvada"]]
                              [:p [:b "program directed by Michelle"]]]]]]]

    [:div.gallery-section
      [:h2 [:mark "Talks + Performances"]]

      [re-com/h-box
        :width "100%"
        :class "gallery-row"
        :children [
                    [project :10
                             "https://youtu.be/tnKPuoWniTg?list=PLWYAdh9hA7geMRCgwDomlHAdwlsno32LU"
                             "img/gallery/moonconf.jpg"
                             "MoonConf"
                             [:div
                              [:p.summary "Presented at MoonConf 2016 by Shelby Switzer & David A. Viramontes"]
                              [:p [:a {:href "https://youtu.be/tnKPuoWniTg?list=PLWYAdh9hA7geMRCgwDomlHAdwlsno32LU"} "Watch: Hacking Web Apps with Clojure(Script) @ Code for Denver"]]
                              [:p [:a {:href "http://moonconf.org/"} "moonconf.org"]]]]

                    [project :11
                             "http://blog.mmmanyfold.com/tag/denver-public-schools/"
                             "img/gallery/north-hs.gif"
                             "Denver Public Schools"
                             [:div
                              [:p.summary [:a {:href "http://blog.mmmanyfold.com/tag/denver-public-schools/"}]
                                   "Code art demos "
                               "in Denver area public schools."]
                              [:p [:i "Clojure, mmmanyfold API, Giphy API, Facebook API, Indico, VVVV"]]
                              [:p [:b "created/presented by Michelle + David"]]]]

                    [project :12
                             "http://blog.mmmanyfold.com/2017/02/06/sonic-pi-shadertone-owl-tech-fair/"
                             "img/gallery/owl-tech-fair.jpg"
                             "OWL Tech Fair"
                             [:div
                              [:p.summary "Audio/visual code art performance + workshop for Denver youth."]
                              [:p [:i "Clojure, Shadertone, Sonic Pi"]]
                              [:p [:a {:href "http://blog.mmmanyfold.com/2017/02/06/sonic-pi-shadertone-owl-tech-fair/"} "Photos"]]
                              [:p [:b "created/presented by Michelle + David"]]]]

                    [project :13
                             "http://blog.mmmanyfold.com/2017/02/06/signal-to-noise/"
                             "img/gallery/signal-to-noise.gif"
                             "Signal-to-Noise Studios"
                             [:div
                              [:p.summary "Audio/visual code art performance for Denver Startup Week 2016."]
                              [:p [:i "Clojure, Shadertone, Sonic Pi"]]
                              [:p [:a {:href "http://blog.mmmanyfold.com/2017/02/06/signal-to-noise/"} "Blog post"]]
                              [:p [:b "created/presented by Michelle, David, and Gary Daniels"]]]]]]]])
