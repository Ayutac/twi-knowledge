package org.abos.twi.knowledge.core.event;

import org.abos.common.Named;

import java.util.Objects;

public record Solstice(String name, String wikiLink) implements Named {

    public Solstice(final String name, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }
}
