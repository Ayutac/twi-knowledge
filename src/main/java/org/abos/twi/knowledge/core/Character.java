package org.abos.twi.knowledge.core;

import org.abos.common.Named;

import java.util.Objects;

public record Character(String wikiLink) implements Named {

    public Character(final String wikiLink) {
        this.wikiLink = Objects.requireNonNull(wikiLink);
    }

    @Override
    public String getName() {
        final int index = wikiLink.substring(0, wikiLink.length()-1).lastIndexOf('/');
        if (index == -1) {
            return wikiLink.replace('_', ' ').replace("/", "");
        }
        return wikiLink.substring(index+1).replace('_', ' ').replace("/", "");
    }
}
