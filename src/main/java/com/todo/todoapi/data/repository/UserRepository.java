package com.todo.todoapi.data.repository;

import com.todo.todoapi.data.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Iterable<User> findUserByUserEmail(String email);
}
