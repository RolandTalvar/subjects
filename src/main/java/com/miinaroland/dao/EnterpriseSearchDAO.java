package com.miinaroland.dao;

import com.miinaroland.model.Enterprise;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miinaroland on 06/06/16.
 */
@Repository
public class EnterpriseSearchDAO {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/dkf6fokg3qu1e";

    static final String USER = "xeaizttgmawsfi";
    static final String PASS = "YaUL6jwiwodWgre75rugIUyRac";

    List<Enterprise> enterpriseList;
    List<Long> enterpriseIdList;

    public List<Long> getEnterpriseIdsBySearch(String name,
                                           String country, String county, String townVillage, String streetAddress, String zipcode,
                                           String contactValueText, String attributeValueText, Long attributeValueNumber, java.sql.Date attributeValueDate, int subjectType) {
        enterpriseIdList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            statement = connection.createStatement();
            String sql;
            sql = "SELECT P.enterprise FROM " +
                    "(SELECT * " +
                    "FROM enterprise " +
                    "INNER JOIN address " +
                    "ON enterprise.enterprise=address.subject_fk " +
                    "INNER JOIN contact " +
                    "ON enterprise.enterprise=contact.subject_fk " +
                    "INNER JOIN subject_attribute " +
                    "ON enterprise.enterprise=subject_attribute.subject_fk " +
                    "WHERE address.subject_type_fk=2 AND contact.subject_type_fk=2 AND " +
                    "subject_attribute.subject_type_fk=" + subjectType +
                    " AND ";


//            Enterprise fields
            if (name != null) {
                sql = sql.concat(" UPPER(enterprise.name) LIKE UPPER('" + name + "%') AND ");
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

            sql = sql.concat(") AS P GROUP BY P.enterprise ORDER BY P.enterprise;");


            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                enterpriseIdList.add(rs.getLong("enterprise"));

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

        return enterpriseIdList;
    }

    public List<Enterprise> search(String name, String fullName) {
        enterpriseList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            statement = connection.createStatement();
            String sql;
            sql = "SELECT * FROM enterprise WHERE ";
            if (name != null) {
                sql = sql.concat(" UPPER(name) LIKE UPPER('" + name + "%') AND ");
            }
            if (fullName != null) {
                sql = sql.concat(" UPPER(full_name) LIKE UPPER('" + fullName + "%') AND ");
            }

            if (sql.endsWith("AND ")) {
                sql = sql.substring(0, sql.length() - 4);
            }

            sql = sql.concat(";");


            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                Enterprise enterprise = new Enterprise();

                enterprise.setEnterprise(rs.getLong("enterprise"));
                enterprise.setName(rs.getString("name"));
                enterprise.setFullName(rs.getString("full_name"));

                enterpriseList.add(enterprise);

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

        return enterpriseList;
    }

}
