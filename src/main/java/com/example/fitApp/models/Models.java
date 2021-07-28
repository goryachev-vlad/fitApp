package com.example.fitApp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Models {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   public Long id;
    private String  exercise;
    private int approaches;
    private Double time;

    public Models(String exercise, int approaches, Double time) {
        this.exercise = exercise;
        this.approaches = approaches;
        this.time = time;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public int getApproaches() {
        return approaches;
    }

    public void setApproaches(int approaches) {
        this.approaches = approaches;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public Models() {
    }
}
