package model;

import anotations.Column;
import anotations.Id;
import anotations.Table;

@Table(name="abonents")
public class Abonent {
    @Id(name = "abonent_id")
    private Integer id;

    @Column(name = "abonent_name")
    private String name;

    public Abonent() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
