package pl.coderslab.models;

import pl.coderslab.dao.UserDao;

public class App {
    public static void main(String[] args) {
        User user = new User("Kamil","ka@gmianl.com","qwerty");
        UserDao userDao = new UserDao();
        //System.out.println(user);
        //user= userDao.create(user);
        //System.out.println(user);

        User user2 = userDao.read(2);
        User user3 = userDao.read(123);
        //System.out.println(user2);
        //System.out.println(user3);
        //tu user2 jest niezsynchronizowany
        //user2.setUserName("Kamila");
        //System.out.println(user2);
        //po update bedzie zsynchronizowany
        //userDao.update(user2);
        //System.out.println(user2);
        //userDao.delete(1);
        User newUser1 = new User("qqq","sdf","www");
        newUser1 = userDao.create(newUser1);
        User[] all = userDao.findAll();
        for(User users : all){
            System.out.println(users);
        }

    }
}