package org.abos.twi.knowledge.db.datafill;

import org.abos.common.LogUtil;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

public final class StaticFields {

    private static final Logger LOGGER = LogManager.getLogger(StaticFields.class);

    private StaticFields() {
        /* No instantiation. */
    }

    public static void fillDb(final DbHelper dbHelper) throws SQLException, IllegalAccessException {
        LOGGER.info("Static fields data filling...");
        final Instant start = Instant.now();
        fillStaticFieldsOfClass(Class.class, dbHelper::addClass);
        fillStaticFieldsOfClass(Skill.class, dbHelper::addSkill);
        fillStaticFieldsOfClass(World.class, dbHelper::addWorld);
        fillStaticFieldsOfClass(LandmassOcean.class, dbHelper::addLandmassOcean);
        fillStaticFieldsOfClass(Landmark.class, dbHelper::addLandmark);
        fillStaticFieldsOfClass(Nation.class, dbHelper::addNation);
        fillStaticFieldsOfClass(Settlement.class, dbHelper::addSettlement);
        fillStaticFieldsOfClass(Species.class, dbHelper::addSpecies);
        final Duration time = Duration.between(start, Instant.now());
        LOGGER.info(LogUtil.LOG_TIME_MSG, "Static fields data filling", time.toMinutes(), time.toSecondsPart());
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
