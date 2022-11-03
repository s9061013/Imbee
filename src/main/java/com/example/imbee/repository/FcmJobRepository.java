package com.example.imbee.repository;

import com.example.imbee.entity.FcmJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FcmJobRepository extends JpaRepository<FcmJobEntity, String> {
}
