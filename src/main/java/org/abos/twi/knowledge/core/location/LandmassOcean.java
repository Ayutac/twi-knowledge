package org.abos.twi.knowledge.core.location;

import org.abos.common.Named;

import java.util.Objects;

public record LandmassOcean(String name, LandmassOceanType type, World world, String wikiLink) implements Named {

    public static final String NORTH_AMERICA = "North America";

    public static final String BLUE_MOON = "Blue Moon";

    public static final String GREEN_MOON = "Green Moon";

    public static final String BALEROS = "Baleros";

    public static final String CHANDRAR = "Chandrar";

    public static final String IZRIL = "Izril";

    public static final String RHIR = "Rhir";

    public static final String TERANDRIA = "Terandria";

    public static final String NORTHERN_IZRIL = "Northern Izril";

    public static final String SOUTHERN_IZRIL = "Southern Izril";

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
