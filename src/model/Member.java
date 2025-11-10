package model;

public class Member { //this class  represents a person registered in the library.
    private String memberId;
    private String firstName;
    private String lastName;
    private String email;
    //private meaning only this class can access them directly (encapsulation.)
    // Constructor
    public Member(String memberId, String firstName, String lastName, String email) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    // Getters --Allow other classes to read the member’s information.
    public String getMemberId() {
        return memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    // toString method --makes printing a Model.Member object readable.
    @Override
    public String toString() {
        return "Model.Member{" +
                "memberId=" + memberId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}