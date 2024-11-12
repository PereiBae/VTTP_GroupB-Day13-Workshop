package vttp.batch5.ssf.day13Workshop.models;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Contact {
    private final String dataDir;

    public Contact(String dataDir) {
        this.dataDir = dataDir;
    }

    // Generates a random 8-character hex ID
    public String generateId() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
    }

    // Saves contact data to a file with the generated ID as the filename
    public void saveContactToFile(String id, Person person) throws IOException {
        File file = new File(dataDir, id);
        try (FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8)) {
            writer.write("Name: " + person.getName() + "\n");
            writer.write("Email: " + person.getEmail() + "\n");
            writer.write("Phone: " + person.getPhone() + "\n");
            writer.write("Date of Birth: " + person.getDob() + "\n");
        }
    }

    public String readContactById(String id) throws IOException {
        Path filePath = Paths.get(dataDir, id);
        File file = filePath.toFile();
        if (!file.exists()) {
            return null; // Indicates the file does not exist
        }
        return Files.readString(filePath); // Reads the file content as a string
    }

    public Map<String, String> getAllContacts() throws IOException {
        Map<String, String> contacts = new HashMap<>();
        File directory = new File(dataDir);

        if (!directory.exists() || !directory.isDirectory()) {
            throw new IOException("Data directory does not exist or is not a directory.");
        }

        File[] files = directory.listFiles();
        if (files == null) {
            throw new IOException("Could not read files from directory.");
        }

        for (File file : files) {
            // Skip hidden files or files starting with a dot
            if (file.isFile() && !file.isHidden() && !file.getName().startsWith(".")) {
                String id = file.getName();
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String name = reader.readLine();
                    if (name != null) {
                        contacts.put(id, name);
                    }
                }
            }
        }
        return contacts;
    }
}
