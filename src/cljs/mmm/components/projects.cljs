(ns mmm.components.projects
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

(defn projects-component []
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
                  [:p [:a {:href "http://letterracer.com"} "letterracer.com"]]]]]]]])

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
