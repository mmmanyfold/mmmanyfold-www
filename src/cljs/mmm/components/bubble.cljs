(ns mmm.components.bubble)

(defn bubble [color cover label]
  [:div.bubble-wrap-outer
   [:div.bubble-wrap-inner
    [:div.bubble.img-bubble {:style {:border-color color}}
     [:div {:style {:background-image (str "url('" cover "')")}}]]
    [:div.bubble.color-bubble {:style {:background-color color}}]
    [:div.bubble.label-bubble label]]])