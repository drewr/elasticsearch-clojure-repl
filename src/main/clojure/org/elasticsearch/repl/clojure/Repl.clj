(ns org.elasticsearch.repl.clojure.Repl
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

(defn repl [port addr]
  (debug "starting repl on {}:{}" port addr))

(defn -repl [logger_ settings]
  (reset! logger logger_)
  (repl (get settings "repl.clojure.address" "127.0.0.1")
        (get settings "repl.clojure.port" "40050")))
