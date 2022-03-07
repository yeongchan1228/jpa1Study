package jpa.hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HelloTeam extends HelloBaseEntity {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team") // Member 도메인의 Team team 과 관련있다(연결되어 있다). -> 읽기 전용, 연관 관계의 주인은 Member의 team이다.
    private List<HelloMember> members = new ArrayList<>();

    public List<HelloMember> getMembers() {
        return members;
    }

    public void setMembers(List<HelloMember> members) {
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
