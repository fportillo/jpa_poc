package br.com.almana.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.almana.domain.User;

@Stateless
public class UserService {

    @PersistenceContext(unitName="jpaPoc")
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }
    
    public User findById(Long userId) {
        return em.find(User.class, userId);
    }
    
}
