package pl.coderslab.models;

import pl.coderslab.dao.UserDao;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //User user = new User("Kamil","ka@gmianl.com","qwerty");
        //UserDao userDao = new UserDao();
        //System.out.println(user);
        //user= userDao.create(user);
        //System.out.println(user);

        //User user2 = userDao.read(2);
        //User user3 = userDao.read(123);
        //System.out.println(user2);
        //System.out.println(user3);
        //tu user2 jest niezsynchronizowany
        //user2.setUserName("Kamila");
        //System.out.println(user2);
        //po update bedzie zsynchronizowany
        //userDao.update(user2);
        //System.out.println(user2);
        //userDao.delete(1);
        //User newUser1 = new User("qqq","sdf","www");
        //newUser1 = userDao.create(newUser1);
        //User[] all = userDao.findAll();
        //for(User users : all){
        //    System.out.println(users);
        //}
        logIn();

    }

    public static void logIn(){
        Scanner scanner = new Scanner(System.in);
        boolean login=false;
        while(login!=true){
            System.out.print("Zalogowac jako admin czy uzytkownik?: ");
            String logType = scanner.nextLine();

            if("uzytkownik".equals(logType)){
                login=true;
            }else if("admin".equals(logType)){
                login=true;
                int wrongPasswordCounter=0;
                while(wrongPasswordCounter<3) {
                    System.out.print("Podaj haslo: ");
                    String adminPassword = scanner.nextLine();
                    if ("admin".equals(adminPassword)) {
                        wrongPasswordCounter=3;

                        adminOptions();
                    } else {
                        System.out.println("Bledne haslo. Pozostaly ci "+(3-wrongPasswordCounter-1)+" proby");
                        wrongPasswordCounter++;
                    }
                }
            }
        }
    }

    public static void adminOptions(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jestes zalogowany jako admin.");
        System.out.println("Jezeli chcesz:");
        System.out.println("zarzadzac uzytkownikami wybierz 1");
        System.out.println("zarzadzac zadaniami wybierz 2");
        System.out.println("zarzadzac grupami wybierz 3");
        System.out.println("Przypisac zadanie wybierz 4");
        System.out.print("Twoj wybor:");

        while(!scanner.hasNextInt()){
            String tmp=scanner.nextLine();
            System.out.print("Podaj poprawna tresc: ");
        }
        int choice = scanner.nextInt();
        boolean falseChoice = false;
        while(falseChoice==false){
        switch (choice) {
            case 1: {
                userAdminister();
                falseChoice = true;
                break;
            }
            case 2: {
                System.out.println("2");
                falseChoice = true;
                break;
            }
            case 3: {
                System.out.println("3");
                falseChoice = true;
                break;
            }
            case 4: {
                System.out.println("4");
                falseChoice = true;
                break;
            }
            default: {
                System.out.print("Niepoprawna cyfra. Podaj nowa odpowiedz:");
                choice =scanner.nextInt();
            }
            }
        }
    }

    public static void userAdminister(){
        UserDao userDao = new UserDao();
        User user=new User();
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        String choice = null, userName,email,password;
        while(!"quit".equals(choice)){
            User[] all = userDao.findAll();
            for(User users : all){
                System.out.println(users);
            }
            System.out.print("Wybierz jedna z opcji(add, edit, delete, quit): ");
            choice =scanner.nextLine();

            if("add".equals(choice)){
                System.out.print("Podaj nazwe uzytkownika:");
                userName = scanner.nextLine();
                System.out.print("Podaj adres e-mail:");
                email = scanner.nextLine();
                System.out.print("Podaj haslo:");
                password = scanner.nextLine();
                User user2 = new User(userName,email,password);
                userDao.create(user2);
            }else if("edit".equals(choice)){
                System.out.print("Podaj nr id dla ktorego chcesz zmienic dane:");
                int id=scanner.nextInt();
                user=userDao.read(id);
                System.out.println("Zmiana nazwy wybierz 1");
                System.out.println("Zmiana e-mailu wybierz 2");
                System.out.println("Zmiana hasla wybierz 3");
                String choice2=scanner2.nextLine();
                if("1".equals(choice2)){
                    System.out.print("Podaj nowa nazwe:");
                    userName=scanner2.nextLine();
                    user.setUserName(userName);
                    userDao.update(user);

                }else if("2".equals(choice2)){
                    System.out.print("Podaj nowy email:");
                    email=scanner2.nextLine();
                    user.setEmail(email);
                    userDao.update(user);

                }else if("3".equals(choice2)){
                    System.out.print("Podaj nowe haslo:");
                    password=scanner2.nextLine();
                    user.setPassword(password);
                    userDao.update(user);
                }
            }else if("delete".equals(choice)){
                System.out.print("Podaj numer id, aby usunac uzytkownika:");
                int id=scanner.nextInt();
                userDao.delete(id);
            }
        }
    }
}