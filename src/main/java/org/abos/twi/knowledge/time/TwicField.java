package org.abos.twi.knowledge.time;

import java.time.temporal.*;


public enum TwicField implements TemporalField {

    SECOND_OF_MINUTE("SecondOfMinute", ChronoUnit.SECONDS, ChronoUnit.MINUTES, ValueRange.of(0, TwicTime.SECONDS_PER_MINUTE - 1)),

    MINUTE_OF_HOUR("MinuteOfHour", ChronoUnit.MINUTES, ChronoUnit.HOURS, ValueRange.of(0, TwicTime.MINUTES_PER_HOUR - 1)),

    HOUR_OF_DAY("HourOfDay", ChronoUnit.HOURS, TwicUnit.DAYS, ValueRange.of(0, TwicTime.HOURS_PER_DAY - 1)),

    DAY_OF_WEEK("DayOfWeek", TwicUnit.DAYS, TwicUnit.WEEKS, ValueRange.of(1, TwicDate.DAYS_PER_WEEK)),

    DAY_OF_MONTH("DayOfMonth", TwicUnit.DAYS, TwicUnit.MONTHS, ValueRange.of(1, TwicDate.DAYS_PER_MONTH)),

    DAY_OF_YEAR("DayOfYear", TwicUnit.DAYS, TwicUnit.YEARS, ValueRange.of(1, TwicDate.DAYS_PER_YEAR)),

    // TODO EPOCH_DAY

    WEEK_OF_MONTH("WeekOfMonth", TwicUnit.WEEKS, TwicUnit.MONTHS, ValueRange.of(1, TwicDate.WEEKS_PER_MONTH)),

    WEEK_OF_YEAR("WeekOfYear", TwicUnit.WEEKS, TwicUnit.YEARS, ValueRange.of(1, TwicDate.WEEKS_PER_YEAR)),

    MONTH_OF_YEAR("MonthOfYear", TwicUnit.MONTHS, TwicUnit.YEARS, ValueRange.of(1, TwicDate.MONTHS_PER_YEAR));

    private final String name;
    private final TemporalUnit baseUnit;
    private final TemporalUnit rangeUnit;
    private final ValueRange range;

    private TwicField(String name, TemporalUnit baseUnit, TemporalUnit rangeUnit, ValueRange range) {
        this.name = name;
        this.baseUnit = baseUnit;
        this.rangeUnit = rangeUnit;
        this.range = range;
    }

    @Override
    public TemporalUnit getBaseUnit() {
        return baseUnit;
    }

    @Override
    public TemporalUnit getRangeUnit() {
        return rangeUnit;
    }

    @Override
    public ValueRange range() {
        return range;
    }

    @Override
    public boolean isDateBased() {
        return true;
    }

    @Override
    public boolean isTimeBased() {
        return false;
    }

    @Override
    public boolean isSupportedBy(TemporalAccessor temporal) {
        return false;
    }

    @Override
    public ValueRange rangeRefinedBy(TemporalAccessor temporal) {
        return range;
    }

    public long checkValidValue(long value) {
        return range().checkValidValue(value, this);
    }

    @Override
    public long getFrom(TemporalAccessor temporal) {
        // TODO
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public <R extends Temporal> R adjustInto(R temporal, long newValue) {
        // TODO
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public String toString() {
        return name;
    }
}
