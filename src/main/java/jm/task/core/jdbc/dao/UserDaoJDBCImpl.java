package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Connection connection = Util.getConnection();

    public void createUsersTable() {
        try (PreparedStatement statement = Util.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS User (id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(32) NOT NULL, lastName VARCHAR(32) NOT NULL, age SMALLINT NOT NULL)")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement statement = Util.getConnection().prepareStatement("DROP TABLE IF EXISTS User")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = Util.getConnection()
                .prepareStatement("INSERT INTO User(name, lastName, age) VALUES (?,?,?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
            System.out.println("User details:" + "\n" +  "name - " + name + "\n"
                    + "lastname - " + lastName + "\n" + "age - " + age + "\n" + "Added to database");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = Util.getConnection().prepareStatement("DELETE FROM User WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List <User> users = new ArrayList<>();
        try (PreparedStatement statement = Util.getConnection().prepareStatement("SELECT * FROM User")){
            ResultSet record = statement.executeQuery();
            while (record.next()) {
                String name = record.getString("name");
                String lastName = record.getString("lastName");
                byte age = record.getByte("age");
                long id = record.getLong("id");
                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (PreparedStatement statement = Util.getConnection().prepareStatement("TRUNCATE TABLE User")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

    }
}
