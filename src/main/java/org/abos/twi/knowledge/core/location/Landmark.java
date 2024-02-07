package org.abos.twi.knowledge.core.location;

import org.abos.common.Named;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.util.Objects;

public record Landmark(String name, boolean natural, LandmassOcean landmassOcean, String wikiLink) implements Named {

    public static final Landmark AMENTUS_GROVE = new Landmark("Amentus Grove", true, LandmassOcean.NORTHERN_IZRIL, WikiHelper.WIKI_URL + "Amentus");
    public static final Landmark BLOODFIELDS = new Landmark("Bloodfields", true, LandmassOcean.IZRIL, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName("Bloodfields"));
    public static final Landmark FIRST_WANDERING_INN = new Landmark("First Wandering Inn", false, LandmassOcean.NORTHERN_IZRIL, WikiHelper.WIKI_URL + "First_Wandering_Inn");
    public static final Landmark FLOODED_WATERS_CAVE = new Landmark("Original cave of the Flooded Waters Tribe", true, LandmassOcean.NORTHERN_IZRIL, null);
    public static final Landmark FLOODPLAINS = new Landmark("Floodplains", true, LandmassOcean.NORTHERN_IZRIL, WikiHelper.WIKI_URL + "Floodplains");
    public static final Landmark FLOODPLAINS_STREAM = new Landmark("Stream in the Floodplains", true, LandmassOcean.NORTHERN_IZRIL, null);
    public static final Landmark HIGH_PASSES = new Landmark("High Passes", true, LandmassOcean.IZRIL, WikiHelper.WIKI_URL + "High_Passes");
    public static final Landmark PISCES_HIDEOUT = new Landmark("Pisces' hideout", true, LandmassOcean.NORTHERN_IZRIL, null);
    public static final Landmark TERIARCHS_NEW_HIDEOUT = new Landmark("Teriarch's new hideout", true, LandmassOcean.NORTHERN_IZRIL, null);
    public static final Landmark TERIARCHS_OLD_HIDEOUT = new Landmark("Teriarch's old hideout", true, LandmassOcean.NORTHERN_IZRIL, null);
    public static final Landmark THIRD_WANDERING_INN = new Landmark("Wandering Inn", false, LandmassOcean.NORTHERN_IZRIL, WikiHelper.WIKI_URL + "Wandering_Inn");

    public Landmark(final String name, final boolean natural, LandmassOcean landmassOcean, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.natural = natural;
        this.landmassOcean = landmassOcean;
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }
}
