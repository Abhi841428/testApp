package com.test.repository;

import com.test.entity.Post;
import com.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

        public User findByuserName(String userName);

        }
