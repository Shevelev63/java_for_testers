package manager;

import model.GroupData;
import model2.AddContact;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {
    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<GroupData> getGroupList() {
        var groups = new ArrayList<GroupData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM grouplist"))
        {
            while (result.next()) {
                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public List<AddContact> getContactList() {
        var contacts = new ArrayList<AddContact>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT id, firstname, lastname, address, mobile, email  FROM addressbook"))
        {
            while (result.next()) {
                contacts.add(new AddContact()
                        .withId(result.getString("id"))
                        .withFirstame(result.getString("firstname"))
                        .withLastame(result.getString("lastname"))
                        .withAddress(result.getString("address"))
                        .withMobile(result.getString("mobile"))
                        .withEmail(result.getString("email")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }


    public void checkConsistency() {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery(
                     "SELECT * FROM address_in_groups ag LEFT JOIN addressbook ab ON ab.id = ag.id WHERE ab.id IS NULL"))
        {
            if (result.next()) {
                throw new IllegalArgumentException("DB is corrupted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
