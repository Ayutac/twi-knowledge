package org.abos.twi.knowledge.core.location;

import org.abos.common.Named;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.util.Objects;

public record Nation(String name, NationType nationType, LandmassOcean landmassOcean, String wikiLink) implements Named {

    public static final Nation USA = new Nation("USA", NationType.REPUBLIC, LandmassOcean.NORTH_AMERICA, null);
    public static final Nation MICHIGAN = new Nation("Michigan", NationType.REPUBLIC, LandmassOcean.NORTH_AMERICA, null);
    public static final Nation FIVE_FAMILIES_TERRITORY = new Nation("Five Families" + " Territory", NationType.OLIGARCHY, LandmassOcean.NORTHERN_IZRIL, WikiHelper.WIKI_URL + "Five Families");
    public static final Nation LISCOR = new Nation("Liscor", NationType.CITY_STATE, LandmassOcean.NORTHERN_IZRIL, WikiHelper.WIKI_URL + "Liscor");
    public static final Nation PALLASS = new Nation("Pallass", NationType.WALLED_CITY, LandmassOcean.NORTHERN_IZRIL, WikiHelper.WIKI_URL + "Pallass");

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
