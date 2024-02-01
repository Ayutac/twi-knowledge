package org.abos.twi.knowledge.core;

import org.abos.common.Named;

import java.util.Objects;

public record LandmassOcean(String name, LandmassOceanType landmassOceanType, Chapter since, World world, String wikiLink) implements Named {

    public LandmassOcean(final String name, final LandmassOceanType landmassOceanType, final Chapter since, final World world, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.landmassOceanType = Objects.requireNonNull(landmassOceanType);
        this.since = since;
        this.world = world;
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }
}
