package br.com.almana;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.almana.domain.User;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPoc");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            User johnDoe = new User();
            johnDoe.setName("John Doe");
            johnDoe.setAge(33);

            em.persist(johnDoe);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            emf.close();
        }

        System.out.println("It is over");
    }
}
