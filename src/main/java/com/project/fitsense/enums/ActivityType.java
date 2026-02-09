package com.project.fitsense.enums;

public enum ActivityType {

    RUNNING(9.8),
    WALKING(4.3),
    CYCLING(6.8),
    SWIMMING(8.3),
    WEIGHT_TRAINING(5.0),
    YOGA(2.5),
    HIIT(9.5),
    CARDIO(7.0),
    STRETCHING(2.3),
    OTHER(0);

    private double met;

    ActivityType(double met) {
        this.met = met;
    }

    public double getMet() {
        return met;
    }


}
