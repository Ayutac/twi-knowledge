package org.abos.twi.knowledge.core;

import org.abos.common.Named;

import java.util.Objects;

public record Nation(String name, NationType nationType, Chapter since, LandmassOcean landmassOcean, String wikiLink) implements Named {

    public Nation(final String name, final NationType nationType, final Chapter since, final LandmassOcean landmassOcean, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.nationType = Objects.requireNonNull(nationType);
        this.since = since;
        this.landmassOcean = landmassOcean;
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }
}
