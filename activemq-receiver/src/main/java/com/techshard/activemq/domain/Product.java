package com.techshard.activemq.domain;

public class Product {
    private String id;
    private String name;
    private int totalContracted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalContracted() {
        return totalContracted;
    }

    public void incrementTotalContracted() {
        this.totalContracted++;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", totalContracted=" + totalContracted +
                '}';
    }
}
