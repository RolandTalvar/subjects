package com.miinaroland.dao;

import com.miinaroland.model.ContactType;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rolandtalvar on 06/06/16.
 */
@Repository
public class ContactTypeDAO {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/dkf6fokg3qu1e";

    static final String USER = "xeaizttgmawsfi";
    static final String PASS = "YaUL6jwiwodWgre75rugIUyRac";

    List<ContactType> contactTypes;

    public List<ContactType> findAll() {
        contactTypes = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            statement = connection.createStatement();
            String sql;
            sql = "SELECT contact_type, type_name FROM contact_type;";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                ContactType contactType = new ContactType();

                contactType.setContactType(rs.getLong("contact_type"));
                contactType.setTypeName(rs.getString("type_name"));

                contactTypes.add(contactType);

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

        return contactTypes;
    }

}
