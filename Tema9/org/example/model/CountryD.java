package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(
        name = "CountryD.findByName",
        query = "SELECT c FROM CountryD c WHERE c.name LIKE :namePattern"
)
public class CountryD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String code;

    @ManyToOne
    @JoinColumn(name = "continent")
    private ContinentD continent;


    public CountryD() {}

    public CountryD(String name, String code, ContinentD continent) {
        this.name = name;
        this.code = code;
        this.continent = continent;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ContinentD getContinent() {
        return continent;
    }

    public void setContinent(ContinentD continent) {
        this.continent = continent;
    }

    @Override
    public String toString() {
        return "CountryD{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", continent=" + continent +
                '}';
    }
}