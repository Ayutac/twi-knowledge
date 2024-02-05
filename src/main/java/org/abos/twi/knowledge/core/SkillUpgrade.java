package org.abos.twi.knowledge.core;

import org.abos.twi.knowledge.core.publication.Chapter;

import java.util.Objects;

public record SkillUpgrade(Skill baseSkill, Skill upgradeSkill, Chapter chapter) implements Comparable<SkillUpgrade> {

    public SkillUpgrade(final Skill baseSkill, final Skill upgradeSkill, final Chapter chapter) {
        this.baseSkill = Objects.requireNonNull(baseSkill);
        this.upgradeSkill = Objects.requireNonNull(upgradeSkill);
        this.chapter = Objects.requireNonNull(chapter);
    }

    @Override
    public int compareTo(final SkillUpgrade other) {
        final int cmpBase = baseSkill.compareTo(other.baseSkill);
        if (cmpBase != 0) {
            return cmpBase;
        }
        return upgradeSkill.compareTo(other.upgradeSkill);
    }
}
