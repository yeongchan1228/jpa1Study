package jpa.jpql;

import javax.persistence.*;

@Entity
public class JpqlOrder {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    private int orderAmount;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private JpqlMember member;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private JpqlProduct product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public JpqlMember getMember() {
        return member;
    }

    public void setMember(JpqlMember member) {
        this.member = member;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public JpqlProduct getProduct() {
        return product;
    }

    public void setProduct(JpqlProduct product) {
        this.product = product;
    }
}
