package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {
    @Test
    public void testDbConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
            Statement st = conn.createStatement();
            //st.executeQuery("select * from group_list");

            ResultSet result_groups = st.executeQuery("select * from group_list");
            Groups groups = new Groups();

            while (result_groups.next()){
                groups.add(new GroupData().withId(result_groups.getInt("group_id")).withGroupName(result_groups.getString("group_name")).
                        withGroupHeader(result_groups.getString("group_header")).withGroupFooter(result_groups.getString("group_footer")));
            }
            result_groups.close();
            st.close();
            conn.close();
            System.out.println(groups);

        } catch (SQLException ex) {
            // handle all errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
