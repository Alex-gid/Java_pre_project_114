package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();

        userDao.saveUser("Иван", "Иванов", (byte) 25);
        userDao.saveUser("Петр", "Петров", (byte) 30);
        userDao.saveUser("Сидор", "Сидоров", (byte) 22);
        userDao.saveUser("Владыка", "Владыкин", (byte) 28);

        for (User user : userDao.getAllUsers()) {
            System.out.println(user);
        }

        userDao.cleanUsersTable();
        System.out.println();
        userDao.dropUsersTable();
    }
}
