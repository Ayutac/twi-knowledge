package org.abos.twi.knowledge.core;

import java.util.Objects;

public record Skill(String name, Chapter since) {

    public Skill(final String name, final Chapter since) {
        this.name = Objects.requireNonNull(name);
        this.since = since;
    }
}
