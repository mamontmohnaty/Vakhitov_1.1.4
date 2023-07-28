package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 18);
        System.out.println("User с именем – " + userService. + " добавлен в базу данных");
        userService.saveUser("Sidor", "Sidorov", (byte) 19);
        userService.saveUser("Petr", "Petrov", (byte) 20);
        userService.saveUser("Alexandr", "Smirnov", (byte) 21);

        List<User> allUsers = userService.getAllUsers();
        System.out.println(allUsers);

        userService.cleanUsersTable();

        userService.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
