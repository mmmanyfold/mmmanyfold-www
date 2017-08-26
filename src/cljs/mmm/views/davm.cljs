(ns mmm.views.davm
  (:require [goog.string :refer [format]]
            [mmm.components.profile :as profile]
            [re-frame.core :as rf]))

(def query "
  {
    allProjects {
      id imageUrl title description url type content
    }
    Collaborator(initials: \"davm\") {
      github, name, twitter
    }
  }")

(defn profile-query [initials]
  (format
    "query {
      Collaborator(initials: \"%s\") {
        github, name, twitter
      }
    }"
    initials))

(defn davm-view []
  (rf/dispatch [:set-css-content-class "davm-content"])
  (rf/dispatch [:set-titles "David A. Viramontes Martinez"
                            "Advanced Beginner / Clojure(Script) & JavaScript Programmer"])
  (rf/dispatch [:get-profile-data :davm query])
  (let [profile-list (rf/subscribe [:project-list])]
    (fn []
      [profile/component :davm @profile-list])))