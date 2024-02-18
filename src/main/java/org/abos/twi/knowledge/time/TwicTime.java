package org.abos.twi.knowledge.time;

import java.io.Serializable;
import java.time.temporal.*;

public final class TwicTime implements Temporal, TemporalAdjuster, Comparable<TwicTime>, Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 641443786542265201L;

    public static final int HOURS_PER_DAY = 24;

    static final int MINUTES_PER_HOUR = 60;

    static final int SECONDS_PER_MINUTE = 60;

    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

    static final int SECONDS_PER_DAY = SECONDS_PER_HOUR * HOURS_PER_DAY;

    private final byte hour;

    private final byte minute;

    private final byte second;

    private TwicTime(int hour, int minute, int second) {
        this.hour = (byte) hour;
        this.minute = (byte) minute;
        this.second = (byte) second;
    }

    public static TwicTime of(int hour, int minute, int second) {
        TwicField.HOUR_OF_DAY.checkValidValue(hour);
        TwicField.MINUTE_OF_HOUR.checkValidValue(minute);
        TwicField.SECOND_OF_MINUTE.checkValidValue(second);
        return new TwicTime(hour, minute, second);
    }

    @Override
    public int compareTo(TwicTime other) {
        final int hourCmp = this.hour - other.hour;
        if (hourCmp != 0) {
            return hourCmp;
        }
        final int minCmp = this.minute - other.minute;
        if (minCmp != 0) {
            return minCmp;
        }
        return this.second - other.second;
    }

    @Override
    public boolean isSupported(TemporalUnit unit) {
        if (unit == ChronoUnit.SECONDS || unit == ChronoUnit.MINUTES || unit == ChronoUnit.HOURS) {
            return true;
        }
        return unit != null && unit.isSupportedBy(this);
    }

    @Override
    public Temporal with(TemporalField field, long newValue) {
        // TODO
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public Temporal plus(long amountToAdd, TemporalUnit unit) {
        // TODO
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        // TODO
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public boolean isSupported(TemporalField field) {
        if (field instanceof TwicField) {
            return field.isTimeBased();
        }
        return field != null && field.isSupportedBy(this);
    }

    @Override
    public long getLong(TemporalField field) {
        return get(field);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        // TODO
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
