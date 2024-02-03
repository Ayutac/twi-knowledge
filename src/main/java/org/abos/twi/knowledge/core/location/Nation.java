package org.abos.twi.knowledge.core.location;

import org.abos.common.Named;
import org.abos.twi.knowledge.core.publication.Chapter;

import java.util.Objects;

public record Nation(String name, NationType nationType, LandmassOcean landmassOcean, String wikiLink) implements Named {

    public static final String USA = "USA";

    public static final String FIVE_FAMILIES = "Five Families";

    public static final String LISCOR = "Liscor";

    public Nation(final String name, final NationType nationType, final LandmassOcean landmassOcean, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.nationType = Objects.requireNonNull(nationType);
        this.landmassOcean = landmassOcean;
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }
}
