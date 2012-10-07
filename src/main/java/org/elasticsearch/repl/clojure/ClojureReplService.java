package org.elasticsearch.repl.clojure;

import org.elasticsearch.ElasticSearchException;
import org.elasticsearch.common.component.AbstractLifecycleComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.common.settings.Settings;
import clojure.lang.RT;
import clojure.lang.Symbol;
import clojure.lang.Var;

public class ClojureReplService extends AbstractLifecycleComponent<ClojureReplService> {
    Settings settings;

    ESLogger logger;

    @Inject
    public ClojureReplService(Settings settings) {
        super(settings);
        this.settings = settings;
        this.logger = Loggers.getLogger(getClass(), settings);
    }

    @Override
    protected void doStart() throws ElasticSearchException {
        RT.var("clojure.core", "require").invoke(Symbol.intern("org.elasticsearch.repl.clojure.Repl"));
        RT.var("org.elasticsearch.repl.clojure.Repl", "repl").invoke(logger, settings.getAsMap());
    }

    @Override
    protected void doStop() throws ElasticSearchException {
    }

    @Override
    protected void doClose() throws ElasticSearchException {
    }
}
