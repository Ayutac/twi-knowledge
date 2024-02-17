package org.abos.twi.knowledge.core.location;

import org.abos.common.Named;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.util.Objects;

public record Settlement(String name, SettlementType settlementType, Nation nation, String wikiLink) implements Named {

    public static final Settlement CHICAGO = new Settlement("Chicago", SettlementType.CITY, Nation.USA, null);
    public static final Settlement GRAND_RAPIDS = new Settlement("Grand Rapids", SettlementType.CITY, Nation.MICHIGAN, null);
    public static final Settlement LONDON = new Settlement("London", SettlementType.CITY, Nation.UK, null);

    public static final Settlement CARVEN_CITY = new Settlement("Carven City", SettlementType.CITY, Nation.ACTELIOS_SALASH, WikiHelper.WIKI_URL + "Carven City");
    public static final Settlement CELUM = new Settlement("Celum", SettlementType.CITY, Nation.FIVE_FAMILIES_TERRITORY, WikiHelper.WIKI_URL + "Celum");
    public static final Settlement ESTHELM = new Settlement("Esthelm", SettlementType.CITY, Nation.FIVE_FAMILIES_TERRITORY, WikiHelper.WIKI_URL + "Esthelm");
    public static final Settlement GOISEDALL = new Settlement("Goisedall", SettlementType.CITY, Nation.DRAKE_TERRITORY, WikiHelper.WIKI_URL + "Goisedall");
    public static final Settlement INSERELADREANUM = new Settlement(Nation.INSERELADREANUM.name(), SettlementType.CITY, Nation.INSERELADREANUM, Nation.INSERELADREANUM.wikiLink());
    public static final Settlement INVRISIL = new Settlement("Invrisil", SettlementType.CITY, Nation.FIVE_FAMILIES_TERRITORY, WikiHelper.WIKI_URL + "Invrisil");
    public static final Settlement LISCOR = new Settlement(Nation.LISCOR.name(), SettlementType.CITY, Nation.LISCOR, Nation.LISCOR.wikiLink());
    public static final Settlement MANUS = new Settlement(Nation.MANUS.name(), SettlementType.CITY, Nation.MANUS, Nation.MANUS.wikiLink());
    public static final Settlement NOMBERNAUGHT = new Settlement(Nation.NOMBERNAUGHT.name(), SettlementType.CITY, Nation.NOMBERNAUGHT, Nation.NOMBERNAUGHT.wikiLink());
    public static final Settlement PALLASS = new Settlement(Nation.PALLASS.name(), SettlementType.CITY, Nation.PALLASS, Nation.PALLASS.wikiLink());
    public static final Settlement PARANFER = new Settlement("Paranfer", SettlementType.CITY, Nation.BLIGHTED_KINGDOM, WikiHelper.WIKI_URL + "Paranfer");
    public static final Settlement PAETH = new Settlement(Nation.PAETH.name(), SettlementType.CITY, Nation.PAETH, Nation.PAETH.wikiLink());
    public static final Settlement REIM = new Settlement("Reim", SettlementType.CITY, Nation.REIM, WikiHelper.WIKI_URL + "Reim");
    public static final Settlement RHEIRGEST = new Settlement("Rheirgest", SettlementType.VILLAGE, Nation.FIVE_FAMILIES_TERRITORY, WikiHelper.WIKI_URL + "Rheirgest");
    public static final Settlement RIVERFARM = new Settlement("Riverfarm", SettlementType.VILLAGE, Nation.UNSEEN_EMPIRE, WikiHelper.WIKI_URL + "Riverfarm");
    public static final Settlement TALENQUAL = new Settlement("Talenqual", SettlementType.TOWN, Nation.BALEROS_UNCLAIMED, WikiHelper.WIKI_URL + "Talenqual");
    public static final Settlement WINDREST = new Settlement("Windrest", SettlementType.VILLAGE, Nation.FIVE_FAMILIES_TERRITORY, WikiHelper.WIKI_URL + "Windrest");
    public static final Settlement WISTRAM = new Settlement("Wistram Academy", SettlementType.CASTLE, Nation.WISTRAM, Nation.WISTRAM.wikiLink());

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
