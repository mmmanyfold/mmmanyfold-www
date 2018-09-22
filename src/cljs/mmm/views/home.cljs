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

(defn- get-image [id assets]
  (let [img (some #(when (= id (-> % :sys :id)) %) assets)]
    (-> img :fields :file :url)))

(defn home-view []
  (let [subs #{:projects :assets}]
    (doall (map (fn [s] (rf/reg-sub s #(s %))) subs))
    (rf/dispatch [:get-contentful-entries (:projects subs)])
    (if-let [projects @(rf/subscribe [(:projects subs)])]
      (let [assets @(rf/subscribe [(:assets subs)])
            projects (map #(:fields %) projects)
            categories ["recent"
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
                           cover-id (-> cover :sys :id)
                           img (get-image cover-id assets)
                           n (.indexOf projects project)
                           color (nth colors n)]]
                 ^{:key (gensym)}
                 [bubble color img title])]])])
      [:div "loading..."])))