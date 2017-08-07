package com.eking.apollo.repository;

import com.eking.apollo.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadlingListRepository extends JpaRepository<Reading, Long> {
    List<Reading> findByReader(String reader);
}
