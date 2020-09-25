package web.dao;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class UserHibernateDao implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        EntityManager em = getEntityManager();
        if (user.getId() == null) {
            em.persist(user);
        } else {
            if (checkLogin(getUserById(user.getId()).getName())) {
                User changedUser = getUserById(user.getId());
                em.detach(changedUser);
                changedUser.setAge(user.getAge());
                changedUser.setName(user.getName());
                changedUser.setLastName(user.getLastName());
                em.merge(changedUser);
                return true;
            }
        }
        return true;
    }

    @Override
    public User getUserById(long id) {
        EntityManager em = getEntityManager();
        User user = em.find(User.class, id);
        return user;
    }


    @Override
    public List<User> getAllUsers() {
        EntityManager em = getEntityManager();
        List<User> result = em.createQuery("SELECT user from User user").getResultList();
        return result;
    }


    @Override
    public boolean deleteUser(long id) {
        if (checkLogin(getUserById(id).getName())) {
            EntityManager em = getEntityManager();
            User deletedUser = getUserById(id);
            em.remove(deletedUser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkLogin(String login) {

        EntityManager em = getEntityManager();
        List<?> tempResult =
                em.createQuery("SELECT user from User user where user.name = ?1")
                        .setParameter(1, login)
                        .getResultList();
        return tempResult.size() != 0;
    }

}

