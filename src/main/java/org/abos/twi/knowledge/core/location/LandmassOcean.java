package org.abos.twi.knowledge.core.location;

import org.abos.common.Named;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.util.Objects;

public record LandmassOcean(String name, LandmassOceanType type, World world, String wikiLink) implements Named {

    public static final LandmassOcean BLUE_MOON = new LandmassOcean("Blue Moon", LandmassOceanType.MOON, World.INNWORLD, null);
    public static final LandmassOcean GREEN_MOON = new LandmassOcean("Green Moon", LandmassOceanType.MOON, World.INNWORLD, null);
    public static final LandmassOcean NORTH_AMERICA = new LandmassOcean("North America", LandmassOceanType.CONTINENT, World.EARTH, null);
    public static final LandmassOcean EUROPE = new LandmassOcean("Europe", LandmassOceanType.CONTINENT, World.EARTH, null);
    public static final LandmassOcean BALEROS = new LandmassOcean("Baleros", LandmassOceanType.CONTINENT, World.INNWORLD, WikiHelper.WIKI_URL + "Baleros");
    public static final LandmassOcean CHANDRAR = new LandmassOcean("Chandrar", LandmassOceanType.CONTINENT, World.INNWORLD, WikiHelper.WIKI_URL + "Chandrar");
    public static final LandmassOcean IZRIL = new LandmassOcean("Izril", LandmassOceanType.CONTINENT, World.INNWORLD, WikiHelper.WIKI_URL + "Izril");
    public static final LandmassOcean RHIR = new LandmassOcean("Rhir", LandmassOceanType.CONTINENT, World.INNWORLD, WikiHelper.WIKI_URL + "Rhir");
    public static final LandmassOcean TERANDRIA = new LandmassOcean("Terandria", LandmassOceanType.CONTINENT, World.INNWORLD, WikiHelper.WIKI_URL + "Terandria");
    public static final LandmassOcean NORTHERN_IZRIL = new LandmassOcean("Northern Izril", LandmassOceanType.HALF_CONTINENT, World.INNWORLD, WikiHelper.WIKI_URL + "Izril");
    public static final LandmassOcean SOUTHERN_IZRIL = new LandmassOcean("Southern Izril", LandmassOceanType.HALF_CONTINENT, World.INNWORLD, WikiHelper.WIKI_URL + "Izril");
    public static final LandmassOcean GREAT_BRITAIN = new LandmassOcean("Great Britain", LandmassOceanType.ISLE, World.EARTH, null);
    public static final LandmassOcean WISTRAM_ISLE = new LandmassOcean("Wistram Isle", LandmassOceanType.ISLE, World.INNWORLD, WikiHelper.WIKI_URL + "Wistram Academy");

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
