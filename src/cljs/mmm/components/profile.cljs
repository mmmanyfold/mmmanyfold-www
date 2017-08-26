(ns mmm.components.profile)

(defn external [{:keys [title imageUrl description url]}]
  [:div.fl.w-50.w-25-m.w-20-l.pa2
   [:a.db.link.dim.tc.rabbit {:href url :target "_blank"}
    [:img.w-100.db.outline.black-10 {:src imageUrl}]
    [:dl.mt2.f6.lh-copy.bg-purple
     [:dt.clip "title"]
     [:dd.ml0.black.truncate.w-100.bg-light-green title]
     [:dt.clip "desc"]
     [:dd.ml0.gray.truncate.w-100.bg-light-green description]]]])

(defn embed [{:keys [title imageUrl description url]}]
  [:div.fl.w-50.w-25-m.w-20-l.pa2
   [:a.db.link.dim.tc.rabbit {:href url :target "_blank"}
    [:iframe.embed {:src url :frame-border 0}]
    [:dl.mt2.f6.lh-copy.bg-purple
     [:dt.clip "title"]
     [:dd.ml0.black.truncate.w-100.bg-light-green title]
     [:dt.clip "desc"]
     [:dd.ml0.gray.truncate.w-100.bg-light-green description]]]])

(defn project [details]
      (case (:type details)
            "embed" [embed details]
            "external" [external details]
            :default [external details]))

(defn component [user project-list]
  [:div.profile
   [:h2.f3.fw4.pa3.mv0 "Projects"]
   [:div.cf.pa2
    (for [details project-list]
      ^{:key (:id details)} [project details])]])