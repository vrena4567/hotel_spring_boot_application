package hu.progmatic.hotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrival;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date leaving;
    @Column(name = "guests_number")
    private Integer guestsNumber;
    public Reservation(Integer id, Guest guest, Room room, Date arrival, Date leaving, Integer guestsNumber) {
        this.id = id;
        this.guest = guest;
        this.room = room;
        this.arrival = arrival;
        this.leaving = leaving;
        this.guestsNumber = guestsNumber;
    }

    public Reservation(){}

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Date getLeaving() {
        return leaving;
    }

    public void setLeaving(Date leaving) {
        this.leaving = leaving;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getGuestsNumber() {
        return guestsNumber;
    }

    public void setGuestsNumber(Integer guestsNumber) {
        this.guestsNumber = guestsNumber;
    }
}
