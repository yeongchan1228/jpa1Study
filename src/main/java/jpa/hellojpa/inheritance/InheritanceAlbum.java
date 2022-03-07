package jpa.hellojpa.inheritance;

import javax.persistence.Entity;

@Entity
public class InheritanceAlbum extends InheritanceItem {

    private String artist;
}
