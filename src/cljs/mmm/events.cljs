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
  (fn [db [_ active-view & [project-name]]]
    (if project-name
      (assoc db :active-view active-view
                :project-in-view project-name)
      (assoc db :active-view active-view))))

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
  :get-contentful-entries
  (fn [{db :db} [_ db-key]]
    (when-not (db-key db)
      (let [endpoint "https://cdn.contentful.com"
            space-id (get-env :mmm-contentful-server-space-id)
            access-token (get-env :mmm-contentful-server-access-token)]
        {:db         db
         :dispatch   [:get-contentful-assets :assets]
         :http-xhrio {:method          :get
                      :headers         {:Authorization (str "Bearer " access-token)}
                      :format          (ajax/json-request-format)
                      :params          {:access_token access-token}
                      :uri             (str endpoint "/spaces/" space-id "/environments/master/entries?include=2")
                      :response-format (ajax/json-response-format {:keywords? true})
                      :on-failure      [:get-contentful-entries-failed]
                      :on-success      [:get-contentful-entries-success db-key]}}))))

(rf/reg-event-db
  :get-contentful-entries-failed
  (fn [db _]
    (js/console.error ":get-contentful-entries event failed")
    db))

(rf/reg-event-db
  :get-contentful-entries-success
  (fn [db [_ db-key & [{items :items}]]]
    (assoc db db-key items)))

(rf/reg-event-fx
  :get-contentful-assets
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
                      :uri             (str endpoint "/spaces/" space-id "/environments/master/assets")
                      :response-format (ajax/json-response-format {:keywords? true})
                      :on-failure      [:get-contentful-assets-failed]
                      :on-success      [:get-contentful-assets-success db-key]}}))))

(rf/reg-event-db
  :get-contentful-assets-failed
  (fn [db _]
    (js/console.error ":get-contentful-assets event failed")
    db))

(rf/reg-event-db
  :get-contentful-assets-success
  (fn [db [_ db-key & [{items :items}]]]
    (assoc db db-key items)))