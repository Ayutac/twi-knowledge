package org.abos.twi.knowledge.core;

import org.abos.common.Named;

import java.util.Objects;

public record Skill(String name, boolean spell, String wikiLink) implements Named, Comparable<Skill> {

    public static final Skill BAD_FRUIT_DETECTOR = new Skill("Bad Fruit Detector", false, null);
    public static final Skill BASIC_CLEANING = new Skill("Basic Cleaning", false, null);
    public static final Skill BASIC_COOKING = new Skill("Basic Cooking", false, null);
    public static final Skill BASIC_CRAFTING = new Skill("Basic Crafting", false, null);
    public static final Skill BROADER_SHOULDERS = new Skill("Broader Shoulders", false, null);
    public static final Skill DANGERSENSE = new Skill("Dangersense", false, null);
    public static final Skill DETECT_GUILT = new Skill("Detect Guilt", false, null);
    public static final Skill DETECT_POISON = new Skill("Detect Poison", false, null);
    public static final Skill IRON_SCALES = new Skill("Iron Scales", false, null);
    public static final Skill THICK_SCALES = new Skill("Thick Scales", false, null);

    public static final Skill APPRAISAL = new Skill("Appraisal", true, null);
    public static final Skill BARRIER_OF_AIR = new Skill("Barrier of Air", true, null);
    public static final Skill DETECT_TRUTH = new Skill("Detect Truth", false, null);
    public static final Skill FIREBALL = new Skill("Fireball", true, null);
    public static final Skill FLASH_STEP = new Skill("Flash Step", true, null);
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

    @Override
    public int compareTo(final Skill other) {
        return name.compareTo(other.name);
    }
}
