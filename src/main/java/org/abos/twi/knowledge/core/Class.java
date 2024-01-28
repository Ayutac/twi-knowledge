package org.abos.twi.knowledge.core;

import java.util.Objects;

public record Class(String name, Chapter since) {

    public Class(final String name, final Chapter since) {
        this.name = Objects.requireNonNull(name);
        this.since = since;
    }
}
