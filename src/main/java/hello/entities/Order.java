package hello.entities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "order", schema = "hookah")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private int cost;
    @Column(name = "guestname")
    private String guestName;
    private String comments;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<Zabiv> order = new ArrayList<>();
    @Expose
    private String status;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<Zabiv> getOrder() {
        return order;
    }

    public void setOrder(List<Zabiv> order) {
        this.order = order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
