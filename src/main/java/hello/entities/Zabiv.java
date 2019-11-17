package hello.entities;


import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zabiv", schema = "hookah")
public class Zabiv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String name;
    @Transient
    private List<Tobacco> flavours = new ArrayList<>();
    @Expose
    private String flavour1;
    @Expose
    private String flavour2;
    @Expose
    private String flavour3;


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

    public List<Tobacco> getFlavours() {
        return flavours;
    }

    public void setFlavours(List<Tobacco> flavours) {
        this.flavours = flavours;
    }

    public String getFlavour1() {
        return flavour1;
    }

    public void setFlavour1(String flavour1) {
        this.flavour1 = flavour1;
    }

    public String getFlavour2() {
        return flavour2;
    }

    public void setFlavour2(String flavour2) {
        this.flavour2 = flavour2;
    }

    public String getFlavour3() {
        return flavour3;
    }

    public void setFlavour3(String flavour3) {
        this.flavour3 = flavour3;
    }
}

