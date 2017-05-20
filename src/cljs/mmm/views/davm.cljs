(ns mmm.views.davm
  (:require [mmm.components.profile :as profile]
            [re-frame.core :as rf]))

(defonce query "
  {
    allProjects {
      id imageUrl title description
    }
  }")

(defn davm-view []
  (rf/dispatch [:get-project-data query])
  (let [profile-list (rf/subscribe [:project-list])]
   (fn []
     [profile/component :davm @profile-list])))
