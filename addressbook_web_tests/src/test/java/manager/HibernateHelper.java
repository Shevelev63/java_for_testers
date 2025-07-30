package manager;
import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.GroupData;
import model2.AddContact;
import net.bytebuddy.asm.MemberSubstitution;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;

import java.util.List;


public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convert).toList();
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }


    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void CreateGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
                });
    }

    static List<AddContact> convertListContact(List<ContactRecord> contactRecords) {
        return contactRecords.stream().map(HibernateHelper::convertContact).toList();

    }

    private static AddContact convertContact(ContactRecord contactRecord) {
        return new AddContact().withId("" + contactRecord.id)
                .withFirstame(contactRecord.firstname)
                .withLastame(contactRecord.lastname)
                .withAddress(contactRecord.address)
                .withMobile(contactRecord.mobile)
                .withEmail(contactRecord.email)
                .withPhoto(contactRecord.photo)
                .withHome(contactRecord.home)
                .withWork(contactRecord.work)
                .withSecondary(contactRecord.phone2)
                .withSecondary(contactRecord.email2)
                .withSecondary(contactRecord.email3);
    }

    private static ContactRecord convert(AddContact contactData) {
        var id = contactData.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), contactData.firstname(), contactData.lastname(), contactData.address(), contactData.address(), contactData.email(), contactData.photo());
    }

    public List<AddContact> getContactList() {
        return convertListContact(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }


    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createAdd(AddContact addContact) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(addContact));
            session.getTransaction().commit();
        });
    }

    public List<AddContact> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertListContact(session.get(GroupRecord.class, group.id()).contacts);
        });
    }

    public List<GroupData> getGroupsByContact(AddContact contact) {
        return sessionFactory.fromSession(session -> {
            return convertList(session.get(ContactRecord.class, contact.id()).groups);
        });
    }

    public List<AddContact> getContactsNotInGroup() {
        var allContacts = getContactList();
        allContacts.removeIf(contact -> {
            var groups = getGroupsByContact(contact);
            return (groups != null) && (!groups.isEmpty());
        });
        return allContacts;
    }
}