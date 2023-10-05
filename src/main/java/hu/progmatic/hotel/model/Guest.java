package hu.progmatic.hotel.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Title title;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "place_of_birth")
    private String placeOfBirth;
    @Column(name = "birth")
    private Date birth;
    @Column(name = "id_number")
    private String idNumber;
    private String email;
    @Column(name = "guests_number")
    private Integer guestsNumber;

    public Guest(Integer id, Title title, String firstName, String lastName, String placeOfBirth, Date birth, String idNumber, String email, Integer guestsNumber) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.placeOfBirth = placeOfBirth;
        this.birth = birth;
        this.idNumber = idNumber;
        this.email = email;
        this.guestsNumber = guestsNumber;
    }
    public Guest(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birthYear) {
        this.birth = birthYear;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGuestsNumber() {
        return guestsNumber;
    }

    public void setGuestsNumber(Integer guestsNumber) {
        this.guestsNumber = guestsNumber;
    }


    public enum Title {
        UNKNOWN,
        MR,
        MRS,
        MS,
        CHILD


    }
}
