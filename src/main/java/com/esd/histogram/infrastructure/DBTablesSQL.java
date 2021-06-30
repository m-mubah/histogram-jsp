package com.esd.histogram.infrastructure;

public class DBTablesSQL {
    public static final String CREATE_STUDENTS_TABLE_SQL = "CREATE TABLE STUDENTS (" +
            "ID LONG NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
            "MARKS INTEGER NOT NULL, " +
            "PRIMARY KEY (ID))";
}
