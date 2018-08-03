package com.zx.shark.repository;

import com.zx.shark.model.MyException;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyExceptionRepository extends MongoRepository<MyException,Long> {
    @Override
    <S extends MyException> S insert(S s);
}
