package org.abos.twi.knowledge.core.location;

import org.abos.common.Named;

import java.util.Objects;

public record Settlement(String name, SettlementType settlementType, Nation nation, String wikiLink) implements Named {

    public static final String CHICAGO = "Chicago";

    public static final String GRAND_RAPIDS = "Grand Rapids";

    public static final String CELUM = "Celum";

    public static final String LISCOR = Nation.LISCOR;

    public static final String PALLASS = Nation.PALLASS;

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
