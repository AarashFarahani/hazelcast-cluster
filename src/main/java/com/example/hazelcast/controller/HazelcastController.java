package com.example.hazelcast.controller;

import com.example.hazelcast.model.Student;
import com.hazelcast.core.HazelcastInstance;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequiredArgsConstructor
public class HazelcastController {
    private final HazelcastInstance hazelcastInstance;

    private ConcurrentMap<String, Student> studentConcurrentMap() {
        return hazelcastInstance.getMap("student");
    }

    @PutMapping
    public ResponseEntity put(@RequestParam(value = "name") String name) {
        var student = new Student(UUID.randomUUID().toString(), name);
        studentConcurrentMap().put(student.getId(), student);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{key}")
    public ResponseEntity<Student> get(@PathVariable(value = "key") String key) {
        var student = this.studentConcurrentMap().get(key);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity all() {
        return ResponseEntity.ok(this.studentConcurrentMap().entrySet());
    }
}
