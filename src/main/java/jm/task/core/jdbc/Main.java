package jm.task.core.jdbc;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();

        UserDao user = new UserDaoJDBCImpl();

        user.createUsersTable();

        user.saveUser("John", "Wik", (byte) 39);
        user.saveUser("Zaur", "Tregulov", (byte) 32);
        user.saveUser("Sergey", "Briliov", (byte) 41);
        user.saveUser("Mason", "Capefell", (byte) 42);

        user.removeUserById(4);

        user.getAllUsers();

        user.cleanUsersTable();

        user.dropUsersTable();// реализуйте алгоритм здесь
    }
}
