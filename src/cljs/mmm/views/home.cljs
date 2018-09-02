(ns mmm.views.home
  (:require [re-frame.core :as rf]
            [mmm.components.bubble :refer [bubble]]))

(def query
  "{
    allContentfulProject {
      edges {
        node {
          title
          cover {
            id
          }
          category
        }
      }
		},
    allContentfulAsset {
      edges {
        node {
          id
          resize(width: 500) {
            src
          }
        }
      }
		}
	}")

(def projects [{:title "project 1"
                :cover "img/projects/tony-seltzer.gif"
                :category ["art"]},
               {:title "project 1"
                :cover "img/projects/tony-seltzer.gif"
                :category ["art"]},
               {:title "project 1"
                :cover "img/projects/tony-seltzer.gif"
                :category ["art"]},
               {:title "project 1"
                :cover "img/projects/tony-seltzer.gif"
                :category ["art"]},
               {:title "project 1"
                :cover "img/projects/tony-seltzer.gif"
                :category ["art"]},
               {:title "project 1"
                :cover "img/projects/tony-seltzer.gif"
                :category ["art"]},
               {:title "project 1"
                :cover "img/projects/tony-seltzer.gif"
                :category ["online shop"]},
               {:title "project 1"
                :cover "img/projects/tony-seltzer.gif"
                :category ["online shop"]},
               {:title "project 1"
                :cover "img/projects/tony-seltzer.gif"
                :category ["business", "online shop"]},
               {:title "project 1"
                :cover "img/projects/tony-seltzer.gif"
                :category ["education", "nonprofit"]}])

(defn filter-by-cat [cat]
  (filter (fn [p]
            (some
              #(when (= % cat) %)
              (:category p)))
    projects))

(defn home-view []
  ;(let [db-key :projects]                                    ;; 0. declare unique db-key
  ;  (rf/reg-sub db-key #(db-key %))                          ;; 1. register subscriber db-key
  ;  (rf/dispatch [:get-contentful-data db-key query :media]) ;; 2. retrieve contentful data & pass key for assoc in db
  ;  (if-let [projects (rf/subscribe [db-key])]
  ;    (prn projects)
  (let [categories ["art"
                    "business"
                    "online shop"
                    "nonprofit"
                    "education"
                    "installation"
                    "mobile"]]
    [:div
     [:div
      (for [cat categories
            :let [projects (filter-by-cat cat)]]
        ^{:key (gensym)}
        [:div
         [:h1 cat]
         (for [project projects
               :let [{:keys [title cover category]} project]]
           ^{:key (gensym)}
           [bubble :blue title])])]]))