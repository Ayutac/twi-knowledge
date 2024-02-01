package org.abos.twi.knowledge.core;

import org.abos.common.Named;

import java.util.Objects;

public record Skill(String name, Chapter since, String wikiLink) implements Named {

    public Skill(final String name, final Chapter since, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.since = since;
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }
}
