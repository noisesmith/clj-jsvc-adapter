(ns org.noisesmith.clj-jsvc-demo)

(defn init
  [context-object]
  (println ::init))

(defn start
  []
  (println ::start))

(defn stop
  []
  (println ::stop)
  (shutdown-agents))

(defn destroy
  []
  (println ::destroy))
