package com.example.attendance.controller;

import com.example.attendance.entity.User;
import com.example.attendance.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class Api {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try{
            userRepo.save(user);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUser(@RequestParam(required = false) String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Please enter name");
        }else {
            List<User> matchingUsers = userRepo.findByNameIgnoreCaseContaining(name);

            if (matchingUsers.isEmpty()) {
                return ResponseEntity.ok( null);
            }
        return ResponseEntity.ok(matchingUsers);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepo.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/view")
    public ResponseEntity<User> viewUser(@RequestParam(required = true) int id) {
        try {
            User u = userRepo.findById(id).get();
            return ResponseEntity.ok(u);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/table-list")
    public ResponseEntity<?> tableList(@RequestParam(required = true) int tableNo) {
        try {
            return ResponseEntity.ok().body(userRepo.findAllByTableNo(tableNo));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @PutMapping("/attend")
    public ResponseEntity<?> attendUser(@RequestBody Map<String, List<Integer>> payload) {
        List<Integer> ids = payload.get("ids");
        if (ids == null || ids.isEmpty()) {
            return ResponseEntity.badRequest().body("Request body must include 'ids' array.");
        }

        try {
            for (Integer id : ids) {
                Optional<User> optionalUser = userRepo.findById(id);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    user.setAttend(true);
                    user.setAttendAt(LocalDateTime.now());
                    userRepo.save(user);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " not found.");
                }
            }
            return ResponseEntity.ok("Attendance marked for provided users.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @GetMapping("/recent-attend-list")
    public ResponseEntity<?> getRecentAttendList() {
        try {
            return ResponseEntity.ok().body(userRepo.findTop10ByAttendTrueOrderByAttendAtDesc());
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

}
