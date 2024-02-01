package org.abos.twi.knowledge.core.location;

import org.abos.common.Named;
import org.abos.twi.knowledge.core.publication.Chapter;

import java.util.Objects;

public record Landmark(String name, boolean natural, LandmassOcean landmassOcean, String wikiLink) implements Named {

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
