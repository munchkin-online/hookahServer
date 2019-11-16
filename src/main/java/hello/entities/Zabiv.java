package hello.entities;


import javax.persistence.*;

@Entity
@Table(name = "zabiv", schema = "hookah")
public class Zabiv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String name;
    private int numofflavours;
    private int flavour1;
    private int flavour2;
    private int flavour3;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumofflavours() {
        return numofflavours;
    }

    public void setNumofflavours(int numofflavours) {
        this.numofflavours = numofflavours;
    }

    public int getFlavour1() {
        return flavour1;
    }

    public void setFlavour1(int flavour1) {
        this.flavour1 = flavour1;
    }

    public int getFlavour2() {
        return flavour2;
    }

    public void setFlavour2(int flavour2) {
        this.flavour2 = flavour2;
    }

    public int getFlavour3() {
        return flavour3;
    }

    public void setFlavour3(int flavour3) {
        this.flavour3 = flavour3;
    }
}

