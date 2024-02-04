package org.abos.twi.knowledge.core.location;

import org.abos.common.Named;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.util.Objects;

public record Settlement(String name, SettlementType settlementType, Nation nation, String wikiLink) implements Named {

    public static final Settlement CHICAGO = new Settlement("Chicago", SettlementType.CITY, Nation.USA, null);
    public static final Settlement GRAND_RAPIDS = new Settlement("Grand Rapids", SettlementType.CITY, Nation.USA, null);
    public static final Settlement LISCOR = new Settlement(Nation.LISCOR.name(), SettlementType.CITY, Nation.LISCOR, Nation.LISCOR.wikiLink());
    public static final Settlement CELUM = new Settlement("Celum", SettlementType.CITY, Nation.FIVE_FAMILIES_TERRITORY, WikiHelper.WIKI_URL + "Celum");
    public static final Settlement PALLASS = new Settlement(Nation.PALLASS.name(), SettlementType.CITY, Nation.PALLASS, Nation.PALLASS.wikiLink());

    public Settlement(final String name, final SettlementType settlementType, final Nation nation, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.settlementType = Objects.requireNonNull(settlementType);
        this.nation = nation;
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }
}
