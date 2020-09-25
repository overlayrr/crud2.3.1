package web.dao;

import java.sql.SQLException;
import java.util.List;
import web.models.User;

public interface UserDao {

    boolean addUser(User user) throws SQLException;

    User getUserById(long id) throws SQLException;

    List<User> getAllUsers() throws SQLException;

    boolean deleteUser(long id) throws SQLException;

    boolean checkLogin(String login) throws SQLException;

}
