package org.abos.twi.knowledge.time;

import java.io.Serializable;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;

public final class TwicDate implements Temporal, TemporalAdjuster, Comparable<TwicDate>, Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 644226520114437865L;

    public static final int DAYS_PER_WEEK = 8;
    public static final int WEEKS_PER_MONTH = 4;
    public static final int DAYS_PER_MONTH = DAYS_PER_WEEK*WEEKS_PER_MONTH;
    public static final int MONTHS_PER_YEAR = 16;
    public static final int DAYS_PER_YEAR = DAYS_PER_MONTH*MONTHS_PER_YEAR;
    public static final int WEEKS_PER_YEAR = WEEKS_PER_MONTH*MONTHS_PER_YEAR;

    @Override
    public int compareTo(TwicDate other) {
        return 0;
    }

    @Override
    public boolean isSupported(TemporalUnit unit) {
        return false;
    }

    @Override
    public Temporal with(TemporalField field, long newValue) {
        return null;
    }

    @Override
    public Temporal plus(long amountToAdd, TemporalUnit unit) {
        return null;
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        return 0;
    }

    @Override
    public boolean isSupported(TemporalField field) {
        return false;
    }

    @Override
    public long getLong(TemporalField field) {
        return 0;
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        return null;
    }
}
