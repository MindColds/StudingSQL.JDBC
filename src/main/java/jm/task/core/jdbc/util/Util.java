package jm.task.core.jdbc.util;

public class Util {
    private static final Connection connection;

    static {
        try {
            String URL = "jdbc:mysql://localhost:3306/mysql";
            String userName = "root";
            String password = "DragonSerg";
            connection = DriverManager.getConnection(URL, userName, password);
            connection.setAutoCommit(false);
            connection.commit();
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public static Connection getConnection(){
        return connection;
    }
// реализуйте настройку соеденения с БД
}
