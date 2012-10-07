(ns org.elasticsearch.repl.clojure.Repl
  (:require [clojure.tools.nrepl.server :refer [start-server]])
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

(defn nrepl [addr port]
  (info "starting nrepl on {}:{}" addr port)
  (start-server :bind addr :port port))

(defn -repl [logger_ settings]
  (reset! logger logger_)
  (nrepl (get settings "repl.clojure.nrepl.address" "127.0.0.1")
         (Integer/parseInt (get settings "repl.clojure.nrepl.port" "40050"))))
