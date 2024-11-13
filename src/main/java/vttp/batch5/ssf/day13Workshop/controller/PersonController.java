package vttp.batch5.ssf.day13Workshop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vttp.batch5.ssf.day13Workshop.models.Contact;
import vttp.batch5.ssf.day13Workshop.models.Person;

import java.io.IOException;

@Controller
public class PersonController {

    private final Contact contact;

    // Injects the data directory from application arguments
    public PersonController(@Value("${dataDir}") String dataDir) {
        this.contact = new Contact(dataDir);
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("person", new Person()); // Make sure the attribute name is "person"
        return "index"; // This should match the HTML file name without the .html extension
    }

    /* @PostMapping("/contact")
    public ResponseEntity<String> submitForm(@Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Invalid input, please correct and try again.", HttpStatus.BAD_REQUEST);
        }

        // Generate ID and save data
        String id = contact.generateId();
        try {
            contact.saveContactToFile(id, person);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to save data", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Return Created response with a message
        String message = "Contact created successfully with ID: " + id;
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    } */
}
