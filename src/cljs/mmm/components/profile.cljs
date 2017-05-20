(ns mmm.components.profile)

(defn project [href]
  [:div.fl.w-50.w-25-m.w-20-l.pa2
   [:a.db.link.dim.tc {:href href}
    [:img {:src "http://is4.mzstatic.com/image/thumb/Music62/v4/93/8f/75/938f7536-0188-f9ba-4585-0a77ceaebf0a/source/400x40000bb.png", :alt "Frank Ocean Blonde Album Cover", :class "w-100 db outline black-10"}]
    [:dl.mt2.f6.lh-copy.bg-purple
     [:dt.clip "Title"]
     [:dd.ml0.black.truncate.w-100.bg-light-green "Blonde"]
     [:dt.clip "Artist"]
     [:dd.ml0.gray.truncate.w-100.bg-light-green "Frank Ocean"]]]])

(defn component []
  [:div.profile
   [:h2.f3.fw4.pa3.mv0 "Projects"]
   [:div.cf.pa2
    [project ""]]])