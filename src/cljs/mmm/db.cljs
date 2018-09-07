(ns mmm.db)

(def default-db
  {:active-view       nil
   :project-in-view   nil
   :view-titles       {:title    "mmmanyfold"
                       :subtitle "web + app dev studio"}
   :content-css-class "default-content"
   :projects          nil
   :assets            nil
   :profiles          {:davm {:github   nil
                              :twitter  nil
                              :name     nil
                              :projects []}
                       :elle {:github   nil
                              :twitter  nil
                              :name     nil
                              :projects []}}})

