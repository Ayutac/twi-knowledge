package org.abos.twi.knowledge.core;

import org.abos.common.Named;
import org.abos.twi.knowledge.core.publication.Chapter;

import java.util.Objects;

public record Class(String name, Chapter since, String wikiLink) implements Named {

    public static final String CHIEFTAIN = "Chieftain";

    public static final String HERO = "Hero";

    public static final String INNKEEPER = "Innkeeper";

    public static final String KNIGHT = "Knight";

    public static final String SHAMAN = "Shaman";

    public Class(final String name, final Chapter since, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.since = since;
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }
}
