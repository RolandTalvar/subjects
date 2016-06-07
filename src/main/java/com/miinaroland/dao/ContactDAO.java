package com.miinaroland.dao;

import com.miinaroland.model.Contact;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rolandtalvar on 06/06/16.
 */
@Repository
public class ContactDAO {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/dkf6fokg3qu1e";

    static final String USER = "xeaizttgmawsfi";
    static final String PASS = "YaUL6jwiwodWgre75rugIUyRac";

    List<Contact> contacts;

    public List<Contact> findAll(long subjectType, long subject) {
        contacts = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            statement = connection.createStatement();
            String sql;
            sql = "SELECT contact, subject_fk, contact_type_fk, value_text, orderby, subject_type_fk, note FROM contact " +
                    "WHERE subject_type_fk=" + subjectType + " AND subject_fk=" + subject + " ORDER BY orderby;";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                Contact contact = new Contact();

                contact.setContact(rs.getLong("contact"));
                contact.setSubjectFk(rs.getLong("subject_fk"));
                contact.setContactTypeFk(rs.getLong("contact_type_fk"));
                contact.setValueText(rs.getString("value_text"));
                contact.setOrderby(rs.getLong("orderby"));
                contact.setSubjectTypeFk(rs.getLong("subject_type_fk"));
                contact.setNote(rs.getString("note"));

                contacts.add(contact);

            }

            rs.close();
            statement.close();
            connection.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return contacts;
    }

    public List<Contact> findAllOfType(long subjectType, long subject, long contactType) {
        contacts = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            statement = connection.createStatement();
            String sql;
            sql = "SELECT contact, subject_fk, contact_type_fk, value_text, orderby, subject_type_fk, note FROM contact " +
                    "WHERE subject_type_fk=" + subjectType + " AND subject_fk=" + subject + " AND contact_type_fk=" + contactType +  " ORDER BY orderby;";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                Contact contact = new Contact();

                contact.setContact(rs.getLong("contact"));
                contact.setSubjectFk(rs.getLong("subject_fk"));
                contact.setContactTypeFk(rs.getLong("contact_type_fk"));
                contact.setValueText(rs.getString("value_text"));
                contact.setOrderby(rs.getLong("orderby"));
                contact.setSubjectTypeFk(rs.getLong("subject_type_fk"));
                contact.setNote(rs.getString("note"));

                contacts.add(contact);

            }

            rs.close();
            statement.close();
            connection.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return contacts;
    }

    public Long getLastId() {
        contacts = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            statement = connection.createStatement();
            String sql;
            sql = "SELECT contact FROM contact ORDER BY contact DESC LIMIT 1;";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                return rs.getLong("contact");
            }

            rs.close();
            statement.close();
            connection.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return null;
    }

    public void insertOrUpdate(Contact contact) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql= "select * from insertOrUpdateContact(?,?,?,?,?,?,?);";

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,contact.getContact());
            preparedStatement.setLong(2,contact.getSubjectFk());
            preparedStatement.setLong(3,contact.getContactTypeFk());
            preparedStatement.setString(4,contact.getValueText());
            preparedStatement.setLong(5,contact.getOrderby());
            preparedStatement.setLong(6, contact.getSubjectTypeFk());
            preparedStatement.setString(7, contact.getNote());

            preparedStatement.executeQuery();

            preparedStatement.close();
            connection.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

}
