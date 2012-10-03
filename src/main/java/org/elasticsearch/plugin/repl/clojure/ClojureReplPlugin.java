package org.elasticsearch.plugin.repl.clojure;

import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.component.LifecycleComponent;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.AbstractPlugin;
import org.elasticsearch.repl.clojure.ClojureReplModule;
import org.elasticsearch.repl.clojure.ClojureReplService;

import java.util.Collection;

public class ClojureReplPlugin extends AbstractPlugin {

    private final Settings settings;

    public ClojureReplPlugin(Settings settings) {
        this.settings = settings;
    }

    @Override
    public String name() {
        return "scalpel";
    }

    @Override
    public String description() {
        return "Clojure REPL plugin";
    }

    @Override
    public Collection<Class<? extends Module>> modules() {
        Collection<Class<? extends Module>> modules = Lists.newArrayList();
        if (settings.getAsBoolean("repl.clojure", true)) {
            modules.add(ClojureReplModule.class);
        }
        return modules;
    }

    @Override
    public Collection<Class<? extends LifecycleComponent>> services() {
        Collection<Class<? extends LifecycleComponent>> services = Lists.newArrayList();
        if (settings.getAsBoolean("repl.clojure", true)) {
            services.add(ClojureReplService.class);
        }
        return services;
    }

}
