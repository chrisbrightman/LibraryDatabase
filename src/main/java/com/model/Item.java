package com.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Item {
    private static final String ADD_COMMAND = "INSERT INTO item (i_name, l_name, " +
            "description, l_rank, age, last_day) VALUES ('";
    private String item_name;
    private String description;

    public Item(String item_name, String description) {
        if (item_name.length() < 10) {
            this.item_name = item_name;
        }
        if (description.length() < 100) {
            this.description = description;
        }
    }

    public String getAddCommand() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime time = LocalDateTime.now();
        return ADD_COMMAND + item_name + "', NULL, '"
                + description + "', NULL, 0, '" + time.format(formatter) +
                "');";
    }
}
