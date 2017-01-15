(ns mmm.views.about)

(defn about-view []
  [:div.text-box
    [:p "is a UX design / development studio by " [:b "Michelle Lim"] " + " [:b "David A. Viramontes-Martinez"]]
    [:h3 "mmmanyfold builds websites, apps, and learning experiences"]
    [:p "We believe in amplifying "
      [:a {:href "https://www.ted.com/talks/alan_kay_shares_a_powerful_idea_about_ideas?language=en"}
        [:span.italic "powerful ideas"]] " through software."]
    [:p [:b "mmmanyfold"] " takes inspiration from self-organizing systems such as the block chain, #blacklivesmatter, microservices & distributed systems, functional programming, the Fluxus movement, etc."]])
