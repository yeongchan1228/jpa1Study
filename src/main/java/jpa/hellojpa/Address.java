package jpa.hellojpa;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Access(AccessType.FIELD)
public class Address {

    private String city;
    private String street;
    private String zipcode;

    /**
     * Address 임베디드 타입을 불변 객체로 만들어서 side effect가 발생하는 것을 막는다.
     * 불변 객체는 setter가 없고 생성자로 값을 할당하면 죽을 때까지 그 값을 유지하는 객체이다.
     * 필드 하나의 값을 바꾸기 위해서는 새로운 객체를 다시 만들어서(값을 바꾼다) 바꿔준다.
     * 즉, 임베디드 타입은 값 타입이기 때문에 불변으로 만들어 주어야 한다.
     * 대표적인 불변 객체는 String, Integer 등이 있다.
     */

    public Address(){}

    public Address(String city, String street, String zipcode){ // 생성자로 값을 할당 후 변경 X -> setter 선언 X
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    /**
     * 우리가 정의한 값 타입 역할을 하는 객체의 동등성 비교를 위해 오버라이드 한다. 게터 세터 만드는 것처럼  생성
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}
