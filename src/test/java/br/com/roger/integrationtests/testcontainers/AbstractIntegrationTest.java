package br.com.roger.integrationtests.testcontainers;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest  {

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.29");

        private static void startContainers() throws ExecutionException, InterruptedException, TimeoutException {
            Startables.deepStart(Stream.of(mysql)).get(30, TimeUnit.SECONDS);
        }

        private static Map<String, String> createConnectionConfiguration() {
            return Map.of(
                    "spring.datasource.url", mysql.getJdbcUrl(),
                    "spring.datasource.username", mysql.getUsername(),
                    "spring.datasource.password", mysql.getPassword()
            );
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
            try {
                startContainers();
            } catch (ExecutionException | InterruptedException | TimeoutException e) {
                throw new RuntimeException(e);
            }
            ConfigurableEnvironment env = applicationContext.getEnvironment();
            MapPropertySource testcontainers = new MapPropertySource(
                    "testcontainers",
                    (Map) createConnectionConfiguration()
            );
            env.getPropertySources().addFirst(testcontainers);
        }

    }
}
