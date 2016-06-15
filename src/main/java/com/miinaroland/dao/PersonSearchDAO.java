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
    List<Long> personIdList;

    public List<Long> getPersonIdsBySearch(String firstName, String lastName, String identityCode, java.sql.Date birthDate,
                                           String country, String county, String townVillage, String streetAddress, String zipcode,
                                           String contactValueText, String attributeValueText, Long attributeValueNumber, java.sql.Date attributeValueDate, int subjectType) {
        personIdList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            statement = connection.createStatement();
            String sql = "";

            if(subjectType == 1) {
                sql = "SELECT P.person FROM " +
                        "(SELECT * " +
                        "FROM person " +
                        "INNER JOIN address " +
                        "ON person.person=address.subject_fk " +
                        "INNER JOIN contact " +
                        "ON person.person=contact.subject_fk " +
                        "INNER JOIN subject_attribute " +
                        "ON person.person=subject_attribute.subject_fk " +
                        "WHERE address.subject_type_fk=1" +
                        " AND contact.subject_type_fk=1" +
                        " AND subject_attribute.subject_type_fk=" + subjectType +
                        " AND ";
            } else if (subjectType == 3) {
                sql = "SELECT P.person FROM " +
                        "(SELECT * " +
                        "FROM employee " +
                        "INNER JOIN person ON person.person=employee.person_fk " +
                        "INNER JOIN address ON person.person=address.subject_fk " +
                        "INNER JOIN contact ON person.person=contact.subject_fk " +
                        "INNER JOIN subject_attribute ON employee.employee=subject_attribute.subject_fk " +
                        "WHERE address.subject_type_fk=1 " +
                        "AND contact.subject_type_fk=1 " +
                        "AND subject_attribute.subject_type_fk=3 " +
                        "AND ";
            } else if (subjectType == 4) {
                sql = "SELECT P.person FROM " +
                        "(SELECT * " +
                        "FROM customer " +
                        "INNER JOIN person ON person.person=customer.subject_fk " +
                        "INNER JOIN address ON person.person=address.subject_fk " +
                        "INNER JOIN contact ON person.person=contact.subject_fk " +
                        "INNER JOIN subject_attribute ON customer.customer=subject_attribute.subject_fk " +
                        "WHERE address.subject_type_fk=1 " +
                        "AND contact.subject_type_fk=1 " +
                        "AND subject_attribute.subject_type_fk=4 " +
                        "AND ";
            }




//            Person fields
            if (firstName != null) {
                sql = sql.concat(" UPPER(person.first_name) LIKE UPPER('" + firstName + "%') AND ");
            }
            if (lastName != null) {
                sql = sql.concat(" UPPER(person.last_name) LIKE UPPER('" + lastName + "%') AND ");
            }
            if (identityCode != null) {
                sql = sql.concat(" UPPER(person.identity_code) LIKE UPPER('" + identityCode + "%') AND ");
            }
            if (birthDate != null) {
                sql = sql.concat(" person.birth_date='" + birthDate + "' AND ");
            }

//            Address fields
            if (country != null) {
                sql = sql.concat(" UPPER(address.country) LIKE UPPER('" + country + "%') AND ");
            }
            if (county != null) {
                sql = sql.concat(" UPPER(address.county) LIKE UPPER('" + county + "%') AND ");
            }
            if (townVillage != null) {
                sql = sql.concat(" UPPER(address.town_village) LIKE UPPER('" + townVillage + "%') AND ");
            }
            if (streetAddress != null) {
                sql = sql.concat(" UPPER(address.street_address) LIKE UPPER('" + streetAddress + "%') AND ");
            }
            if (zipcode != null) {
                sql = sql.concat(" UPPER(address.zipcode) LIKE UPPER('" + zipcode + "%') AND ");
            }

//            Contact fields

            if (contactValueText != null) {
                sql = sql.concat(" UPPER(contact.value_text) LIKE UPPER('" + contactValueText + "%') AND ");
            }

//            Subject attribute fields

            if (attributeValueText != null) {
                sql = sql.concat(" UPPER(subject_attribute.value_text) LIKE UPPER('" + attributeValueText + "%') AND ");
            }
            if (attributeValueNumber != null) {
                sql = sql.concat(" subject_attribute.value_number='" + attributeValueNumber + "' AND ");
            }
            if (attributeValueDate != null) {
                sql = sql.concat(" subject_attribute.value_date='" + attributeValueDate + "' AND ");
            }

//            WHERE clauses ending

            if (sql.endsWith("AND ")) {
                sql = sql.substring(0, sql.length() - 4);
            }

            sql = sql.concat(") AS P GROUP BY P.person ORDER BY P.person;");


            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                personIdList.add(rs.getLong("person"));

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

        return personIdList;
    }

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
                sql = sql.concat(" birth_date='" + birthDate + "' AND ");
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
