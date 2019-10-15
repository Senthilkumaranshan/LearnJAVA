package com.example.authserversql.repository;

import com.example.authserversql.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import javax.jws.soap.SOAPBinding;


public interface UserDetailRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String name);
}
