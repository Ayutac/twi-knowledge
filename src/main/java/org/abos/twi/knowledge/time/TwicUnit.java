package org.abos.twi.knowledge.time;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

public enum TwicUnit implements TemporalUnit {

    DAYS("Days", Duration.ofHours(TwicTime.HOURS_PER_DAY)),

    WEEKS("Weeks", Duration.ofHours(TwicTime.HOURS_PER_DAY* TwicDate.DAYS_PER_WEEK)),

    MONTHS("Months", Duration.ofHours(TwicTime.HOURS_PER_DAY* TwicDate.DAYS_PER_MONTH)),

    YEARS("Years", Duration.ofHours(TwicTime.HOURS_PER_DAY* TwicDate.DAYS_PER_YEAR));

    private final String name;
    private final Duration duration;

    TwicUnit(String name, Duration estimatedDuration) {
        this.name = name;
        this.duration = estimatedDuration;
    }

    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public boolean isDurationEstimated() {
        return false;
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
    public <R extends Temporal> R addTo(R temporal, long amount) {
        return (R)temporal.plus(Math.multiplyExact(amount, duration.toHours()), ChronoUnit.HOURS);
    }

    @Override
    public long between(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return temporal1Inclusive.until(temporal2Exclusive, ChronoUnit.HOURS) / this.duration.toHours();
    }

    @Override
    public String toString() {
        return name;
    }
}
