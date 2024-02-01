package org.abos.twi.knowledge.core.location;

import org.abos.common.Named;
import org.abos.twi.knowledge.core.publication.Chapter;

import java.util.Objects;

public record LandmassOcean(String name, LandmassOceanType type, World world, String wikiLink) implements Named {

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
