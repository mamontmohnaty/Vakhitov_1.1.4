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

        String sql = "CREATE TABLE IF NOT EXISTS `UsersTable` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(20) NOT NULL,\n" +
                "  `lastName` VARCHAR(30) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`))";

        Transaction transaction = null;

        try(Session session = Util.getSessionFactory().openSession()) {

            Query query = session.createSQLQuery(sql).addEntity(User.class);

            query.setString(1, name);
            query.setString(2, lastName);
            query.setByte(3, age);

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
    public void removeUserById(long id) {


        String sql = "TRUNCATE UsersTable";

        Transaction transaction = null;

        try(Session session = Util.getSessionFactory().openSession()) {

            Query query = session.createSQLQuery(sql).addEntity(User.class);

            query.setLong(1, id);
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
    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        String sql = "TRUNCATE UsersTable";

        Transaction transaction = null;

        try(Session session = Util.getSessionFactory().openSession()) {

            Query query = session.createSQLQuery(sql).addEntity(User.class);

            ResultSet resultSet = query.executeQuery();

            {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastname"));
                    user.setAge(resultSet.getByte("age"));

                    userList.add(user);

                }

            query.executeUpdate();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void cleanUsersTable() {

        String sql = "TRUNCATE UsersTable";

        Transaction transaction = null;

        try(Session session = Util.getSessionFactory().openSession()) {

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
}
