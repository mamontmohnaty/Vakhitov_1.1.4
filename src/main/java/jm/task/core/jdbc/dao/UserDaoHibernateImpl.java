package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS `UsersTable` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(20) NOT NULL,\n" +
                "  `lastName` VARCHAR(30) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`))";

        Transaction transaction = null;

        try(Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Query query = session.createSQLQuery(sql).addEntity(User.class);

            query.executeUpdate();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {


        String sql = "DROP TABLE IF EXISTS UsersTable";

        Transaction transaction = null;

        try(Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Query query = session.createSQLQuery(sql).addEntity(User.class);

            query.executeUpdate();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Transaction transaction = null;

        try(Session session = Util.getSessionFactory().openSession()) {

            User user = new User(name, lastName, age);

            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {

        Transaction transaction = null;

        try(Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            User user = session.get(User.class, id);

            session.delete(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


    }

    @Override
    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();
        Transaction transaction = null;

        try(Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            userList = session.createQuery("from User").getResultList();
            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {

        Transaction transaction = null;

        try(Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.createQuery("delete User").executeUpdate();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}
