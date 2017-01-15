(ns mmm.views.contact)

(defn contact-view []
  [:div.text-box
    "Say " [:a {:href "mailto:hello@mmmanyfold.com"} "hello@mmmanyfold.com"]])
