package hello.entities;


import javax.persistence.*;

@Entity
@Table(name = "zabivs", schema = "hookah")
public class Zabiv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_zabiv;

    private String name;
    private int numofflavours;
    private int flavour1;
    private int flavour2;
    private int flavour3;

    public int getId_zabiv() {
        return id_zabiv;
    }

    public void setId_zabiv(int id_zabiv) {
        this.id_zabiv = id_zabiv;
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
