package com.selenium.pojo;

/**
 * Created by Rayant on 11.04.2017.
 */
public class Alias {


    private String name;

    private Long id;



    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Alias(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alias)) return false;

        Alias alias = (Alias) o;

        return getName().equals(alias.getName());

    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return "Alias{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
