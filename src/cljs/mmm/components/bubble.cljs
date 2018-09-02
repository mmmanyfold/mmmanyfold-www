(ns mmm.components.bubble)

(def colors {:red "#EB5757"
             :green "#6FCF97"
             :blue "#56CCF2"
             :purple "#BB6BD9"
             :yellow "#F2C94C"})

(defn bubble [color-key text]
  (let [color-val (color-key colors)]
    [:div.bubble {:style {:border-color color-val
                          :background-color color-val}}
     [:div.text text]]))