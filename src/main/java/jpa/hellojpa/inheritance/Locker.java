package jpa.hellojpa.inheritance;

import javax.persistence.*;

@Entity
public class Locker {

    @Id
    @Column(name = "LOCKER_ID")
    @GeneratedValue
    private Long id;

    private String name;

    /*@OneToOne(mappedBy = "locker")
    private HelloMember member;*/
}
