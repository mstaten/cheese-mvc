package org.launchcode.cheesemvc.models;

import javax.validation.constraints.*;

/*
** Models class
**/
public class Cheese {

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

    @NotNull
    @Digits(integer = 1, fraction = 0, message = "Must be integer between 1 and 5")
    @Min(value = 1, message = "Must be integer between 1 and 5")
    @Max(value = 5,message = "Must be integer between 1 and 5")
    private int rating;

    private CheeseType type;
    private int cheeseId;
    private static int nextId;

    public Cheese(String name, String description, CheeseType type) {
        this();
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Cheese() {
        cheeseId = nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        this.name = aName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        this.description = aDescription;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public CheeseType getType() {
        return type;
    }

    public void setType(CheeseType type) {
        this.type = type;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Cheese{" + name + " : " + description + "}";
    }
}
