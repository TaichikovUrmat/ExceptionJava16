package java16.service;

import java16.dao.UserDaoImpl;
import java16.db.DataBase;
import java16.enums.Gender;
import java16.models.User;

import java.util.Arrays;
import java.util.Scanner;

public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao;

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    private final DataBase dataBase = new DataBase();
    private final Scanner scannerStr = new Scanner(System.in);
    private final Scanner scannerInt = new Scanner(System.in);

    @Override
    public void register(User newUser) {

        try {

            System.out.println("write id : ");
            newUser.setId(scannerInt.nextLong());
            /// name
            System.out.println("write  userName :");
            String name = scannerStr.nextLine();
            boolean isbloc = false;
            for (User user : dataBase.users) {
                if (user.getUserName().equals(name)) {
                    isbloc = true;
                    throw new RuntimeException(" мындай  " + name + " уже бар , башка  name  жаз ! : ");
                }
            }
            if (!isbloc) {
                newUser.setUserName(name);
            }
            ///  phoneNumber
            System.out.println("write phoneNumber :  (+996 -  узундугу 13 болсун ))");
            String phoneNumber = scannerStr.nextLine();
            if (!phoneNumber.startsWith("+996") && phoneNumber.length() != 13) {
                throw new RuntimeException("Телефон номер туура жмес форматта ! ");
            }
            newUser.setPhoneNumber(phoneNumber);

            //  TODO   gmail
            System.out.println("write gmail -> ( @gmail.com ) : ");
            String gmail = scannerStr.nextLine();
            if (!gmail.contains("@gmail.com")) {
                throw new RuntimeException(" gmail туура жаз ");
            }
            newUser.setGmail(gmail);
            // TODO password
            System.out.println("write password : ");
            String password = scannerStr.nextLine();
            if (password.length() < 4) {
                throw new RuntimeException(" узундугу 4 аз болбосун");
            }
            newUser.setPassword(password);
            /// TODO gender

            System.out.println("write gender : ( 1.FEMALE  \n  2. MALE)");
            String gender = scannerStr.nextLine();

//   TODO         switch (gender) {
//                case "1", "FEMALE":
//                    newUser.setGender(Gender.FEMALE);
//                    break;
//                case "2" , "MALE":
//                    newUser.setGender(Gender.MALE);
//                    break;
//            }
            if (gender.equals("FEMALE") || gender.equals("1")) {
                newUser.setGender(Gender.FEMALE);
            } else if (gender.equals("2")) {
                newUser.setGender(Gender.MALE);
            } else {
                throw new RuntimeException("(1,2 ) цифралардан бирин  танда !!  ");
            }

            System.out.println("Success users  ! ");
            System.out.println(Arrays.toString(dataBase.users));
            userDao.register(newUser);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void login() {
        userDao.login();
    }


}
