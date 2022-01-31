package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;



import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Sasdkfh", "Pim", (byte) 78);
        userService.saveUser("Yesllh", "Pim", (byte) 45);
        userService.saveUser("HFl", "Pim", (byte) 55);
        userService.saveUser("IHF", "Pim", (byte) 21);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
