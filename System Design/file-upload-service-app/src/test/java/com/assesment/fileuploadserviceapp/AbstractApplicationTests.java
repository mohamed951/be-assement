package com.assesment.fileuploadserviceapp;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@AutoConfigureMockMvc
@SpringBootTest
@Testcontainers
@TestExecutionListeners(mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
        value = {DbUnitTestExecutionListener.class})
abstract class AbstractApplicationTests {

    @Container
    protected static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:14.5");

    @Autowired
    protected Environment environment;

    @Autowired
    protected MockMvc mockMvc;

    @DynamicPropertySource
    static void overrideDatasourceUrl(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        registry.add("spring.flyway.default-schema", postgresContainer::getDatabaseName);
    }
}
