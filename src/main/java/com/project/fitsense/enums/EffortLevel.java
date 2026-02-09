package com.project.fitsense.enums;

public enum EffortLevel {
    LOW(0.8),
    MEDIUM(1.0),
    HIGH(1.2);

    private final double multiplier;

    EffortLevel(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
