package vn.edu.hcmut.cse.adse.student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.hcmut.cse.adse.student_management.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findByNameContainingIgnoreCase(String name);
}
