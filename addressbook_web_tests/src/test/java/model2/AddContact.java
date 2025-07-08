package model2;

public record AddContact(String id, String firstname, String lastname, String address, String mobile, String email, String photo) {

    public AddContact() {
        this("", "Petrov","Alexei","street1","8929777","3@mail.ru","");
    }

    public AddContact withId(String id) {
        return new AddContact(id , this.firstname, this.lastname, this.address, this.mobile, this.email, this.photo);
    }

    public AddContact withFirstame(String firstname) {
        return new AddContact(this.id, firstname, this.lastname, this.address, this.mobile, this.email, this.photo);
    }

    public AddContact withLastame(String lastname) {
        return new AddContact(this.id , this.firstname, lastname, this.address, this.mobile, this.email, this.photo);
    }

    public AddContact withAddress(String address) {
        return new AddContact(this.id , this.firstname, this.lastname, address, this.mobile, this.email, this.photo);
    }

    public AddContact withMobile(String mobile) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, mobile, this.email, this.photo);
    }

    public AddContact withEmail(String email) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, this.mobile, email, this.photo);
    }

    public AddContact withPhoto(String photo) {
        return new AddContact(this.id , this.firstname, this.lastname, this.address, this.mobile, this.email, photo);
    }
}