package atu.ie.week7solution.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Reservation {

    private Long reservationId;

    @NotBlank(message = "Equipment tag is required")
    private String equipmentTag;

    @NotBlank(message = "Student email is required")
    @Email(message = "Email must be valid")
    private String studentEmail;

    @NotNull(message = "Reservation date is required")
    private LocalDate reservationDate;

    @Min(value = 0, message = "Start hour must be between 0 and 23")
    @Max(value = 23, message = "Start hour must be between 0 and 23")
    private int startHour;

    @Min(value = 1, message = "Duration must be between 1 and 24")
    @Max(value = 24, message = "Duration must be between 1 and 24")
    private int durationHours;
}
