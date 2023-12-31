package com.fivestar.loginwithjwtoauthform.repository;

import com.fivestar.loginwithjwtoauthform.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member,Long> {
    boolean existsByEmail(String email);

     Optional<Member> findByEmail(String email);
}


