package com.jdbc;


import com.mysql.fabric.jdbc.FabricMySQLDriver;

import javax.jws.soap.SOAPBinding;
import java.sql.*;

/**
 * Created by Жека on 28.03.2017.
 */
public class Main {
    private final static String Url = "jdbc:mysql://localhost:3306/avtomabiles";
    private final static String Username ="root";
    private final static String Pasword ="root";
    public static void main(String[] args) {
        Connection connection;
        try {
              Driver driver = new FabricMySQLDriver();
                 DriverManager.registerDriver(driver);

                     connection = DriverManager.getConnection(Url, Username, Pasword);
            Statement statement = connection.createStatement();
            statement.execute("Insert into users(name,age,email) values('Zheka', 19, 'zheka.13.03.1998@gmail.com');");
           int kol = statement.executeUpdate("update users set name='Artem', age=17 where id=4;");
            ResultSet result = statement.executeQuery("select * from users;");
            while (result.next()){
                User User = new User();
               User.setId(result.getInt(1));
               User.setName(result.getString(2));
               User.setAge(result.getInt(3));
              User.setEmail(result.getString(4));
                System.out.println(User);
            }
           statement.addBatch("Insert into users(name,age,email) values('Artem', 17, 'Artem4ik@gmail.com');");
            statement.addBatch("Insert into users(name,age,email) values('Roma', 17, 'Roman@gmail.com');");
             statement.addBatch("Insert into users(name,age,email) values('Dima', 18, 'Dim4ik@gmail.com');");
            // statement.executeBatch();

            connection.close();
                if (connection.isClosed()){
                    System.out.println("Соединение с базой данных закрыто!");
                }
        }
        catch (SQLException e) {
            System.err.println("Не удалось загрузить класс драйвера!");
        }
    }
}
