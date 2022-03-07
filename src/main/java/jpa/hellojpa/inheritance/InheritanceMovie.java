package jpa.hellojpa.inheritance;

import javax.persistence.Entity;

@Entity
public class InheritanceMovie extends InheritanceItem {

    private String director;
    private String actor;
}
