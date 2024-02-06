package org.abos.twi.knowledge.db.datafill;

import org.abos.twi.knowledge.core.Class;
import org.abos.twi.knowledge.core.Skill;
import org.abos.twi.knowledge.core.Species;
import org.abos.twi.knowledge.core.location.Landmark;
import org.abos.twi.knowledge.core.location.LandmassOcean;
import org.abos.twi.knowledge.core.location.Nation;
import org.abos.twi.knowledge.core.location.Settlement;
import org.abos.twi.knowledge.core.location.World;
import org.abos.twi.knowledge.db.DbHelper;
import org.abos.twi.knowledge.db.SQLConsumer;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.SQLException;

public final class StaticFields {

    private StaticFields() {
        /* No instantiation. */
    }

    public static void fillDb(final DbHelper dbHelper) throws SQLException, IllegalAccessException {
        fillStaticFieldsOfClass(Class.class, dbHelper::addClass);
        fillStaticFieldsOfClass(Skill.class, dbHelper::addSkill);
        fillStaticFieldsOfClass(World.class, dbHelper::addWorld);
        fillStaticFieldsOfClass(LandmassOcean.class, dbHelper::addLandmassOcean);
        fillStaticFieldsOfClass(Landmark.class, dbHelper::addLandmark);
        fillStaticFieldsOfClass(Nation.class, dbHelper::addNation);
        fillStaticFieldsOfClass(Settlement.class, dbHelper::addSettlement);
        fillStaticFieldsOfClass(Species.class, dbHelper::addSpecies);
    }

    public static <T> void fillStaticFieldsOfClass(java.lang.Class<T> clazz, SQLConsumer<T> consumer) throws IllegalAccessException, SQLException {
        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                final Object fieldValue = field.get(null);
                if (clazz.isInstance(fieldValue)) {
                    consumer.consume((T)fieldValue);
                }
            }
        }
    }

}
