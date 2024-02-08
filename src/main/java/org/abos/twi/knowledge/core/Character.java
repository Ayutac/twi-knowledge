package org.abos.twi.knowledge.core;

import org.abos.common.Named;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.util.Objects;

public record Character(String wikiLink) implements Named {

    public static final Character ARTHUR = new Character(WikiHelper.WIKI_URL + "King_Arthur");
    public static final Character AZKERASH = new Character(WikiHelper.WIKI_URL + "Az’kerash");
    public static final Character BEILMARK = new Character(WikiHelper.WIKI_URL + "Beilmark");
    public static final Character BELSC = new Character(WikiHelper.WIKI_URL + "Belsc");
    public static final Character CARA = new Character(WikiHelper.WIKI_URL + "Cara_O'Sullivan");
    public static final Character CORETINE = new Character(WikiHelper.WIKI_URL + "Coretine");
    public static final Character CHOLE = new Character(WikiHelper.WIKI_URL + "Chole");
    public static final Character CYNTHIA = new Character(WikiHelper.WIKI_URL + "Cynthia");
    public static final Character DRASSI = new Character(WikiHelper.WIKI_URL + "Drassi_Tewing");
    public static final Character DURENE = new Character(WikiHelper.WIKI_URL + "Durene_Faerise");
    public static final Character EDDY = new Character(WikiHelper.WIKI_URL + "Edward");
    public static final Character EMILY = new Character(WikiHelper.WIKI_URL + "Emily");
    public static final Character ERIN = new Character(WikiHelper.WIKI_URL + "Erin_Solstice");
    public static final Character FLOS = new Character(WikiHelper.WIKI_URL + "Flos_Reimarch");
    public static final Character FLOODED_WATERS_CHIEFTAIN = new Character(WikiHelper.WIKI_URL + "Goblin_Chieftain");
    public static final Character GALINA = new Character(WikiHelper.WIKI_URL + "Galina");
    public static final Character GEWILENA = new Character(WikiHelper.WIKI_URL + "Gewilena");
    public static final Character HAYVON = new Character(WikiHelper.WIKI_URL + "Hayvon_Operland");
    public static final Character HAWK = new Character(WikiHelper.WIKI_URL + "Hawk");
    public static final Character IMANI = new Character(WikiHelper.WIKI_URL + "Imani");
    public static final Character JOSEPH = new Character(WikiHelper.WIKI_URL + "Joseph_Ortega");
    public static final Character KATY = new Character(WikiHelper.WIKI_URL + "Katy");
    public static final Character KEITH = new Character(WikiHelper.WIKI_URL + "Keith");
    public static final Character KEVIN = new Character(WikiHelper.WIKI_URL + "Kevin_Hall");
    public static final Character KLBKCH = new Character(WikiHelper.WIKI_URL + "Klbkch");
    public static final Character KRSHIA = new Character(WikiHelper.WIKI_URL + "Krshia_Silverfang");
    public static final Character LAKEN = new Character(WikiHelper.WIKI_URL + "Laken_Godart");
    public static final Character LEON = new Character(WikiHelper.WIKI_URL + "Leon");
    public static final Character LILY = new Character(WikiHelper.WIKI_URL + "Lillian_Woods");
    public static final Character LISM = new Character(WikiHelper.WIKI_URL + "Lism_Swifttail");
    public static final Character MAGNOLIA = new Character(WikiHelper.WIKI_URL + "Magnolia_Reinhart");
    public static final Character MARIAN_US = new Character(WikiHelper.WIKI_URL + "Marian_(The_Americans)");
    public static final Character NANETTE = new Character(WikiHelper.WIKI_URL + "Nanette_Weishart");
    public static final Character NERESHAL = new Character(WikiHelper.WIKI_URL + "Nereshal");
    public static final Character ORTHENON = new Character(WikiHelper.WIKI_URL + "Orthenon");
    public static final Character OTHIUS = new Character(WikiHelper.WIKI_URL + "Othius_the_Fourth");
    public static final Character PISCES = new Character(WikiHelper.WIKI_URL + "Pisces_Jealnet");
    public static final Character QUARASS = new Character(WikiHelper.WIKI_URL + "Quarass");
    public static final Character RAGS = new Character(WikiHelper.WIKI_URL + "Rags");
    public static final Character RED = new Character(WikiHelper.WIKI_URL + "Red");
    public static final Character RELC = new Character(WikiHelper.WIKI_URL + "Relc_Grasstongue");
    public static final Character REYANNE = new Character(WikiHelper.WIKI_URL + "Reyanne");
    public static final Character RICHARD = new Character(WikiHelper.WIKI_URL + "Richard_Davenport");
    public static final Character RON = new Character(WikiHelper.WIKI_URL + "Ron");
    public static final Character ROSE = new Character(WikiHelper.WIKI_URL + "Rose");
    public static final Character RYOKA = new Character(WikiHelper.WIKI_URL + "Ryoka_Griffin");
    public static final Character SELYS = new Character(WikiHelper.WIKI_URL + "Selys_Shivertail");
    public static final Character STACY = new Character(WikiHelper.WIKI_URL + "Stacy");
    public static final Character TERES = new Character(WikiHelper.WIKI_URL + "Teresa_Atwood");
    public static final Character TERIARCH = new Character(WikiHelper.WIKI_URL + "Teriarch");
    public static final Character TKRN = new Character(WikiHelper.WIKI_URL + "Tkrn");
    public static final Character TOM = new Character(WikiHelper.WIKI_URL + "Thomas_Trautmann");
    public static final Character TREY = new Character(WikiHelper.WIKI_URL + "Trey_Atwood");
    public static final Character TROYDEL = new Character(WikiHelper.WIKI_URL + "Troydel");
    public static final Character ZEL = new Character(WikiHelper.WIKI_URL + "Zel_Shivertail");
    public static final Character VINCENT = new Character(WikiHelper.WIKI_URL + "Vincent");
    public static final Character ZEVARA = new Character(WikiHelper.WIKI_URL + "Zevara_Sunderscale");

    public Character(final String wikiLink) {
        this.wikiLink = Objects.requireNonNull(wikiLink);
    }

    @Override
    public String getName() {
        final int index = wikiLink.substring(0, wikiLink.length()-1).lastIndexOf('/');
        if (index == -1) {
            return wikiLink.replace('_', ' ').replace("/", "");
        }
        return wikiLink.substring(index+1).replace('_', ' ').replace("/", "");
    }
}
