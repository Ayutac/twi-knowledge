package org.abos.twi.knowledge.core;

import org.abos.common.Named;

/**
 * RsK is short for Relationship Kind
 */
public record Rsk(String name) implements Named {

    @Override
    public String getName() {
        return name;
    }
}
