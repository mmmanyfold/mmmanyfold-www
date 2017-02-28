(ns mmm.views.about)

(defn about-view []
  [:div.text-box
    [:h3 "mmmanyfold builds websites, apps, and learning experiences"]
    [:p "We believe in amplifying "
      [:a {:href "https://www.ted.com/talks/alan_kay_shares_a_powerful_idea_about_ideas?language=en"}
        [:span.italic "powerful ideas"]] " through software."]
    [:p [:b "mmmanyfold"] " embraces forward-looking technology that the current tech industry shies away from."]
    [:p [:b "We choose sanity over status quo. "] "We choose languages, frameworks and processes that give us and our clients more power, freedom and flexibility."]
    [:p [:b "mmmanyfold"] " takes inspiration from self-organizing systems such as the blockchain, #blacklivesmatter, microservices & distributed systems, functional programming, the Fluxus movement, etc."]])
