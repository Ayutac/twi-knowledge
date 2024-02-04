package org.abos.twi.knowledge.core.location;

import org.abos.common.Named;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.util.Objects;

public record World(String name, String wikiLink) implements Named, Comparable<World> {

    public static final World EARTH = new World("Earth", WikiHelper.WIKI_URL + WikiHelper.sanitizePageName("Earth"));
    public static final World INNWORLD = new World("Innworld", WikiHelper.WIKI_URL + WikiHelper.sanitizePageName("Innworld"));

    public World(final String name, final String wikiLink) {
        this.name = Objects.requireNonNull(name);
        this.wikiLink = wikiLink;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(final World other) {
        return name.compareTo(other.name);
    }
}
