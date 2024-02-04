package org.abos.twi.knowledge.core.location;

import org.abos.common.Named;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.util.Objects;

public record LandmassOcean(String name, LandmassOceanType type, World world, String wikiLink) implements Named {

    public static final LandmassOcean BLUE_MOON = new LandmassOcean("Blue Moon", LandmassOceanType.MOON, World.INNWORLD, null);
    public static final LandmassOcean GREEN_MOON = new LandmassOcean("Green Moon", LandmassOceanType.MOON, World.INNWORLD, null);
    public static final LandmassOcean NORTH_AMERICA = new LandmassOcean("North America", LandmassOceanType.CONTINENT, World.EARTH, null);
    public static final LandmassOcean IZRIL = new LandmassOcean("Izril", LandmassOceanType.CONTINENT, World.INNWORLD, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName("Izril"));
    public static final LandmassOcean NORTHERN_IZRIL = new LandmassOcean("Northern Izril", LandmassOceanType.HALF_CONTINENT, World.INNWORLD, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName("Izril"));
    public static final LandmassOcean SOUTHERN_IZRIL = new LandmassOcean("Southern Izril", LandmassOceanType.HALF_CONTINENT, World.INNWORLD, WikiHelper.WIKI_URL + WikiHelper.sanitizePageName("Izril"));

    public LandmassOcean(final String name, final LandmassOceanType type, final World world, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.type = Objects.requireNonNull(type);
        this.world = world;
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }
}
