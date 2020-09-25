package web.services;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.models.User;

@Service
public class UserService {

    @Autowired
    private UserDao dao;

    @Transactional
    public void addUser(User user)
            throws SQLException {
        dao.addUser(user);
    }

    @Transactional(readOnly = true)
    public User getUserById(long id) throws SQLException {
        return dao.getUserById(id);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() throws SQLException {
        return dao.getAllUsers();
    }

    @Transactional
    public boolean deleteUser(long id) throws SQLException {
        return dao.deleteUser(id);
    }
}