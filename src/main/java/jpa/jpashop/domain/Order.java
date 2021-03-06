package jpa.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ORDERS") // Order는 관례상 테이블 명을 ORDERS로 해주어야 한다.
public class Order extends BaseEntity{

    @Id @GeneratedValue()
    @Column(name = "ORDER_ID")
    private Long id;

   /* @Column(name = "MEMBER_ID")
    private Long memberId; // MEMBER 참조를 가져와야지 식별자 값을 그대로 가져옴 RDB 형식의 방법, 참조가 끊긴다.*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member; //--> 객체 지향스러운 코드, MEMBER와 ORDER가 연결된다.

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;
}
