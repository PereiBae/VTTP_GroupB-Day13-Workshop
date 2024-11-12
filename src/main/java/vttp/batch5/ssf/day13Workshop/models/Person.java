package vttp.batch5.ssf.day13Workshop.models;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class Person {

    @NotNull(message = "Name is required")
    @Size(min = 3, max = 64, message = "Name must be between 3 and 64 characters")
    private String name;

    @NotNull(message = "Email is required")
    @Email(message = "Must be a valid email address")
    private String email;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{7,}$", message = "Phone number must contain at least 7 digits")
    private String phone;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    public @NotNull(message = "Name is required") @Size(min = 3, max = 64, message = "Name must be between 3 and 64 characters") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Name is required") @Size(min = 3, max = 64, message = "Name must be between 3 and 64 characters") String name) {
        this.name = name;
    }

    public @NotNull(message = "Email is required") @Email(message = "Must be a valid email address") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "Email is required") @Email(message = "Must be a valid email address") String email) {
        this.email = email;
    }

    public @NotNull(message = "Phone number is required") @Pattern(regexp = "^[0-9]{7,}$", message = "Phone number must contain at least 7 digits") String getPhone() {
        return phone;
    }

    public void setPhone(@NotNull(message = "Phone number is required") @Pattern(regexp = "^[0-9]{7,}$", message = "Phone number must contain at least 7 digits") String phone) {
        this.phone = phone;
    }

    public @NotNull(message = "Date of birth is required") @Past(message = "Date of birth must be in the past") LocalDate getDob() {
        return dob;
    }

    public void setDob(@NotNull(message = "Date of birth is required") @Past(message = "Date of birth must be in the past") LocalDate dob) {
        this.dob = dob;
    }
}
