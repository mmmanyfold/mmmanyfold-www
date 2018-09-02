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
  :get-profile-data
  (fn [{db :db} [_ user query]]
    ;; TODO: add loading state...
    {:db         db
     :http-xhrio {:method          :post
                  :headers         {:Authorization (str "Bearer " GRAPHQL_API_KEY)}
                  :format          (ajax/json-request-format)
                  :params          {:query query}
                  :uri             graphql-endpoint
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success      [:get-profile-data-success user]}}))

(rf/reg-event-db
  :get-profile-data-success
  (rf/path [:profiles])
  (fn [db [_ user & [{data :data}]]]
    (let [{:keys [allProjects Collaborator]} data]
      (-> db
        (assoc-in [user] (merge (db user) Collaborator))
        (assoc-in [user :projects] allProjects)))))

(rf/reg-event-db
  :set-titles
  (rf/path [:view-titles])
  (fn [db [_ new-title new-subtitle]]
      (assoc db :title new-title :subtitle new-subtitle)))

(rf/reg-event-db
  :set-css-content-class
  (fn [db [_ new-class]]
      (assoc db :content-css-class new-class)))

(rf/reg-event-fx
  :get-contentful-data
  (fn [{db :db} [_ db-key query]]
    (when-not (db-key db)
      (let [endpoint "http://localhost:8000/___graphql/"
            space-id "x84uyf33pmv4"]
        {:db         db
         :http-xhrio {:method          :post
                      :format          (ajax/json-request-format)
                      :params          {:query query}
                      :uri             (str endpoint space-id)
                      :response-format (ajax/json-response-format {:keywords? true})
                      :on-failure      [:get-contentful-data-failed]
                      :on-success      [:get-contentful-data-success db-key]}}))))

(rf/reg-event-db
  :get-contentful-data-failed
  (fn [db _]
    (js/console.error ":get-contentful-data event failed, is the GraphQL server running ?")
    db))

(rf/reg-event-db
  :get-contentful-data-success
  (fn [db [_ db-key & [{data :data}]]]
    (assoc db db-key data)))