package com.example.jlmart;

import java.sql.Date;
import java.time.LocalDate;

public class Mail {
    private String message;
    private String username;
    private LocalDate sendDate;

    public Mail(String message, String username, LocalDate sendDate) {
        this.message = message;
        this.username = username;
        this.sendDate = sendDate;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }
}
