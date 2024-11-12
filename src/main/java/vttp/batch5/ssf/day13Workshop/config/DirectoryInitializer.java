package vttp.batch5.ssf.day13Workshop.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DirectoryInitializer{

    private String dataDir;

    @Bean
    public String dataDir(ApplicationArguments args) {
        // Check if --dataDir argument is provided
        if (!args.containsOption("dataDir")) {
            System.out.println("Error: --dataDir option is required.");
            System.exit(1); // Stop the application if dataDir is not provided
        }

        // Retrieve the directory path from --dataDir argument
        dataDir = args.getOptionValues("dataDir").get(0);
        File directory = new File(dataDir);

        // Create the directory if it doesn’t exist
        if (!directory.exists() && !directory.mkdirs()) {
            System.out.println("Error: Unable to create directory at " + dataDir);
            System.exit(1); // Stop if directory creation fails
        }

        return dataDir;
    }
}
