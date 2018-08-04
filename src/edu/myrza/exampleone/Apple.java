package edu.myrza.exampleone;

public class Apple{

    private String name;
    private String color;

    public Apple(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }
}