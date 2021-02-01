package com.walker.manual;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/1/27
 */
public class OverlySpecificBankStatementValidator {

    private final String description;
    private final String date;
    private final String amount;

    public OverlySpecificBankStatementValidator(String description, String date, String amount) {
        this.description = Objects.requireNonNull(description);
        this.date = Objects.requireNonNull(date);
        this.amount = Objects.requireNonNull(amount);
    }

    public Notification validate() {
        Notification notification = new Notification();

        if (this.description.length() > 100) {
            notification.addError("The description is too long");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(this.date);
            if (date.isAfter(LocalDate.now())) {
                notification.addError("date cannot be in the future");
            }
        } catch (DateTimeParseException e) {
            notification.addError("Invalid format for date");
        }

        double amount;
        try {
            amount = Double.parseDouble(this.amount);
        } catch (NumberFormatException e) {
            notification.addError("Invalid format for date");
        }

        return notification;
    }
}
