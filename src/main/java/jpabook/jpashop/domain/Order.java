package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id @GeneratedValue()
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "MEMBER_ID")
    private Long memberId; // MEMBER 참조를 가져와야지 식별자 값을 그대로 가져옴 RDB 형식의 방법, 참조가 끊긴다.
//  private Member member; --> 객체 지향스러운 코드, MEMBER와 ORDER가 연결된다.
    
    
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
