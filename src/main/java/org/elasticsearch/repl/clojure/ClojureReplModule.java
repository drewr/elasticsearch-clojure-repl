package org.elasticsearch.repl.clojure;

import org.elasticsearch.common.inject.AbstractModule;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.common.settings.Settings;

public class ClojureReplModule extends AbstractModule {
    Settings settings;

    ESLogger logger;

    @Inject
    public ClojureReplModule(Settings settings) {
        this.settings = settings;
        this.logger = Loggers.getLogger(getClass(), settings);
    }

    @Override
    public void configure() {
        logger.trace("completed module configuration");
    }
}
