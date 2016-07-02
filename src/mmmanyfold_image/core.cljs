(ns mmmanyfold-image.core
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ; setup function returns initial state. It contains
  ; circle color and position.
  {:color  0
   :angle  0
   :chance 2})

(defn update-state [state]
  ; Update sketch state by changing circle color and position.
  {:color  (mod (+ (:color state) 0.27) 255)
   :angle  (+ (:angle state) 0.01)
   :chance (+ (:chance state) 0.01)})

(defn draw-state [state]
  ; Clear the sketch by filling it with light-grey color.
  (q/background 255)
  ; Set circle color.
  (q/fill (:color state) 255 255 100)
  ; disable stroke
  (q/no-stroke)
  ; Calculate x and y coordinates of the circle.
  (let [angle (:angle state)
        x (* 25 (q/cos angle))
        y (* 150 (q/sin angle))]
    (dotimes [n 10]
      (q/with-translation [(/ (q/width) 3)
                           (/ (q/height) 3)]
                          ; Draw the circle.
                          ; Move origin point to the center of the sketch.
                          (q/with-rotation [(* 120 n)]
                                           (q/ellipse x y 100 100))))))



(q/defsketch mmmanyfold-image
             :host "mmmanyfold-image"
             :size [500, 500]
             ; setup function called only once, during sketch initialization.
             :setup setup
             ; update-state is called on each iteration before draw-state.
             :update update-state
             :draw draw-state
             ; This sketch uses functional-mode middleware.
             ; Check quil wiki for more info about middlewares and particularly
             ; fun-mode.
             :middleware [m/fun-mode])
