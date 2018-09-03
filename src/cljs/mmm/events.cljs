(ns mmm.events
  (:require-macros [mmm-clj.env :refer [get-env]])
  (:require [re-frame.core :as rf]
            [ajax.core :as ajax :refer [POST]]
            [day8.re-frame.http-fx]
            [mmm.db :as db]))

(rf/reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))

(rf/reg-event-db
  :set-active-view
  (fn [db [_ active-view]]
    (assoc db :active-view active-view)))

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
  (fn [{db :db} [_ db-key]]
    (when-not (db-key db)
      (let [endpoint "https://cdn.contentful.com"
            space-id (get-env :mmm-contentful-server-space-id)
            access-token (get-env :mmm-contentful-server-access-token)]
        {:db         db
         :http-xhrio {:method          :get
                      :headers         {:Authorization (str "Bearer " access-token)}
                      :format          (ajax/json-request-format)
                      :params          {:access_token access-token}
                      :uri             (str endpoint "/spaces/" space-id "/environments/master/entries")
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
  (fn [db [_ db-key & [{items :items}]]]
    (assoc db db-key items)))