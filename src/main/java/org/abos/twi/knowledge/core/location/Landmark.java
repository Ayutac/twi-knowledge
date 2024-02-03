package org.abos.twi.knowledge.core.location;

import org.abos.common.Named;
import org.abos.twi.knowledge.core.publication.Chapter;

import java.util.Objects;

public record Landmark(String name, boolean natural, LandmassOcean landmassOcean, String wikiLink) implements Named {

    public static final String AMENTUS_GROVE = "Amentus Grove";

    public static final String FIRST_WANDERING_INN = "First Wandering Inn";

    public static final String FLOODED_WATERS_CAVE = "Original cave of the Flooded Waters Tribe";

    public static final String FLOODPLAINS = "Floodplains";

    public static final String FLOODPLAINS_STREAM = "Stream in the Floodplains";

    public static final String HIGH_PASSES = "High Passes";

    public static final String PISCES_HIDEOUT = "Pisces' hideout";

    public static final String TERIARCHS_NEW_HIDEOUT = "Teriarch's new hideout";

    public static final String TERIARCHS_OLD_HIDEOUT = "Teriarch's old hideout";

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
