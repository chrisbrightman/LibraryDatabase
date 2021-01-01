package control;

import com.control.AddItem;
import mockit.*;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class AddItemTest {

    @Mocked Connection mockConnection;
    @Mocked Statement mockedStatement;
    AddItem uut;

    @BeforeAll
    public void createMocks() throws SQLException {
        new Expectations() {{
            mockConnection.createStatement(); result = mockedStatement;
        }};

    }

}
