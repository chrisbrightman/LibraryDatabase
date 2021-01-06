import com.ui.App;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SQLDump {

    private static void printList(ResultSet item) throws SQLException{
        System.out.println("***** List Tables *****");
        System.out.println("i_name\tl_name\tdescription\tl_rank\tage\tlast_day");
        while(item.next()) {
            System.out.println(item.getString("i_name") + "\t"
                    + item.getString("l_name") + "\t"
                    + item.getString("description") + "\t"
                    + item.getInt("l_rank") + "\t"
                    + item.getInt("age") + "\t"
                    + item.getString("last_day") + "\t\n"
            );
        }
    }

    private static void printItems(ResultSet item) throws SQLException{
        System.out.println("***** Item Tables *****");
        System.out.println("i_name\tl_name\tdescription\tl_rank\tage\tlast_day");
        while(item.next()) {
            System.out.println(item.getString("i_name") + "\t"
                            + item.getString("l_name") + "\t"
                            + item.getString("description") + "\t"
                            + item.getInt("l_rank") + "\t"
                            + item.getInt("age") + "\t"
                            + item.getString("last_day") + "\t\n"
                    );
        }
    }

    public static void main(String... args) throws SQLException {
        Connection conn = DriverManager.getConnection(App.dbFile);
        Statement itemStatement = conn.createStatement();
        ResultSet itemDump = itemStatement.executeQuery("SELECT * FROM item");
        printItems(itemDump);
        assertTrue(true);
    }

}
