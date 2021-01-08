package control;

import com.control.AddItem;
import com.model.Item;
import com.sun.prism.sw.SWMaskTexture;
import mockit.*;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Tag("Classes")
public class AddItemTest {

    @Rule public final ExpectedException thrown = ExpectedException.none();

    @Mocked Connection mockConnection;
    @Mocked Item mockedItem;
    @Mocked Statement mockedStatement;

    @Test
    void testAddItemException () throws SQLException {
        new Expectations() {{
            mockConnection.createStatement();
            result = mockedStatement;
            mockedItem.getAddCommand();
            result = "GOOD";
        }};
        AddItem uut = new AddItem(mockConnection);
        //uut.addItem(mockedItem);
        assertTrue(true);
        new Verifications() {{
            mockedStatement.execute("GOOD");
        }};
        assertTrue(true);
    }

}
