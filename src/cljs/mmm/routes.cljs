(ns mmm.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:require [secretary.core :as secretary]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [re-frame.core :as rf])
  (:import goog.history.Html5History
           goog.Uri))

(defn hook-browser-navigation! []
  (let [history (doto (Html5History.)
                  (events/listen
                    EventType/NAVIGATE
                    (fn [event]
                      (secretary/dispatch! (.-token event))))
                  (.setUseFragment false)
                  (.setPathPrefix "")
                  (.setEnabled true))]
    (events/listen js/document "click"
                   (fn [e]
                     (let [path (.getPath (.parse Uri (.-href (.-target e))))
                           title (.-title (.-target e))]
                       (when (= "rabbit rainbow" (.-className (.-target e)))
                         (js/window.open (.-href (.-target e)) "_blank"))
                       (when (secretary/locate-route path)
                         (. history (setToken path title))))))))


(defn app-routes []

  ;; --------------------
  ;; define routes here

  (defroute "/" []
            (rf/dispatch [:set-active-view :home-view]))

  (defroute "/contact" []
            (rf/dispatch [:set-active-view :contact-view]))

  (defroute "/david" []
            (rf/dispatch [:set-active-view :davm-view]))

  ;; --------------------
  (hook-browser-navigation!))
