package org.abos.twi.knowledge.core;

import org.abos.common.Named;

import java.util.Objects;

public record Skill(String name, boolean spell, String wikiLink) implements Named, Comparable<Skill> {

    public static final Skill AN_APPLE_A_DAY = new Skill("An Apple A Day", false, null);
    public static final Skill BAD_FRUIT_DETECTOR = new Skill("Bad Fruit Detector", false, null);
    public static final Skill BASIC_CLEANING = new Skill("Basic Cleaning", false, null);
    public static final Skill BASIC_COOKING = new Skill("Basic Cooking", false, null);
    public static final Skill BASIC_CRAFTING = new Skill("Basic Crafting", false, null);
    public static final Skill BENEDICTION_OF_HOPE = new Skill("Benediction of Hope", false, null);
    public static final Skill BROADER_SHOULDERS = new Skill("Broader Shoulders", false, null);
    public static final Skill COMPARTMENTS_OF_HOLDING = new Skill("Compartments of Holding", false, null);
    public static final Skill CONCEAL_REPUTATION = new Skill("Conceal Reputation", false, null);
    public static final Skill DANGERSENSE = new Skill("Dangersense", false, null);
    public static final Skill DETECT_GUILT = new Skill("Detect Guilt", false, null);
    public static final Skill DETECT_POISON = new Skill("Detect Poison", false, null);
    public static final Skill DISABLE_FRIENDLY_FIRE = new Skill("Disable Friendly Fire", false, null);
    public static final Skill DOOR_OF_PORTALS = new Skill("Door of Portals", false, null);
    public static final Skill FAST_SCRAWL = new Skill("Fast Scrawl", false, null);
    public static final Skill FLAWLESS_ATTEMPT = new Skill("Flawless Attempt", false, null);
    public static final Skill FLURRY_OF_MOTION = new Skill("Flurry of Motion", false, null);
    public static final Skill GARDEN_OF_SANCTUARY = new Skill("Garden of Sanctuary", false, null);
    public static final Skill IMMORTAL_MOMENT = new Skill("Immortal Moment", false, null);
    public static final Skill IRON_SCALES = new Skill("Iron Scales", false, null);
    public static final Skill LIKE_FIRE_MEMORY = new Skill("Like Fire, Memory", false, null);
    public static final Skill PARTIAL_RECONSTRUCTION = new Skill("Partial Reconstruction", false, null);
    public static final Skill PERFECT_STRIKE = new Skill("Perfect Strike", false, null);
    public static final Skill PERFECTION_IS_OVERRATED = new Skill("Perfection is Overrated", false, null);
    public static final Skill QUICK_BOIL = new Skill("Quick Boil", false, null);
    public static final Skill REMEMBER_MY_DEFINING_MOMENT = new Skill("Remember: My Defining Moment", false, null);
    public static final Skill SHARED_AUTHORITY_THE_WANDERING_INN = new Skill("Shared Authority: The Wandering Inn", false, null);
    public static final Skill STAFF_FLURRY_OF_EFFICIENCY = new Skill("Staff: Flurry of Efficiency", false, null);
    public static final Skill THE_TRANSIENT_ETC = new Skill("The Transient, Ephemeral, Fleeting Vault of the Mortal World. The Evanescent Safe of Passing Moments, the Faded Chest of Then and Them. The Box of Incontinuity.", false, null);
    public static final Skill THICK_SCALES = new Skill("Thick Scales", false, null);
    public static final Skill WORLD_OF_YOU_AND_ME = new Skill("World of You and Me", false, null);
    public static final Skill WORLDS_EYE_THEATRE = new Skill("World's Eye Theatre", false, null);

    public static final Skill APPRAISAL = new Skill("Appraisal", true, null);
    public static final Skill BARRIER_OF_AIR = new Skill("Barrier of Air", true, null);
    public static final Skill CLEANSE = new Skill("Cleanse", true, null);
    public static final Skill CONJURE_PREPARED_DISH = new Skill("Conjure Prepared Dish", true, null);
    public static final Skill CREATE_FOOD = new Skill("Create Food", true, null);
    public static final Skill DETECT_TRUTH = new Skill("Detect Truth", true, null);
    public static final Skill FIREBALL = new Skill("Fireball", true, null);
    public static final Skill FLASH_STEP = new Skill("Flash Step", true, null);
    public static final Skill HEATED_AIR = new Skill("Heated Air", true, null);
    public static final Skill MESSAGE = new Skill("Message", true, null);
    public static final Skill REPAIR = new Skill("Repair", true, null);
    public static final Skill SCRY = new Skill("Scry", true, null);
    public static final Skill SILVERGLOW_ENCHANTMENT = new Skill("Silverglow Enchantment", true, null);
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
