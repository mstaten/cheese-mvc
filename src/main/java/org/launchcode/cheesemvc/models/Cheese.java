package org.launchcode.cheesemvc.models;

/*
** Models class
**
**/
public class Cheese {

    private String name;
    private String description;

    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Cheese(String name) {
        this(name, "");
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
}
