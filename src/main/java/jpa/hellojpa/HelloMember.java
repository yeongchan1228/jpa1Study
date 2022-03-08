package jpa.hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity // JPA가 관리할 객체 지정
@Table(name = "HELLOMEMBER")
@SequenceGenerator(name = "MYSEQ",
sequenceName = "MEMBER_SEQ",
initialValue = 1, allocationSize = 50)
public class HelloMember extends HelloBaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    /*@Column(name = "TEAM_ID")
    private Long teamId;*/

    /*@OneToOne
    @JoinColumn(name = "LOKER_ID")
    private Locker locker;*/

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩
    //@ManyToOne(fetch = FetchType.EAGER) // 즉시 로딩 -> HelloMember를 찾을 때 HelloTeam도 항상 같이 가져온다 -> Join
    @JoinColumn(name = "TEAM_ID")
    private HelloTeam team;

    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeAddress;

    @ElementCollection // PK가 생기지 않아 데이터 변경시 구분할 수 없어 값 변경시 모든 데이터 다시 넣기 등이 이루어진다
    // 따라서 1:N 매핑으로 풀어야 한다.
    @CollectionTable(name = "FAVORITE_FOOD",
            joinColumns = @JoinColumn(name = "MEMBER_ID")
    ) // 매핑 정보
    @Column(name = "FOOD")
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection // 컬렉션을 요소로 사용하기 위해 필요 -> 컬렉션은 테이블에 매핑될 수 없기 떄문에 따로 테이블을 만들어 줘야 한다.
    @CollectionTable(name = "ADDRESS", // 컬렉션 요소를 담을 테이블 이름과 연관 요소인 MEMBER_ID -> 조인 컬럼을 넣어 준다.
            joinColumns = @JoinColumn(name = "MEMBER_ID ")
    )
    private List<Address> addresseHistory = new ArrayList<>();

    /**
     * 값 타입 컬렉션 실무에서 적용하는 방법
     * 1:N 방식으로 풀기
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true )
     // cascade나 orphanRemoval를 해주는 이유는 외래키는
    // EntityAddress에 존재하는데 이쪽에서 참조를 거는 것이므로 이쪽의 변경 사항이 저쪽에도 영향을 주게 하기 위해서
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory2 = new ArrayList<>();

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "city", column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "WORK_ZIPCODE"))
    })
    private Address workAddress;

    public HelloMember() {
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

    public HelloTeam getTeam() {
        return team;
    }

    public void changeTeam(HelloTeam team) { // 연관관계 편의 메소드
        this.team = team;
        team.getMembers().add(this); // 양방향 관계일 때 순수 자바 객체를 고려하여 setTeam을 할때 team 멤버에도 넣어주는 것이 좋다.
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address> getAddresseHistory() {
        return addresseHistory;
    }

    public void setAddresseHistory(List<Address> addresseHistory) {
        this.addresseHistory = addresseHistory;
    }

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }

    /* @Id // PK를 알려줌
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
    }*/

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

    public void setTeam(HelloTeam team) {
        this.team = team;
    }
}
