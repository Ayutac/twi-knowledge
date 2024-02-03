package org.abos.twi.knowledge.core;

import org.abos.common.Named;

import java.util.Objects;

public record Status(String name) implements Named {

    public static final String ALIVE = "Alive";

    public Status(final String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public String getName() {
        return name;
    }
}
