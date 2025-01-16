package com.example.attendance.repo;

import com.example.attendance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    List<User> findByNameIgnoreCaseContaining(String name);

    List<User> getAllByTableNo(int tableNo);

    List<User> findAllByTableNo(int tableNo);
}
