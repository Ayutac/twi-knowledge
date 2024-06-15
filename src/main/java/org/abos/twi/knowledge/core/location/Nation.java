package org.abos.twi.knowledge.core.location;

import org.abos.common.Named;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.util.Objects;

public record Nation(String name, NationType nationType, LandmassOcean landmassOcean, String wikiLink) implements Named {

    public static final Nation ITALY = new Nation("Italy", NationType.REPUBLIC, LandmassOcean.EUROPE, null);
    public static final Nation MICHIGAN = new Nation("Michigan", NationType.REPUBLIC, LandmassOcean.NORTH_AMERICA, null);
    public static final Nation NEW_YORK = new Nation("New York", NationType.REPUBLIC, LandmassOcean.NORTH_AMERICA, null);
    public static final Nation JAPAN = new Nation("Japan", NationType.REPUBLIC, LandmassOcean.JAPAN, null);
    public static final Nation UK = new Nation("UK", NationType.REPUBLIC, LandmassOcean.GREAT_BRITAIN, null);
    public static final Nation USA = new Nation("USA", NationType.REPUBLIC, LandmassOcean.NORTH_AMERICA, null);
    public static final Nation ACTELIOS_SALASH = new Nation("A’ctelios Salash", NationType.KINGDOM, LandmassOcean.CHANDRAR, WikiHelper.WIKI_URL + "A’ctelios_Salash");
    public static final Nation BALEROS_UNCLAIMED = new Nation("Unclaimed Territory in Baleros", NationType.OLIGARCHY, LandmassOcean.BALEROS, null);
    public static final Nation BLIGHTED_KINGDOM = new Nation("Blighted Kingdom", NationType.KINGDOM, LandmassOcean.RHIR, WikiHelper.WIKI_URL + "Blighted_Kingdom");
    public static final Nation CLAIVEN_EARTH = new Nation("Claiven Earth", NationType.KINGDOM, LandmassOcean.CHANDRAR, WikiHelper.WIKI_URL + "Claiven_Earth");
    public static final Nation DRAKE_TERRITORY = new Nation("Drake Territory", NationType.OLIGARCHY, LandmassOcean.SOUTHERN_IZRIL, null);
    public static final Nation ERRIBATHE = new Nation("Erribathe", NationType.KINGDOM, LandmassOcean.TERANDRIA, WikiHelper.WIKI_URL + "Erribathe");
    public static final Nation FIVE_FAMILIES_TERRITORY = new Nation("Five Families Territory", NationType.OLIGARCHY, LandmassOcean.NORTHERN_IZRIL, WikiHelper.WIKI_URL + "Five_Families");
    public static final Nation GAIIL_DROME = new Nation("Gaiil-Drome", NationType.KINGDOM, LandmassOcean.TERANDRIA, WikiHelper.WIKI_URL + "Gaiil-Drome");
    public static final Nation GOLAEN = new Nation("Golaen", NationType.KINGDOM, LandmassOcean.TERANDRIA, WikiHelper.WIKI_URL + "Golaen");
    public static final Nation HIVELANDS = new Nation("Hivelands", NationType.OLIGARCHY, LandmassOcean.SOUTHERN_IZRIL, null);
    public static final Nation INSERELADREANUM = new Nation("Inserelad’reanum", NationType.CITY_STATE, LandmassOcean.NEW_LANDS_OF_IZRIL, WikiHelper.WIKI_URL + "Inserelad’reanum");
    public static final Nation KAAZ = new Nation("Kaaz", NationType.KINGDOM, LandmassOcean.TERANDRIA, WikiHelper.WIKI_URL + "Kaaz");
    public static final Nation KALIV = new Nation("Kaliv", NationType.KINGDOM, LandmassOcean.TERANDRIA, WikiHelper.WIKI_URL + "Kaliv");
    public static final Nation LISCOR = new Nation("Liscor", NationType.CITY_STATE, LandmassOcean.NORTHERN_IZRIL, WikiHelper.WIKI_URL + "Liscor");
    public static final Nation MANUS = new Nation("Manus", NationType.WALLED_CITY, LandmassOcean.SOUTHERN_IZRIL, WikiHelper.WIKI_URL + "Manus");
    public static final Nation NOMBERNAUGHT = new Nation("Nombernaught", NationType.CITY_STATE, LandmassOcean.NEW_LANDS_OF_IZRIL, WikiHelper.WIKI_URL + "Nombernaught");
    public static final Nation NOELICTUS = new Nation("Noelictus", NationType.KINGDOM, LandmassOcean.TERANDRIA, WikiHelper.WIKI_URL + "Noelictus");
    public static final Nation PAETH = new Nation("Paeth", NationType.CITY_STATE, LandmassOcean.BALEROS, WikiHelper.WIKI_URL + "Paeth");
    public static final Nation PALLASS = new Nation("Pallass", NationType.WALLED_CITY, LandmassOcean.SOUTHERN_IZRIL, WikiHelper.WIKI_URL + "Pallass");
    public static final Nation PHEISLANT = new Nation("Pheislant", NationType.KINGDOM, LandmassOcean.TERANDRIA, WikiHelper.WIKI_URL + "Pheislant");
    public static final Nation REIM = new Nation("Reim", NationType.KINGDOM, LandmassOcean.CHANDRAR, WikiHelper.WIKI_URL + "Reim");
    public static final Nation TAIMAGUROS = new Nation("Taimaguros", NationType.KINGDOM, LandmassOcean.TERANDRIA, WikiHelper.WIKI_URL + "Taimaguros");
    public static final Nation UNSEEN_EMPIRE = new Nation("Unseen Empire", NationType.EMPIRE, LandmassOcean.NORTHERN_IZRIL, WikiHelper.WIKI_URL + "Unseen_Empire");
    public static final Nation WISTRAM = new Nation("Wistram", NationType.CITY_STATE, LandmassOcean.WISTRAM_ISLE, WikiHelper.WIKI_URL + "Wistram_Academy");

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
