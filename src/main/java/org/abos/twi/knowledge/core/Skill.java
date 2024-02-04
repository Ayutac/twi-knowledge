package org.abos.twi.knowledge.core;

import org.abos.common.Named;

import java.util.Objects;

public record Skill(String name, boolean spell, String wikiLink) implements Named {

    public static final String BASIC_CLEANING = "Basic Cleaning";

    public static final String BASIC_COOKING = "Basic Cooking";

    public static final String BASIC_CRAFTING = "Basic Crafting";

    public static final String TELEPORTATION = "Teleportation";

    public Skill(final String name, final boolean spell, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.spell = spell;
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }
}
