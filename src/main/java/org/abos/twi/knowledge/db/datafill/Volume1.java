package org.abos.twi.knowledge.db.datafill;

import org.abos.twi.knowledge.core.location.Landmark;
import org.abos.twi.knowledge.core.location.LandmassOcean;
import org.abos.twi.knowledge.core.location.LandmassOceanType;
import org.abos.twi.knowledge.core.location.World;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.db.DbHelper;
import org.abos.twi.knowledge.wiki.WikiHelper;

import java.sql.SQLException;

public final class Volume1 {

    private Volume1() {
        /* No instantiation. */
    }

    public static void fillDb(final DbHelper dbHelper) throws SQLException {
        ch00(dbHelper);
        ch01(dbHelper);
        ch02(dbHelper);
        ch03(dbHelper);
        ch04(dbHelper);
        ch05(dbHelper);
        ch06(dbHelper);
        ch07(dbHelper);
        ch08(dbHelper);
        ch09(dbHelper);
        ch10(dbHelper);
        theGreatRitual(dbHelper);
        ch11(dbHelper);
        ch12(dbHelper);
        ch13(dbHelper);
        ch14(dbHelper);
        ch15(dbHelper);
        ch16(dbHelper);
        ch17(dbHelper);
        ch18(dbHelper);
        ch19r(dbHelper);
        ch20r(dbHelper);
        ch21(dbHelper);
        ch22(dbHelper);
        ch23a(dbHelper);
        ch24(dbHelper);
        king(dbHelper);
        ch25(dbHelper);
        ch26r(dbHelper);
        ch27r(dbHelper);
        ch28a(dbHelper);
        ch29(dbHelper);
        ch30(dbHelper);
        ch31(dbHelper);
        ch32r(dbHelper);
        ch33r(dbHelper);
        ch34(dbHelper);
        ch35r(dbHelper);
        ch36(dbHelper);
        ch37(dbHelper);
        ch38(dbHelper);
        ch39r(dbHelper);
        ch40r(dbHelper);
        ch41(dbHelper);
        ch42(dbHelper);
        ch43r(dbHelper);
        ch44r(dbHelper);
        ch45(dbHelper);
        ch46(dbHelper);
        ch47r(dbHelper);
        ch48r(dbHelper);
        ch49(dbHelper);
        ch50(dbHelper);
        ch51(dbHelper);
        ch52r(dbHelper);
        ch53(dbHelper);
        ch54(dbHelper);
        ch55r(dbHelper);
        ch56(dbHelper);
        ch57h(dbHelper);
        ch58h(dbHelper);
        ch59h(dbHelper);
        ch60(dbHelper);
        ch61(dbHelper);
        ch62(dbHelper);
        ch63(dbHelper);
    }

    private static void ch00(final DbHelper dbHelper) throws SQLException {
        final Chapter ch = dbHelper.fetchChapter("Chapter 1.00");
        final World innworld = new World("Innworld", WikiHelper.WIKI_URL + "Innworld");
        dbHelper.addWorld(innworld);
        final LandmassOcean izril = new LandmassOcean("Izril", LandmassOceanType.CONTINENT, innworld, WikiHelper.WIKI_URL + "Izril");
        dbHelper.addLandmassOcean(izril);
        dbHelper.addLandmark(new Landmark("Floodplains", true, izril, WikiHelper.WIKI_URL + "Floodplains"));
        dbHelper.addLandmark(new Landmark("High Passes", true, izril, WikiHelper.WIKI_URL + "High_Passes"));
        dbHelper.addLandmark(new Landmark("Wandering Inn", true, izril, WikiHelper.WIKI_URL + "First_Wandering_Inn"));

    }

    private static void ch01(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch02(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch03(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch04(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch05(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch06(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch07(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch08(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch09(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch10(final DbHelper dbHelper) throws SQLException {

    }

    private static void theGreatRitual(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch11(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch12(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch13(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch14(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch15(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch16(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch17(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch18(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch19r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch20r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch21(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch22(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch23a(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch24(final DbHelper dbHelper) throws SQLException {

    }

    private static void king(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch25(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch26r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch27r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch28a(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch29(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch30(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch31(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch32r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch33r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch34(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch35r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch36(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch37(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch38(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch39r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch40r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch41(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch42(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch43r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch44r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch45(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch46(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch47r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch48r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch49(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch50(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch51(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch52r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch53(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch54(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch55r(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch56(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch57h(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch58h(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch59h(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch60(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch61(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch62(final DbHelper dbHelper) throws SQLException {

    }

    private static void ch63(final DbHelper dbHelper) throws SQLException {

    }

}
