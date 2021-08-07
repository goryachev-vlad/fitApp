package com.example.fitApp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Models {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    private String exercise;
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

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public Models() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Models models = (Models) o;
        return id == models.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
