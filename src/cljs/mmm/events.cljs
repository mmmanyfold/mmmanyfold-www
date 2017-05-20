(ns mmm.events
  (:require-macros [adzerk.env :as env])
  (:require [re-frame.core :as rf]
            [ajax.core :as ajax :refer [POST]]
            [day8.re-frame.http-fx]
            [mmm.db :as db]))

(env/def GRAPHQL_API_KEY "MMM_WWW_GRAPHQL_COOL_API_KEY")

(defonce graphql-endpoint "https://api.graph.cool/simple/v1/cj2wiig1p3rcq0154lhr2nbk5")

(rf/reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))

(rf/reg-event-db
  :set-active-view
  (fn [db [_ active-view]]
    (assoc db :active-view active-view)))

(rf/reg-event-fx
  :get-project-data
  (fn [{db :db} [_ query]]
    ;; TODO: add loading state...
    {:db         db
     :http-xhrio {:method          :post
                  :headers         {:Authorization (str "Bearer " GRAPHQL_API_KEY)}
                  :format          (ajax/json-request-format)
                  :params          {:query query}
                  :uri             graphql-endpoint
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success      [:get-project-data-success]}}))

(rf/reg-event-db
  :get-project-data-success
  (fn [db [_ {data :data}]]
    (let [projects (:allProjects data)]
      (assoc-in db [:profiles :davm :projects] projects))))