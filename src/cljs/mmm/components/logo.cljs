(ns mmm.components.logo)

;; TweenLite is loaded via a script tag on index.html
;; assumed

(defn create-dot! [rotation element dot-size radius colors]
  (let [dot (js/document.createElement "div")]
    (.appendChild element dot)
    (.set js/TweenLite
          dot
          (clj->js {:width           dot-size
                    :height          dot-size
                    :transformOrigin (str (* -1 radius) "px 0px")
                    :x               radius
                    :backgroundColor (last colors)
                    :borderRadius    "50%"
                    :force3D         true
                    :position        "absolute"
                    :rotation        rotation}))
    (set! (.-className dot) "preloader-dot")
    dot))

(defn GSAnimationSeq
  "inits green sock animation sequence for logo animation"
  [& [opts]]

  (let [parent (js/document.getElementById "mmm")
        element (js/document.createElement "div")
        radius 42
        dot-size 15
        animation-offset ()
        dot-count 10
        colors #js ["#61AC27", "black"]
        animation (js/TimelineLite. #js {:paused true})
        box (js/document.createElement "div")]

    ;; TODO: find a more clojure idomatic way to do this
    ;(.push colors (.shift colors))

    ;; should see element on screen with this call
    (js/parent.appendChild element)

    (js/TweenLite.set element (clj->js {:position    "fixed"
                                        :top         "45%"
                                        :left        "50%"
                                        :perspective 600
                                        :overflow    "visible"
                                        :zIndex      2000}))
    (.from animation box 0.1
           (clj->js {:opacity 0
                     :scale   0.1
                     :ease    (aget js/Power1 "easeOut")}) animation-offset)

    (dotimes [i dot-count]
      (let [rotation-increment (/ 360 i)
            dot (create-dot!
                  (* i rotation-increment)
                  element
                  dot-size
                  radius
                  colors)]
        (.from animation dot 0.1
               (clj->js {:scale   0.1
                         :opacity 0
                         :ease    (aget js/Power1 "easeOut")})
               animation-offset)
        (let [timeline (js/TimelineMax. #js {:repeat -1 :repeatDelay 0.25})]
          (dotimes [j (count colors)]
            (-> timeline
                (.to dot 2.5
                     (clj->js {:rotation "-=360"
                               :ease     (aget js/Power2 "easeInOut")})
                     (* 2.9 j))
                (.to dot 1.2
                     (clj->js {:skewX           "+=360"
                               :backgroundColor (get colors j)
                               :ease            (aget js/Power2 "easeInOut")})
                     (* 2.9 j))))
          (.add animation timeline (* i 0.07)))))
    ;(when (.-render js/TweenLite)
    ;  (js/TweenLite.render))
    (js/TweenLite.render)
    ;(set! (-> element .-style .-visibility) "visible")
    (js/TweenLite.set #js [element, box] #js {:rotation 0})
    (.play animation animation-offset)))