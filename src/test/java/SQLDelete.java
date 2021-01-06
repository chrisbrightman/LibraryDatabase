import com.ui.App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDelete {
    public static void main(String... args) {
        try (Connection connection = DriverManager.getConnection(App.dbFile)) {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM item;");
            statement.execute("DELETE FROM list;");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
