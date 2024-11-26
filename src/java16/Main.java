package java16;

import java16.dao.UserDao;
import java16.dao.UserDaoImpl;
import java16.models.User;
import java16.service.UserService;
import java16.service.UserServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        UserServiceImpl userService = new UserServiceImpl(userDao);


        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    1. Register
                    2. Login
                    """);
            String wort = scannerStr.nextLine();
            switch (wort) {
                case "1":
                    User user = new User();
                    userService.register(user);
                    break;
                case "2":
                    userService.login();
                    break;
                default :
                    System.out.println(" tuura  emes komanda :  " + wort);
            }

        }

    }
}