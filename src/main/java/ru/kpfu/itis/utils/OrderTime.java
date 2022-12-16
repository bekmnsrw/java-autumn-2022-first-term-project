package ru.kpfu.itis.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderTime {
    public String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
