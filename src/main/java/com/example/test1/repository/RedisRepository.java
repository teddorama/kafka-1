package com.example.test1.repository;

import com.example.test1.dto.Reply;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<Reply, String> {

}
