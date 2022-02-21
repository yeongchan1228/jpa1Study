package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity // JPA가 관리할 객체 지정
@Table(name = "JpaMember")
@SequenceGenerator(name = "MYSEQ",
sequenceName = "MEMBER_SEQ",
initialValue = 1, allocationSize = 50)
public class Member {

    @Id // PK를 알려줌
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MYSEQ") // DB의 방언에 맞게 id 값을 자동으로 생성한다. @GeneratedValue가 없으면 id값은 우리가 넣어주어야 한다.
    private Long id;

    @Column(name = "name")
    private String username;

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

    /*private Integer age; // int형은 not null까지, Integer은 null 허용

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    *//*@Temporal(TemporalType.TIMESTAMP)
        private Date createDate;*//*
    private LocalDate createDate; // 년/월

    *//*@Temporal(TemporalType.TIMESTAMP) // TIMESTAMP 날짜/시간, DATE 날짜만, TIME 시간만
    private Date lastModifiedDate;*//*
    private LocalDateTime lastModifiedDate; // 날짜/시간

    @Lob // VARCHER보다 큰 값을 넣고 싶을 때
    private String descriprion;

    @Transient // 디비의 컬럼과 매칭 X
    private int temp;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }



    public String getDescriprion() {
        return descriprion;
    }

    public void setDescriprion(String descriprion) {
        this.descriprion = descriprion;
    }*/
}
