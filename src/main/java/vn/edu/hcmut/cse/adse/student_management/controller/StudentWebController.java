package vn.edu.hcmut.cse.adse.student_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Luu y: su dung @Controller, KHONG dung @RestController
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmut.cse.adse.student_management.Student;
import vn.edu.hcmut.cse.adse.student_management.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;

    // 1. Trang Danh Sách (List View)
    // Route: GET http://localhost:8080/students
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()) {
            students = service.searchByName(keyword);
        } else {
            students = service.getAll();
        }
        model.addAttribute("dsSinhVien", students);
        return "students";
    }

    // 2. Trang Chi Tiết (Detail View)
    // Route: GET http://localhost:8080/students/{id}
    @GetMapping("/{id}")
    public String getStudentDetail(@PathVariable String id, Model model) {
        Student student = service.getById(id);
        model.addAttribute("student", student);
        return "student_detail";
    }

    // 3. Chức Năng Thêm Mới - Form
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student_form";
    }

    // 4. Chức Năng Chỉnh Sửa - Form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = service.getById(id);
        model.addAttribute("student", student);
        return "student_form";
    }

    // 5. Xử lý Lưu (Thêm/Sửa)
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        service.save(student);
        return "redirect:/students";
    }

    // 6. Xử lý Xóa
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        service.deleteById(id);
        return "redirect:/students";
    }
}