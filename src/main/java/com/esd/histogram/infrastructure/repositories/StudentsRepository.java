package com.esd.histogram.infrastructure.repositories;

import com.esd.histogram.infrastructure.Database;
import com.esd.histogram.models.Student;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Query;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StudentsRepository {
    @Resource(lookup = "jdbc/histogram")
    DataSource derby;

    public List<Student> findAll() throws SQLException {
        Connection connection = derby.getConnection();

        String query = "SELECT * FROM STUDENTS";

        PreparedStatement ps = connection.prepareStatement(query);

        List<Student> students = new ArrayList<>();

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Student student = new Student();

            student.setId(rs.getLong("ID"));
            student.setMarks(rs.getInt("MARKS"));

            students.add(student);
        }

        rs.close();
        ps.close();
        connection.close();

        return students;
    }

    public Student save(Student student) throws SQLException {
        Connection connection = derby.getConnection();

        String query = "INSERT INTO STUDENTS (MARKS) VALUES (?)";
        PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

        ps.setString(1, String.valueOf(student.getMarks()));

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();

        if (rs.next()) {
            student.setId(rs.getLong(1));
        }

        rs.close();
        ps.close();
        connection.close();

        return student;
    }
}
