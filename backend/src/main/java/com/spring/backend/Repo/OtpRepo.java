package com.spring.backend.Repo;

import com.spring.backend.Entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OtpRepo extends JpaRepository<OtpEntity, Long> {

    OtpEntity findByToken(String token);

    OtpEntity findByEmail(String email);
}
