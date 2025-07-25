package model2;

public record AddContact(String id,
                         String firstname,
                         String lastname,
                         String address,
                         String mobile,
                         String email,
                         String photo,
                         String home,
                         String work,
                         String secondary,
                         String email2,
                         String email3) {

    public AddContact() {
        this("", "","","","","3@.ru","", "", "", "", "", "");
    }

    public AddContact withId(String id) {
        return new AddContact(id , this.firstname, this.lastname, this.address, this.mobile, this.email, this.photo, this.home, this.work, this.secondary, this.email2, this.email3);
    }

    public AddContact withFirstame(String firstname) {
        return new AddContact(this.id, firstname, this.lastname, this.address, this.mobile, this.email, this.photo, this.home, this.work, this.secondary, this.email2, this.email3);
    }

    public AddContact withLastame(String lastname) {
        return new AddContact(this.id , this.firstname, lastname, this.address, this.mobile, this.email, this.photo, this.home, this.work, this.secondary, this.email2, this.email3);
    }

    public AddContact withAddress(String address) {
        return new AddContact(this.id , this.firstname, this.lastname, address, this.mobile, this.email, this.photo, this.home, this.work, this.secondary, this.email2, this.email3);
    }

    public AddContact withMobile(String mobile) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, mobile, this.email, this.photo, this.home, this.work, this.secondary, this.email2, this.email3);
    }

    public AddContact withEmail(String email) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, this.mobile, email, this.photo, this.home, this.work, this.secondary, this.email2, this.email3);
    }

    public AddContact withPhoto(String photo) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, this.mobile, this.email, photo, this.home, this.work, this.secondary, this.email2, this.email3);
    }

    public AddContact withHome(String home) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, this.mobile, this.email, this.photo, home, this.work, this.secondary, this.email2, this.email3);
    }

    public AddContact withWork(String work) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, this.mobile, this.email, this.photo, this.home, work, this.secondary, this.email2, this.email3);
    }

    public AddContact withSecondary(String secondary) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, this.mobile, this.email, this.photo, this.home, this.work, secondary, this.email2, this.email3);
    }

    public AddContact withEmail2(String email2) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, this.mobile, this.email, this.photo, this.home, this.work, this.secondary, email2, this.email3);
    }

    public AddContact withEmail3(String email3) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, this.mobile, this.email, this.photo, this.home, this.work, this.secondary, this.email2, email3);
    }
}