(ns mmm.views.davm
  (:require [goog.string :refer [format]]
            [mmm.components.profile :as profile]
            [re-frame.core :as rf]))

(defonce query "
  {
    allProjects {
      id imageUrl title description url
    }
    Collaborator(initials: \"davm\") {
      github, name, twitter
    }
  }")

(defn profile-query [intials]
  (format
    "query {
      Collaborator(initials: \"%s\") {
        github, name, twitter
      }
    }"
    intials))

(defn davm-view []
  (rf/dispatch [:set-titles "David A. Viramontes Martinez"
                            "Advanced Beginner / Clojure(Script) & JavaScript Programmer"])
  (rf/dispatch [:get-profile-data :davm query])
  (let [profile-list (rf/subscribe [:project-list])]
    (fn []
      [profile/component :davm @profile-list])))