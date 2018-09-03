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

(def all-projects [{:title "project 1"
                    :cover "img/projects/tony-seltzer.gif"
                    :category ["recent"]},
                   {:title "project 2"
                    :cover "img/projects/tony-seltzer.gif"
                    :category ["recent"]},
                   {:title "project 3"
                    :cover "img/projects/tony-seltzer.gif"
                    :category ["recent"]},
                   {:title "project 4"
                    :cover "img/projects/tony-seltzer.gif"
                    :category ["recent"]},
                   {:title "project 5"
                    :cover "img/projects/tony-seltzer.gif"
                    :category ["recent"]},
                   {:title "project 6"
                    :cover "img/projects/tony-seltzer.gif"
                    :category ["recent"]},
                   {:title "project 7"
                    :cover "img/projects/tony-seltzer.gif"
                    :category ["past"]},
                   {:title "project 8"
                    :cover "img/projects/tony-seltzer.gif"
                    :category ["past"]},
                   {:title "project 9"
                    :cover "img/projects/tony-seltzer.gif"
                    :category ["past"]},
                   {:title "project 10"
                    :cover "img/projects/tony-seltzer.gif"
                    :category ["past"]}
                   {:title "project 11"
                    :cover "img/projects/tony-seltzer.gif"
                    :category ["past"]}
                   {:title "project 12"
                    :cover "img/projects/tony-seltzer.gif"
                    :category ["past"]}])

(def colors ["#fbb4ae"   ;red
             "#fed9a6"   ;orange
             "#ccebc5"   ;green
             "#b3cde3"   ;blue
             "#f2f2f2"    ;white
             "#decbe4"   ;purple
             "#b3cde3"   ;blue
             "#f2f2f2"   ;white
             "#decbe4"   ;purple
             "#fbb4ae"   ;red
             "#fed9a6"   ;orange
             "#ccebc5"]) ;green

(defn filter-by-cat [cat]
  (filter (fn [p]
            (some
              #(when (= % cat) %)
              (:category p)))
    all-projects))

(defn home-view []
  ;(let [db-key :projects]                                    ;; 0. declare unique db-key
  ;  (rf/reg-sub db-key #(db-key %))                          ;; 1. register subscriber db-key
  ;  (rf/dispatch [:get-contentful-data db-key query :media]) ;; 2. retrieve contentful data & pass key for assoc in db
  ;  (if-let [projects (rf/subscribe [db-key])]
  ;    (prn projects)
  (let [categories ["recent"
                    "past"]]
    [:div
     [:div.projects-gallery
      (for [cat categories
            :let [projects (filter-by-cat cat)]]
        ^{:key (gensym)}
        [:div.category-wrap
         [:h1 cat]
         [:div.category-projects

          (for [project projects
                :let [{:keys [title cover]} project
                      n (.indexOf all-projects project)
                      color (nth colors n)]]
            ^{:key (gensym)}
            [bubble color cover title])]])]]))