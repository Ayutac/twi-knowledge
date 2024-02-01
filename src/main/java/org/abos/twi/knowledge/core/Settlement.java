package org.abos.twi.knowledge.core;

import org.abos.common.Named;

import java.util.Objects;

public record Settlement(String name, SettlementType settlementType, Chapter since, Nation nation, String wikiLink) implements Named {

    public Settlement(final String name, final SettlementType settlementType, final Chapter since, final Nation nation, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.settlementType = Objects.requireNonNull(settlementType);
        this.since = since;
        this.nation = nation;
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }
}
