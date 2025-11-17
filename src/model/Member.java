package model;

public class Member {
    private String memberId;
    private String firstName;
    private String lastName;
    private String email;

    public Member(String memberId, String firstName, String lastName, String email) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getMemberId() { return memberId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
}
