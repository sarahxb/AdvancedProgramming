package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(
        name = "ContinentD.findByName",
        query = "SELECT c FROM ContinentD c WHERE c.name LIKE :namePattern"
)
public class ContinentD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;


    public ContinentD() {}

    public ContinentD(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ContinentD{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}