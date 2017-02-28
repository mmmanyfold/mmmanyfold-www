(ns mmm.views.gallery
  (:require [re-com.core :as re-com :refer-macros [handler-fn]]
            [re-com.popover]
            [re-frame.core :as re-frame]
            [reagent.core :as reagent :refer [atom]]))

(defonce og-popover-states (reduce #(assoc %1 (keyword (str %2)) false) {} (range 1 14)))

(def popover-states (reagent/atom og-popover-states))

(defn set-popover-state [id]
  (reset! popover-states og-popover-states)
  (swap! popover-states update-in [id] not))

(defn project [id title img summary info]
  (let [showing? (reagent/atom (id @popover-states))]
    [re-com/popover-anchor-wrapper
     :showing? showing?
     :position :above-right
     :anchor [:div.gallery-item
              [:a {:on-click (handler-fn (set-popover-state id))}
               [:img {:src img}]
               [:div.label title]]]
     :popover [re-com/popover-content-wrapper
               :width "320px"
               :title (str "project : " title)
               :body [:div
                      [:p.summary summary]
                      info]]]))

(defn gallery-view []
  [:div.gallery-wrap

   [:div.gallery-section
    [:h2 [:mark "Our Work"]]

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
                  [:p [:a {:href "http://playgroundcoffeeshop.com"} "playgroundcoffeeshop.com"]]]]

                [project :2
                 "Sporting Life"
                 "img/gallery/sporting-life.gif"
                 "Custom website for NYC producer Sporting Life."
                 [:div
                  [:p [:i "Github Pages"]]
                  [:p [:a {:href "http://sportinglife.nyc"} "sportinglife.nyc"]]]]

                [project :3
                 "Princess Nokia"
                 "img/gallery/princess-nokia.jpg"
                 "Custom website for NYC artist Princess Nokia."
                 [:div
                  [:p [:i "mmmanyfold API, Clojure, Node.js, React.js, AWS"]]
                  [:p [:a {:href "http://princessnokia.org"} "princessnokia.org"]]]]

                [project :4
                 "Dizzy Magazine"
                 "img/gallery/dizzy.jpg"
                 "Custom website for Dizzy Magazine."
                 [:div
                  [:p [:i "mmmanyfold API, Clojure(script), re-frame, AWS"]]
                  [:p [:b [:i "Coming Soon"]]]]]

                [project :7
                 "OWLET"
                 "img/gallery/owlet.png"
                 "Web platform designed for middle schoolers to explore creative tech via self-guided activities."
                 [:div
                  [:p [:i "Owlet API, Clojure(script), re-frame, PostgreSQL, Contentful, Auth0, Firebase, AWS"]]
                  [:p [:a {:href "http://owlet.codefordenver.org"} "owlet.codefordenver.org"]]]]

                [project :5
                 "Picture Room"
                 "img/gallery/picture-room.png"
                 "Custom website/online shop for Picture Room."
                 [:div
                  [:p [:i "Lightspeed eCom, Github Pages, mmmanyfold API, Facebook Graph API"]]
                  [:p [:a {:href "http://pictureroom.mcnallyjacksonstore.com"} "pictureroom.mcnallyjacksonstore.com"]]]]

                [project :6
                 "Letter Racer"
                 "img/gallery/letter-racer.gif"
                 "Custom website/online shop for the NYC music + art collective."
                 [:div
                  [:p [:i "Shopify, Cart.js, Jekyll, Github Pages, AWS"]]
                  [:p [:a {:href "http://letterracer.com"} "letterracer.com"]]]]]]]

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

   [:div.gallery-section
    [:h2 [:mark "Talks + Performances"]]

    [re-com/h-box
     :width "100%"
     :class "gallery-row"
     :children [
                [project :10
                 "MoonConf"
                 "img/gallery/moonconf.jpg"
                 "Presented at MoonConf 2016 by Shelby Switzer & David A. Viramontes"
                 [:div
                  [:p "Watch: " [:a {:href "https://youtu.be/tnKPuoWniTg?list=PLWYAdh9hA7geMRCgwDomlHAdwlsno32LU"} "Hacking Web Apps with Clojure(Script) @ Code for Denver"]]
                  [:p "We ♥ " [:a {:href "http://moonconf.org/"} "MoonConf"]]]]

                [project :11
                 "Denver Public Schools"
                 "img/gallery/north-hs.gif"
                 [:span
                  [:a {:href "http://blog.mmmanyfold.com/tag/denver-public-schools/"}
                   "Code art demos "]
                  "in Denver area public schools."]
                 [:div
                  [:p [:i "Clojure, mmmanyfold API, Giphy API, Facebook API, Indico, VVVV"]]]]

                [project :12
                 "OWL Tech Fair"
                 "img/gallery/owl-tech-fair.jpg"
                 "Audio/visual code art performance + workshop for Denver youth."
                 [:div
                  [:p [:i "Clojure, Shadertone, Sonic Pi"]]
                  [:p [:a {:href "http://blog.mmmanyfold.com/2017/02/06/sonic-pi-shadertone-owl-tech-fair/"} "Photos"]]]]

                [project :13
                 "Signal-to-Noise Media Lab"
                 "img/gallery/signal-to-noise.gif"
                 "Audio/visual code art performance for Denver Startup Week 2016."
                 [:div
                  [:p [:i "Clojure, Shadertone, Sonic Pi"]]
                  [:p [:a {:href "https://www.denverstartupweek.org/panel-picker/2399-an-amalgamation-of-art-science-and-technology"} "An Amalgamation of Art, Science, and Technology @ Signal-to-Noise"]]]]]]]])
