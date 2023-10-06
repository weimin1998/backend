package com.example.backend.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class IndexController {

    @GetMapping("/getStudents")
    @CrossOrigin("http://localhost:7070")
    public String getStudents() {

        return """
                [
                { "id": 1, "name": "张三", "sex": "男", "age": 18 },
                { "id": 2, "name": "李四", "sex": "女", "age": 17 },
                { "id": 3, "name": "王五", "sex": "女", "age": 20 }
                ]
                """
                ;
    }

    @GetMapping("/getStudentsById")
    public String getStudentsById(int id) {
        if (id == 1) {
            return """
                    { "id": 1, "name": "张三", "sex": "男", "age": 18 }
                    """;
        }
        if (id == 2) {
            return """
                    { "id": 2, "name": "李四", "sex": "女", "age": 17 }
                    """;
        }
        if (id == 3) {
            return """
                    { "id": 3, "name": "王五", "sex": "女", "age": 20 }
                    """;
        }

        return "id is not exist!";
    }

    @PostMapping("/postStudentInfo")
    public String post(String id, String name, String sex, String age) {

        return "id: " + id + ",name: " + name + ",sex: " + sex + ",age: " + age;
    }

    @PostMapping("/postStudentInfoJson")
    public String postJson(@RequestBody String body) {
        return body;
    }

    @PostMapping("/upload")
    public String uploadFile(String name, MultipartFile image) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/static/img/" + image.getOriginalFilename())) {
            byte[] bytes = image.getBytes();
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name + "-" + image.getName() + "-" + image.getOriginalFilename();
    }
}
