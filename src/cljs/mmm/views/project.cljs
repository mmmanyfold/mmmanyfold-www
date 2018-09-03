(ns mmm.views.project
  (:require [re-frame.core :as rf]
            [cljsjs.showdown]))

(def showdown (js/showdown.Converter.))

(def project-titles ["Salter House"
                     "Equalizer Demo"
                     "Playground Coffee Shop"
                     "Picture Room"
                     "Video Looper"
                     "Closet of Souls"
                     "Letter Racer"
                     "This Machine Has a Soul"
                     "Owlet"
                     "SMS Onboarding"
                     "Remember the Memories"
                     "Sporting Life"])

(def images ["//images.ctfassets.net/x84uyf33pmv4/3wVDq47yI0OsM2kM6u06OA/d7372723f8a22d5e3b585bcd26eb1369/salter-house.png?w=500&fl=progressive&q=50"
             "//images.ctfassets.net/x84uyf33pmv4/N1F2TU3scw2gG08CoCcye/155861cf756af2bb0321785364428eef/equalizer.jpg?w=500&fl=progressive&q=50"
             "//images.ctfassets.net/x84uyf33pmv4/1VwYzsGEl6kKeCkmuqcUCA/8a21f77721283f2097d992f9f20b13d5/playground.gif?w=500&fl=progressive&q=50"
             "//images.ctfassets.net/x84uyf33pmv4/3TDU5LRkcgCSKy4MOgGgeg/13305714ef4377a06980150d82416b9e/picture-room.png?w=500&fl=progressive&q=50"
             "//images.ctfassets.net/x84uyf33pmv4/6q5NvdSV5SAS8uEuIsGgiq/fe3b40c41cf5e66fcd2562a46bfbba7a/video-looper.jpg?w=500&fl=progressive&q=50"
             "//images.ctfassets.net/x84uyf33pmv4/1QV4naSpUUW2myEowK0E8M/52239228f8a3463151fb434fe264f256/closet-of-souls.jpg?w=500&fl=progressive&q=50"
             "//images.ctfassets.net/x84uyf33pmv4/3mLFnbdpdSeC8eIAyGeuU8/2a3e14fc20f04dd27812c5313fe0458e/letter-racer.png?w=500&fl=progressive&q=50"
             "//images.ctfassets.net/x84uyf33pmv4/2V83jUvyiAu0mqW60gGqYU/d351437768b9d71c0ce76cbd2c4dd4c5/tmhas.png?w=500&fl=progressive&q=50"
             "//images.ctfassets.net/x84uyf33pmv4/6won1y1DJCQKyoCCO0e8cG/9bf731655011f7375c15b7ce2ade62b7/owlet.png?w=500&fl=progressive&q=50"
             "//images.ctfassets.net/x84uyf33pmv4/2YhXEeEXPq4AgMcuEccQ2s/a350240674c04cfd3ce785478d76a363/copa.gif?w=500&fl=progressive&q=50"
             "//images.ctfassets.net/x84uyf33pmv4/2uaxUB5n3Oyc622IgGUI6M/1607cc671899a4ffc0a5aa320b4f03a1/tony-seltzer.gif?w=500&fl=progressive&q=50"
             "//images.ctfassets.net/x84uyf33pmv4/2d9Kt8YaFK4AKGgmUeU2WC/e6e6e2f0bdaddb33c882a57db5671eec/sporting-life.gif?w=500&fl=progressive&q=50"])

(defn project-view []
  (let [db-key :projects]
    (rf/reg-sub db-key #(db-key %))
    (rf/dispatch [:get-contentful-data db-key])
    (if-let [projects (map #(:fields %) @(rf/subscribe [db-key]))]
      [:div {:style {:padding "0 2em 3em 2em"}}
       [:h1.title "mmmanyfold dev studio"]
       [:h1 "We're building a new website! In the meantime, here are some of our projects (in no particular order). Contact: hello@mmmanyfold.com"]
       (for [project projects
             :let [{:keys [title description tech credits]} project
                   n (.indexOf project-titles title)
                   img (nth images n)]]
         ^{:key (gensym)}
         [:div.project-view-wrap
          [:div.project-view-image
           [:div.img-bubble {:style {:width "100%"
                                     :height "100%"
                                     :background-size "100%"
                                     :border-radius "50%"
                                     :border-width "3px"
                                     :border-color "#b3cde3"
                                     :background-image (str "url('" img "')")}}]]
          [:div.project-view-details
           [:h1 title]
           [:h2 {"dangerouslySetInnerHTML"
                 #js{:__html (.makeHtml showdown description)}}]
           (when tech
             [:p (str "Tech: " tech)])
           (when credits
             [:p credits])]])])))