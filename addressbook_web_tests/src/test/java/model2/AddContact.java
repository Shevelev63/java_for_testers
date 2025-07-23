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
                         String secondary) {

    public AddContact() {
        this("", "","","","","3@.ru","", "", "", "");
    }

    public AddContact withId(String id) {
        return new AddContact(id , this.firstname, this.lastname, this.address, this.mobile, this.email, this.photo, this.home, this.work, this.secondary);
    }

    public AddContact withFirstame(String firstname) {
        return new AddContact(this.id, firstname, this.lastname, this.address, this.mobile, this.email, this.photo, this.home, this.work, this.secondary);
    }

    public AddContact withLastame(String lastname) {
        return new AddContact(this.id , this.firstname, lastname, this.address, this.mobile, this.email, this.photo, this.home, this.work, this.secondary);
    }

    public AddContact withAddress(String address) {
        return new AddContact(this.id , this.firstname, this.lastname, address, this.mobile, this.email, this.photo, this.home, this.work, this.secondary);
    }

    public AddContact withMobile(String mobile) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, mobile, this.email, this.photo, this.home, this.work, this.secondary);
    }

    public AddContact withEmail(String email) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, this.mobile, email, this.photo, this.home, this.work, this.secondary);
    }

    public AddContact withPhoto(String photo) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, this.mobile, this.email, photo, this.home, this.work, this.secondary);
    }

    public AddContact withHome(String home) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, this.mobile, this.email, this.photo, home, this.work, this.secondary);
    }

    public AddContact withWork(String work) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, this.mobile, this.email, this.photo, this.home, work, this.secondary);
    }

    public AddContact withSecondary(String secondary) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, this.mobile, this.email, this.photo, this.home, this.work, secondary);
    }
}