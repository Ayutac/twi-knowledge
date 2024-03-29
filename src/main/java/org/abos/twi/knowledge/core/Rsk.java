package org.abos.twi.knowledge.core;

import org.abos.common.Named;

import java.util.Objects;

/**
 * RsK is short for Relationship Kind
 */
public record Rsk(String name) implements Named {

    public Rsk(final String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public String getName() {
        return name;
    }
}
