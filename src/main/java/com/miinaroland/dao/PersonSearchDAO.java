package com.miinaroland.dao;

import com.miinaroland.model.Person;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rolandtalvar on 06/06/16.
 */
@Repository
public class PersonSearchDAO {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/dkf6fokg3qu1e";

    static final String USER = "xeaizttgmawsfi";
    static final String PASS = "YaUL6jwiwodWgre75rugIUyRac";

    List<Person> personList;

    public List<Person> search(String firstName, String lastName, String identityCode, java.sql.Date birthDate) {
        personList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            statement = connection.createStatement();
            String sql;
            sql = "SELECT * FROM person WHERE ";
            if (firstName != null) {
                sql = sql.concat(" UPPER(first_name) LIKE UPPER('" + firstName + "%') AND ");
            }
            if (lastName != null) {
                sql = sql.concat(" UPPER(last_name) LIKE UPPER('" + lastName + "%') AND ");
            }
            if (identityCode != null) {
                sql = sql.concat(" UPPER(identity_code) LIKE UPPER('" + identityCode + "%') AND ");
            }
            if (birthDate != null) {
                sql = sql.concat(" UPPER(birth_date) LIKE UPPER('" + birthDate + "%') AND ");
            }

            if (sql.endsWith("AND ")) {
                sql = sql.substring(0, sql.length() - 4);
            }

            sql = sql.concat(";");


            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                Person person = new Person();

                person.setPerson(rs.getLong("person"));
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setIdentityCode(rs.getString("identity_code"));
                person.setBirthDate(rs.getDate("birth_date"));
                person.setCreatedBy(rs.getLong("created_by"));
                person.setUpdatedBy(rs.getLong("updated_by"));
                person.setCreated(rs.getDate("created"));
                person.setUpdated(rs.getDate("updated"));

                personList.add(person);

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

        return personList;
    }

}
