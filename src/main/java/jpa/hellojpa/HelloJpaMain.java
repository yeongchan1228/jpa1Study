package jpa.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJpaMain {
    public static void main(String[] args) {
//      애플리케이션 로딩 시점에 한 개만 생성되어야 한다.
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

//      실제 DB에 접근하는, 트랜잭션 단위가 실행되면, 쿼리를 날릴때마다 만들어줘야함
//      한번 사용하고 나면 반드시 닫아줘야한다.
                EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜잭션 시작

        //code 작성
        try {
            /*Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member); // 데이터 저장*/


           /* Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName()); // 데이터 찾기*/

            /*Member findMember = em.find(Member.class, 2L);
            em.remove(findMember); // 삭제*/

            /*Member findMember = em.find(Member.class, 1L);
            findMember.setName("memberA"); // 수정, 커밋되기 전 변경된 사항이 있으면 JPA가 알아서 Update 쿼리를 날린다.*/

            /*List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(2)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member = " + member.getName());
            }*/

//            Member member1 = new Member();
//            member1.setUsername("D");
//            Member member2 = new Member();
//            member2.setUsername("E");
//            Member member3 = new Member();
//            member3.setUsername("F");

//            em.persist(member1);
//            em.persist(member2);
//            em.persist(member3);


            HelloMember member = new HelloMember();
            member.setName("memberA");
            em.persist(member);

            HelloTeam team = new HelloTeam();
            team.setName("TeamA");
//            team.getMembers().add(member); // 오류 발생, 연관관계의 주인은 Member의 Team team 이다.
            em.persist(team);

            member.changeTeam(team);

            em.flush();
            em.clear();




/*            양방향*/
           /* Member findMember = em.find(Member.class, member.getId());
//            Team findTeam = findMember.getTeam();
//            System.out.println("findTeam = " + findTeam.getName());
            List<Member> members = findMember.getTeam().getMembers(); // 양방향 설정

            for (Member m : members) {
                System.out.println("team_memberName = " + m.getName());
            }
*/
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close(); // 하나의 트랜잭션이 이루어지면 반드시 닫아야 한다.
        }

        emf.close();
    }
}
