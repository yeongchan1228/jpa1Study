package jpa;

import jpa.hellojpa.Child;
import jpa.hellojpa.HelloMember;
import jpa.hellojpa.HelloTeam;
import jpa.hellojpa.Parent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
            /*
            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("hi");

            em.persist(book);
            */

            /**
             * 지연 로딩, 즉시 로딩 예제
             */

/*
//            HelloMember member = em.find(HelloMember.class, 1L);
//
//            printMember(member); // 지연 로딩이 좋음
//            printMemberAndTeam(member); // 즉시 로딩이 좋음

            HelloTeam team = new HelloTeam();
            team.setName("TeamA");
            em.persist(team);

            HelloMember member = new HelloMember();
            member.setName("UserA");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

//            HelloMember findMember = em.find(HelloMember.class, member.getId());
//            System.out.println("m = " + findMember.getTeam().getClass()); // fetch = LAZY로 Team은 지연 로딩에 의해 프록시만 가져옴
//
//            System.out.println("=========================");
//            findMember.getTeam().getName(); // Team의 무언가를 건드려야지 조회 쿼리가 나옴
//            System.out.println("=========================");

            //즉시 로딩의 JPQL N+1문제
//            List<HelloMember> result = em.createQuery("select m from HelloMember m", HelloMember.class)
//                                            .getResultList();
            // SQL : select * from HelloMember -> 즉시 로딩은 HelloMember의 Team을 채워야 하기 때문에
            // HelloMember 하나 당 HelloTeam을 찾기 위한 추가 SQL이 생성된다.
            // 즉, HelloMember 전부를 찾는 SQL 1개, HelloMember당 Team을 찾기 위한 SQL N개
            // 지연 로딩시 Team의 프록시를 가져옴. -> SQL 생성 X

            // 이 문제를 해결하기 위해 join fetch 사용 -> SQL 하나로 team을 전부 채울 수 있다.
            HelloTeam team2 = new HelloTeam();
            team2.setName("TeamB");
            em.persist(team2);

            HelloMember member2 = new HelloMember();
            member2.setName("UserB");
            member2.setTeam(team2);
            em.persist(member2);

            em.flush();
            em.clear();

            List<HelloMember> result = em.createQuery("select m from HelloMember m join fetch m.team", HelloMember.class)
                    .getResultList();
*/


            /**
             * 프록시 예제
             */
            /*
            HelloMember member1 = new HelloMember();
            member1.setName("hello");

            HelloMember member2 = new HelloMember();
            member2.setName("hello");

            em.persist(member1);
            em.persist(member2);

            em.flush();
            em.clear();

            HelloMember findReference1 = em.find(HelloMember.class, member1.getId()); // 가짜 참조 객체를 만들어 놓는다. -> 프록시
            HelloMember findReference2 = em.getReference(HelloMember.class, member2.getId()); // 가짜 참조 객체를 만들어 놓는다. -> 프록시
            // 프록시는 가짜 참조 객체를 만들어 놓고 이를 사용하여 get, set할 때 참조할 객체를 db에서 찾는 쿼리를 날린다.
            System.out.println("findReference1 == findReference2 "
                    + (findReference1.getClass() == findReference2.getClass())); // false
            //프록시 객체와 entity객체는 다른 Class이다.

            System.out.println("(findReference1 instanceof HelloMember) = " + (findReference1 instanceof HelloMember));
            System.out.println("(findReference1 instanceof HelloMember) = " + (findReference2 instanceof HelloMember));
            //따라서 프록시가 넘어올지 실제 객체가 넘어올지 모르기 때문에 타입 비교는 instanceof를 사용해야 한다.

            HelloMember findReference3 = em.getReference(HelloMember.class, member1.getId());
            //영속성 컨텍스트에 있는 객체를 프록시로 만드려고 할 때는 프록시로 생성되지 않고 영속성 컨텍스트에 존재하는 객체가 반환된다.
            System.out.println("(findReference3.getClass() == findReference1.getClass()) = "
                    + (findReference3.getClass() == findReference1.getClass())); // true
            //즉
            System.out.println("(findReference3 == findReference1) = " + (findReference3 == findReference1)); // true 아예 같은 객체

            // 하지만 먼저 Reference를 받고 find를 해도 find 결과는 프록시가 나온다.
            // 자바는 항상 a == a를 맞추기 때문에 처음 a타입에 맞춘다.
            // 프록시가 영속 컨텍스트에 없을 때(준영속성 상태일 때) 프로시를 통한 조회를 하면 오류가 발생한다.

            //프록시 인스턴스가 초기화 됐는지 확인
            System.out.println("emf.getPersistenceUnitUtil().isLoaded() findReference2 = "
                    + emf.getPersistenceUnitUtil().isLoaded(findReference2)); // false 초기화가 안됐다.
                    */

            /**
             * cascade 예제
             */

            /*
            Child child = new Child();
            child.setName("ChildA");

            Child child2= new Child();
            child.setName("ChildB");

            Parent parent = new Parent();
            parent.setName("ParentA");
            parent.addChild(child);
            parent.addChild(child2);
            em.persist(parent);
            //em.persist(child); -> cascade를 적용하면 지워도 된다.
            //em.persist(child2); -> cascade를 적용하면 persist를 안해도 된다.
            */

            /**
             * 고아 삭제 예제
             */

            /*
            Child child = new Child();
            child.setName("ChildA");

            Child child2= new Child();
            child2.setName("ChildB");

            Parent parent = new Parent();
            parent.setName("ParentA");
            parent.addChild(child);
            parent.addChild(child2);
            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);

            em.remove(findParent); // 부모를 죽이면 그 안의 자식도 다 죽는다. -> 사용에 주의
            */

            /* cascade나 orphanRemoval은 자식 엔티티를 본인만 참조하고 관리할 때 사용해야 한다. -> 나만 자식 엔티티를 참조해야 한다.*/


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

    private static void printMember(HelloMember member) { // 지연 로딩이 적용되면 좋은 예
        System.out.println("name = " + member.getName());
    }

    private static void printMemberAndTeam(HelloMember member) { // 즉시 로딩이 적용되면 좋은 예
        System.out.println("name = " + member.getName());

        System.out.println("team = " + member.getTeam());
    }
}
