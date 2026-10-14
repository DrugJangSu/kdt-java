package chapter03.lesson05;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/** Reads the DB password from an environment variable or an ignored local file. */
final class LocalDbConfig {
    private LocalDbConfig() {}

    static String password() {
        String fromEnvironment = System.getenv("DB_PASSWORD");
        if (fromEnvironment != null && !fromEnvironment.isBlank()) {
            return fromEnvironment;
        }
        for (Path path : new Path[]{Path.of("db-local.properties"),
                Path.of("kdt-java", "db-local.properties")}) {
            if (!Files.isRegularFile(path)) continue;
            Properties properties = new Properties();
            try (Reader reader = Files.newBufferedReader(path)) {
                properties.load(reader);
            } catch (IOException e) {
                throw new IllegalStateException("Cannot read local DB configuration.", e);
            }
            String configured = properties.getProperty("db.password");
            if (configured != null && !configured.isBlank()) return configured;
        }
        throw new IllegalStateException(
                "Set DB_PASSWORD or db.password in the ignored db-local.properties file.");
    }
}
