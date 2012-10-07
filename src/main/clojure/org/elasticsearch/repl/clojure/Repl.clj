(ns org.elasticsearch.repl.clojure.Repl
  (:require [clojure.tools.nrepl.server :refer [start-server]]
            [clojure.tools.nrepl.transport :as transport])
  (:import (org.elasticsearch.common.logging.ESLogger))
  (:gen-class :methods [#^{:static true}
                        [repl [org.elasticsearch.common.logging.ESLogger
                               java.util.Map] void]]))

(def logger (atom nil))

(defn info [msg & args]
  (when @logger
    (.info @logger msg (to-array args))))

(defn debug [msg & args]
  (when @logger
    (.debug @logger msg (to-array args))))

(defn trace [msg & args]
  (when @logger
    (.trace @logger msg (to-array args))))

(defn repl-bin [addr port]
  (info "starting nrepl/bencode on {}:{}" addr port)
  (start-server :bind addr :port port))

(defn repl-tty [addr port]
  (info "starting nrepl/tty on {}:{}" addr port)
  (start-server :bind addr :port port :transport-fn transport/tty))

(defn -repl [logger_ settings]
  (reset! logger logger_)
  (repl-tty (get settings "repl.clojure.tty.address" "127.0.0.1")
            (Integer/parseInt (get settings "repl.clojure.tty.port" "40050")))
  (repl-bin (get settings "repl.clojure.bin.address" "127.0.0.1")
            (Integer/parseInt (get settings "repl.clojure.bin.port" "40051"))))
