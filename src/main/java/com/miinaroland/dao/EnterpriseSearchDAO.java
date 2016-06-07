package com.miinaroland.dao;

import com.miinaroland.model.Enterprise;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rolandtalvar on 06/06/16.
 */
@Repository
public class EnterpriseSearchDAO {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/dkf6fokg3qu1e";

    static final String USER = "xeaizttgmawsfi";
    static final String PASS = "YaUL6jwiwodWgre75rugIUyRac";

    List<Enterprise> enterpriseList;

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
