package com.papont.lesson.repository;

import com.papont.lesson.IntegrationTestBase;
import com.papont.lesson.entity.Company;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.reflections.util.ConfigurationBuilder.build;

public class JdbcTemplateTest extends IntegrationTestBase {

    private static final String INSERT_SQL = "insert into company(name) values (?);";

    //language=PostgreSQL
    private static final String INSERT_RETURNING_SQL = "delete from company where name = ? RETURNING *;";

    @Autowired
    private JdbcOperations jdbcOperations;

    @Test
    void testInsert() {
        final var result = jdbcOperations.update(INSERT_SQL, "Microsoft");
        Assertions.assertEquals(1, result);
    }

    @Test
    void testReturning() {
        final var companyName = "Microsoft";
        jdbcOperations.update(INSERT_SQL, companyName);

        final var result = jdbcOperations.query(INSERT_RETURNING_SQL,
                (rs, rowNum) -> Company.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build(), companyName);

        MatcherAssert.assertThat(result, hasSize(1));
    }

}
