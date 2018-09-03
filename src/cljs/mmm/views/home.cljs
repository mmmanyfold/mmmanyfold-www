(ns mmm.views.home
  (:require [re-frame.core :as rf]
            [mmm.components.bubble :refer [bubble]]))

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
                :let [projects (filter-by-cat cat projects)]]
            ^{:key (gensym)}
            [:div
             [:h1 cat]
             (for [project projects
                   :let [{:keys [title cover category]} project]]
               ^{:key (gensym)}
               [bubble :blue title])])]])
      [:div "loading..."])))