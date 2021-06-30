package com.esd.histogram.infrastructure;

import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Database {
    private static final String JDBC_URL = "jdbc:derby:database;create=true"; //specifies the database URL

    public Database() {
        try {
            initializeDatabase();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getDatabaseConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL);
        return connection;
    }

    /**
     * Checks if a given table exists and generates it if the table doesn't exist.
     * @param tableName Name of the table to check.
     * @param dbmd Database Meta Data object.
     * @param stmt Statement object to execute queries.
     * @param createTable Name of the table to generate.
     * @throws SQLException
     */
    private static void checkTableExists(String tableName, DatabaseMetaData dbmd, Statement stmt,
                                         String createTable) throws SQLException{
        //gets the result set from the metadata
        ResultSet rs = dbmd.getTables(null, null, tableName.toUpperCase() , null);
        //checks the metadata
        if(rs.next()){
            System.out.println("Table " + tableName + " already exists.");
        }else{
            System.out.println("Table " + tableName + " does not exist");
            stmt.execute(createTable);
            System.out.println(tableName + " Table Created");
        }
    }//end checkTableExists

    /**
     * Initialize database.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private static void initializeDatabase() throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL);
        System.out.println("Connected to DB Successfully");

        Statement initTables = connection.createStatement();

        DatabaseMetaData dbmd = connection.getMetaData(); //gets the database metadata

        try{
            checkTableExists("STUDENTS", dbmd, initTables, DBTablesSQL.CREATE_STUDENTS_TABLE_SQL);
        } catch (Error e) {
            System.out.println(e);
        }
    }
}
