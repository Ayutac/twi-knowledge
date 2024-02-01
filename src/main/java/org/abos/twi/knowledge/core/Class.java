package org.abos.twi.knowledge.core;

import org.abos.common.Named;

import java.util.Objects;

public record Class(String name, Chapter since, String wikiLink) implements Named {

    public Class(final String name, final Chapter since, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.since = since;
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }
}
