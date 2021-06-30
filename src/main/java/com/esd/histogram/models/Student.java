package com.esd.histogram.models;

import jakarta.persistence.*;

@Table
@Entity(name = "Students")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "marks", nullable = false)
    private int marks;

    public Student() {
    }

    public Student(int marks) {
        this.marks = marks;
    }

    public Student(Long id, int marks) {
        this.id = id;
        this.marks = marks;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", marks=" + marks +
                '}';
    }
}