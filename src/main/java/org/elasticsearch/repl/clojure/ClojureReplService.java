package org.elasticsearch.repl.clojure;

import clojure.lang.RT;
import clojure.lang.Symbol;
import clojure.lang.Var;
import org.elasticsearch.ElasticSearchException;
import org.elasticsearch.common.component.AbstractLifecycleComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;

public class ClojureReplService extends AbstractLifecycleComponent<ClojureReplService> {
    private final Node node;

    @Inject
    public ClojureReplService(Settings settings, Node node) {
        super(settings);
        this.node = node;
    }

    @Override
    protected void doStart() throws ElasticSearchException {
        RT.var("clojure.core", "require").invoke(Symbol.intern("org.elasticsearch.repl.clojure"));
        RT.var("org.elasticsearch.repl.clojure", "repl")
                    .invoke(logger, node, settings.getAsMap());
    }

    @Override
    protected void doStop() throws ElasticSearchException {
    }

    @Override
    protected void doClose() throws ElasticSearchException {
    }
}
