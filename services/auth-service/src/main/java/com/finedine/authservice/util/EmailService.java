package com.finedine.authservice.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generic interface for sending emails.
 * This interface defines a method for sending email messages of type T.
 * It also includes a default method to validate email addresses using regex.
 *
 * @param <T> the type of the email message
 */
public interface EmailService<T> {
    void send (T message);

    default boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
