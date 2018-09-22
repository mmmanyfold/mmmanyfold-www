(ns mmm.views.project
  (:require [re-frame.core :as rf]
            [cljsjs.showdown]))

(def showdown (js/showdown.Converter.))

(defn- get-image [id assets]
  (let [img (some #(when (= id (-> % :sys :id)) %) assets)]
    (-> img :fields :file :url)))

(defn project-view []
  (let [subs #{:projects :assets}]
    (doall (map (fn [s] (rf/reg-sub s #(s %))) subs))
    (rf/dispatch [:get-contentful-entries (:projects subs)])
    (if-let [projects @(rf/subscribe [(:projects subs)])]
      (let [assets @(rf/subscribe [(:assets subs)])
            projects (map #(:fields %) projects)
            sorted-projects (reverse (sort-by :order projects))]
        [:div {:style {:padding "0 2em 3em 2em"}}
         [:h1.title "mmmanyfold dev studio"]
         [:h1 "We're building a new website! In the meantime, here are some of our projects (in no particular order). Contact: hello@mmmanyfold.com"]
         (for [project sorted-projects
               :let [{:keys [title description tech credits cover order]} project
                     cover-id (-> cover :sys :id)
                     img (get-image cover-id assets)]]
           ^{:key (gensym)}
           [:div.project-view-wrap
            [:div.project-view-image
             [:div.img-bubble {:style {:width            "100%"
                                       :height           "100%"
                                       :background-size  "100%"
                                       :border-radius    "50%"
                                       :border-width     "3px"
                                       :border-color     "#b3cde3"
                                       :background-image (str "url('" img "')")}}]]
            [:div.project-view-details
             [:h1 title]
             [:h2 {"dangerouslySetInnerHTML"
                   #js{:__html (.makeHtml showdown description)}}]
             (when tech
               [:p (str "Tech: " tech)])
             (when credits
               [:p credits])]])])
      [:div {:style {:padding "3em" :font-size "1em"}} "loading..."])))