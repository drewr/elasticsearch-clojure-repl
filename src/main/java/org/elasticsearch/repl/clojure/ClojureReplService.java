package org.elasticsearch.repl.clojure;

import org.elasticsearch.ElasticSearchException;
import org.elasticsearch.common.component.AbstractLifecycleComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.repl.clojure.Repl;

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
        Repl.repl(logger, settings.getAsMap());
    }

    @Override
    protected void doStop() throws ElasticSearchException {
    }

    @Override
    protected void doClose() throws ElasticSearchException {
    }
}
