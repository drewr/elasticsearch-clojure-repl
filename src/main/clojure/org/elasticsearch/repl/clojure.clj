(ns org.elasticsearch.repl.clojure
  (:require [clojure.tools.nrepl.server :refer [start-server]]
            [clojure.tools.nrepl.transport :as transport]))

(defonce _node (atom nil))

(defonce _logger (atom nil))

(defn info [msg & args]
  (when @_logger
    (.info @_logger msg (to-array args))))

(defn debug [msg & args]
  (when @_logger
    (.debug @_logger msg (to-array args))))

(defn trace [msg & args]
  (when @_logger
    (.trace @_logger msg (to-array args))))

(defn node []
  @_node)

(defn repl-bin [addr port]
  (info "starting nrepl/bencode on {}:{}" addr port)
  (start-server :bind addr :port port))

(defn repl-tty [addr port]
  (info "starting nrepl/tty on {}:{}" addr port)
  (start-server :bind addr :port port :transport-fn transport/tty))

(defn repl [__logger __node settings]
  (reset! _logger __logger)
  (reset! _node __node)
  (repl-tty (get settings "repl.clojure.tty.address" "127.0.0.1")
            (Integer/parseInt (get settings "repl.clojure.tty.port" "40050")))
  (repl-bin (get settings "repl.clojure.bin.address" "127.0.0.1")
            (Integer/parseInt (get settings "repl.clojure.bin.port" "40051"))))
