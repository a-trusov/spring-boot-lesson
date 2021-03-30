package com.papont.lesson;

import com.papont.lesson.initializer.Postgres;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@SpringBootTest
@Sql("/sql/data.sql")
@ContextConfiguration(initializers = {
        Postgres.Initializer.class
})
@Transactional
public class IntegrationTestBase {

    @BeforeAll
    static void init() {
        Postgres.container.start();
    }
}
