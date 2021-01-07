package model;

import static org.junit.jupiter.api.Assertions.*;

import com.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Tag("Classes")
public class TestItem {

    private static final DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String date = LocalDateTime.now().format(formater);
    private static final String ADD_COMMAND = "INSERT INTO item (i_name, l_name, " +
            "description, l_rank, age, last_day) VALUES ('test', NULL, 'to test Item', NULL, 0, '"
            + date + "');";

    @Test
    public void TestGetAddCommand() {
        Item uut = new Item("test", "to test Item");
        assertEquals(ADD_COMMAND, uut.getAddCommand());

    }
}
