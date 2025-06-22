package model2;

public record AddContact(String firstname, String lastname, String address, String mobile, String email) {

    public AddContact() {
        this("","","","","");
    }

    public AddContact withFirstame(String firstname) {
        return new AddContact(firstname, this.lastname, this.address, this.mobile, this.email);
    }

    public AddContact withLastame(String lasttname) {
        return new AddContact(this.firstname, lastname, this.address, this.mobile, this.email);
    }

    public AddContact withAddress(String address) {
        return new AddContact(this.firstname, this.lastname, address, this.mobile, this.email);
    }

    public AddContact withMobile(String mobile) {
        return new AddContact(this.firstname, this.lastname, this.address, mobile, this.email);
    }

    public AddContact withEmail(String email) {
        return new AddContact(this.firstname, this.lastname, this.address, this.mobile, email);
    }
}