package jpabook.jpashop;

import jpabook.jpashop.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            /**
             * Inheritance 예제
             */
            /*
            InheritanceBook book = new Book();
            //자식 필드
            book.setAuthor("aaaaa");
            book.setIsbn("bbbb");

            //부모 필드
            book.setName("Spring JPA");
            book.setPrice(10000);

            em.persist(book);

            em.flush();
            em.clear();

            Book findBook = em.find(Book.class, book.getId());
            */

            /**
             * MappedSuperclass 예제 -> 공통으로 사용하는 필드를 새로운 클래스에 만들고 @MappedSuperclass를 붙여 생성하면
             * 다른 클래스는 위 클래스를 상속받아 어디서든 사용할 수 있다.
             * 위 클래스는 테이블로 만들 필요가 없으므로 추상 클래스로 생성하는 것이 좋다.
             */
            /*
            HelloMember member = new HelloMember();
            member.setName("User1");
            member.setCreatedBy("choi");
            member.setCreatedDate(LocalDateTime.now());

            em.persist(member);

            em.flush();
            em.clear();
            */

            /**
             * JPA Shop Inheritance 확인
             */
            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("hi");

            em.persist(book);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
