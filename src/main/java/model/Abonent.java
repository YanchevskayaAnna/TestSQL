package model;

/**
 * Created by IT-Univer004 on 14.09.2017.
 */
@Table (name="abonents")
public class Abonent {
    private int id;
    @Column(name = "id")
    private String name;

    public Abonent() {
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
}
