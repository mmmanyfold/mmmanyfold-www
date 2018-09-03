(ns mmm.views.home
  (:require [re-frame.core :as rf]
            [mmm.components.bubble :refer [bubble]]))

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

(defn filter-by-cat [cat projects]
  (filter (fn [p]
            (some
              #(when (= % cat) %)
              (:category p)))
          projects))

(defn home-view []
  (let [db-key :projects]
    (rf/reg-sub db-key #(db-key %))
    (rf/dispatch [:get-contentful-data db-key])
    (if-let [projects (map #(:fields %) @(rf/subscribe [db-key]))]
      (let [categories ["recent"
                        "past"]]
        [:div.projects-gallery
         (for [cat categories
               :let [projects (filter-by-cat cat projects)]]
           ^{:key (gensym)}
           [:div.category-wrap
            [:h1 cat]
            [:div.category-projects

             (for [project projects
                   :let [{:keys [title cover]} project
                         n (.indexOf projects project)
                         color (nth colors n)]]
               ^{:key (gensym)}
               [bubble color cover title])]])])
      [:div "loading..."])))