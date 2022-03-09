package jpa.jpql;

import javax.persistence.*;

@Entity
public class JpqlMember {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;
    private int age;
    private Address address;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private JpqlTeam team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public JpqlTeam getTeam() {
        return team;
    }

    public void setTeam(JpqlTeam team) {
        this.team = team;
    }
}
