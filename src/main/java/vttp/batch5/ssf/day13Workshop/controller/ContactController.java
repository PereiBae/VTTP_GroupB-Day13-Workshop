package vttp.batch5.ssf.day13Workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vttp.batch5.ssf.day13Workshop.models.Contact;
import vttp.batch5.ssf.day13Workshop.models.Person;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class ContactController {

    private final Contact contactService;

    @Autowired
    public ContactController(String dataDir) {
        this.contactService = new Contact(dataDir); // Initialize Contact with dataDir
    }

    @GetMapping("/contact/{id}")
    public String getContactById(@PathVariable String id, Model model) {
        // Validate id length
        if (id.length() != 8) {
            model.addAttribute("errorMessage", "Invalid ID format. ID must be 8 characters long.");
            return "error"; // Render an error page if ID is invalid
        }

        try {
            // Attempt to read the contact file by ID
            String content = contactService.readContactById(id);
            if (content == null) {
                model.addAttribute("errorMessage", "Contact not found.");
                return "error"; // Render an error page if contact not found
            }

            // Add content to the model to display in the HTML view
            model.addAttribute("contactContent", content);
            return "contactDetails"; // Render contactDetails.html to show the contact
        } catch (IOException e) {
            model.addAttribute("errorMessage", "Error reading contact data.");
            return "error"; // Render an error page on read failure
        }
    }

    @GetMapping("/contacts")
    public String listContacts(Model model) {
        try {
            Map<String, String> contacts = contactService.getAllContacts();
            model.addAttribute("contacts", contacts);
        } catch (IOException e) {
            model.addAttribute("errorMessage", "Failed to load contacts.");
        }
        return "contacts";
    }

    @PostMapping("/contact/create")
    public String createContact(@Validated Person person, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "contacts"; // Return to contacts page if there are validation errors
        }

        String id = UUID.randomUUID().toString().substring(0, 8);

        try {
            contactService.saveContactToFile(id, person);
            model.addAttribute("contactId", id);
            model.addAttribute("successMessage", "Contact created successfully!");
        } catch (IOException e) {
            model.addAttribute("errorMessage", "Failed to save contact data.");
            return "contacts";
        }

        return "success"; // Render success.html with the contact ID and success message
    }

}
