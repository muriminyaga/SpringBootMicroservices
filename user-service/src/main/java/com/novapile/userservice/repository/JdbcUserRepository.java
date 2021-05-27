package com.novapile.userservice.repository;

import java.util.List;

import com.novapile.userservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Jdbc user repository demonstration
 */
@Repository
public class JdbcUserRepository {
    // Spring Boot will create and configure DataSource and JdbcTemplate
    // To use it, just @Autowired
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int count() {
        return jdbcTemplate
                .queryForObject("select count(*) from user", Integer.class);
    }

}
