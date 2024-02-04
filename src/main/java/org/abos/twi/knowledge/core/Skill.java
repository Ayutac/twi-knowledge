package org.abos.twi.knowledge.core;

import org.abos.common.Named;
import org.abos.twi.knowledge.core.publication.Chapter;

import java.util.Objects;

public record Skill(String name, String wikiLink) implements Named {

    public static final String BASIC_CLEANING = "Basic Cleaning";

    public static final String BASIC_COOKING = "Basic Cooking";

    public Skill(final String name, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }
}
