package hello.entities;

import com.google.gson.annotations.Expose;
//import lombok.Getter;
//import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "hookah")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String username;
    private String password;
    @Column(name = "email")
    private String mail;
    private boolean active;
    @Expose
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setEmail(String mail) {
        this.mail = mail;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
