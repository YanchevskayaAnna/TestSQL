package model;

@Table (name="abonents")
public class Abonent {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
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
