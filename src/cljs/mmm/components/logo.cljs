(ns mmm.components.logo)

;; TweenLite is loaded via a script tag on index.html
;; assumed

(defonce colors #js ["#61AC27" "#555" "purple" "#FF6600"])

(defn create-dot!

  "create a dot and return it"

  [rotation element dot-size radius]

  (let [dot (js/document.createElement "div")]

    (.appendChild element dot)

    (js/TweenLite.set
      dot
      (clj->js {:width           dot-size
                :height          dot-size
                :transformOrigin (str (* -2 radius) "px 0px")
                :x               radius
                :backgroundColor "red"
                :borderRadius    "50%"
                :force3D         true
                :position        "absolute"
                :rotation        rotation}))

    dot))

(defn GSAnimationSeq

  "inits green sock animation sequence for logo animation"

  []

  (let [parent (js/document.getElementById "mmm")
        element (js/document.createElement "div")
        box (js/document.createElement "div")
        radius 45
        dot-size 20
        animation-offset 1.8
        dot-count 15
        animation (js/TimelineLite. #js {:paused false})]

    ;; TODO: find a more clojure idomatic way to do this
    ;(.push colors (.shift colors))

    (js/parent.appendChild element)

    (js/TweenLite.set element (clj->js {:position    "fixed"
                                        :top         "45%"
                                        :left        "50%"
                                        :perspective 600
                                        :overflow    "visible"
                                        :zIndex      2000}))
    (js/animation.from box
                       0.1
                       (clj->js {:opacity 0
                                 :scale   0.1
                                 :ease    (aget js/Power1 "easeOut")})
                       animation-offset)

    (dotimes [i dot-count]

      (let [rotation-increment (/ 180 i)
            dot (create-dot!
                  (* i rotation-increment)
                  element
                  dot-size
                  radius)]

        (js/animation.from dot
                           0.1
                           (clj->js {:scale   0.1
                                     :opacity 0
                                     :ease    (aget js/Power1 "easeOut")})
                           animation-offset)

        (let [timeline (js/TimelineMax. #js {:repeat      1
                                             :repeatDelay 0.25})]


          (dotimes [j 10]

            (-> timeline
                (.to dot 2.5
                     (clj->js {:rotation "-=360"
                               :ease     (aget js/Power2 "easeInOut")})
                     (* 2.9 j))
                (.to dot 1.2
                     (clj->js {:skewX           "+=360"
                               :backgroundColor (get colors j)
                               :ease            (aget js/Power2 "easeInOut")})
                     (+ 1.6 (* 2.9 j)))))

          (js/animation.add timeline (* i 0.7)))))

    (when-not (nil? (.-render js/TweenLite))
      (js/TweenLite.render))

    (js/TweenLite.set #js [element box]
                      #js {:rotation 0})
    (js/animation.play animation-offset)))
