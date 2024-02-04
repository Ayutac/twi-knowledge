package org.abos.twi.knowledge.core;

import org.abos.common.Named;

import java.util.Objects;

public record Skill(String name, boolean spell, String wikiLink) implements Named {

    public static final Skill BASIC_CLEANING = new Skill("Basic Cleaning", false, null);
    public static final Skill BASIC_COOKING = new Skill("Basic Cooking", false, null);
    public static final Skill BASIC_CRAFTING = new Skill("Basic Crafting", false, null);

    public static final Skill TELEPORTATION = new Skill("Teleportation", true, null);

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
