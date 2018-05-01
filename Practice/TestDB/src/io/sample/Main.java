package io.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING =
    "jdbc:sqlite:/Users/zhouyi/workspace/Program/Github/Java/PracticeFromWindowsLaptop/Practice" +
    "/TestDB/" + DB_NAME;
    
    public static final String TABLE_CONTACTS = "Contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = connection.createStatement()) {
            
            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "(" + COLUMN_NAME +
                              " TEXT, " + COLUMN_PHONE + " INTEGER, " + COLUMN_EMAIL + " TEXT)");
            statement.execute("INSERT INTO " + TABLE_CONTACTS + "(" + COLUMN_NAME + ", " +
                              COLUMN_PHONE + ", " + COLUMN_EMAIL + ") " + "VALUES('Joey', " +
                              "'123456', 'joey@email.com')");
            statement.execute("INSERT INTO " + TABLE_CONTACTS + "(" + COLUMN_NAME + ", " +
                              COLUMN_PHONE + ", " + COLUMN_EMAIL + ") " + "VALUES('Joe', " +
                              "'345678', 'joe@email.com')");
            statement.execute("INSERT INTO " + TABLE_CONTACTS + "(" + COLUMN_NAME + ", " +
                              COLUMN_PHONE + ", " + COLUMN_EMAIL + ") " + "VALUES('Ashley', " +
                              "'98867', 'ashley@email.com')");
            statement.execute("INSERT INTO " + TABLE_CONTACTS + "(" + COLUMN_NAME + ", " +
                              COLUMN_PHONE + ", " + COLUMN_EMAIL + ") " + "VALUES('Java', " +
                              "'9123867', 'java@email.com')");
        } catch (SQLException e) {
            System.out.println("Something wrong: " + e.getMessage());
        }
    }
}
