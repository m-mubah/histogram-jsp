package com.esd.histogram.services;

import com.esd.histogram.models.Student;
import com.esd.histogram.infrastructure.repositories.StudentsRepository;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentsService {
    private final StudentsRepository studentsRepository;

    @Inject
    public StudentsService(StudentsRepository studentRepository) {
        this.studentsRepository = studentRepository;
    }

    public Student create(int marks) {
        Student student = new Student(marks);
        try {
        student = studentsRepository.save(student);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return student;
    }

    public void createMany(List<Integer> data) {
        data.forEach(i -> {
            create(i.intValue());
        });
    }

    public int[] generateHistogram() {
        List<Student> students = new ArrayList<>();
        try {
            students = studentsRepository.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int[] histogram = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        students.forEach(i -> {
            int marks = i.getMarks();

            if (marks > 0 && marks < 11) {
                histogram[0] = histogram[0] += 1;
            } else if (marks > 10 && marks < 21) {
                histogram[1] = histogram[1] += 1;
            } else if (marks > 20 && marks < 31) {
                histogram[2] = histogram[2] += 1;
            } else if (marks > 30 && marks < 41) {
                histogram[3] = histogram[3] += 1;
            } else if (marks > 40 && marks < 51) {
                histogram[4] = histogram[4] += 1;
            } else if (marks > 50 && marks < 61) {
                histogram[5] = histogram[5] += 1;
            } else if (marks > 60 && marks < 71) {
                histogram[6] = histogram[6] += 1;
            } else if (marks > 70 && marks < 81) {
                histogram[7] = histogram[7] += 1;
            } else if (marks > 80 && marks < 91) {
                histogram[8] = histogram[8] += 1;
            } else if (marks > 90 && marks < 101) {
                histogram[9] = histogram[9] += 1;
            }
        });

        return histogram;
    }
}
