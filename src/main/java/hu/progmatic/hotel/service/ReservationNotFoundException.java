package hu.progmatic.hotel.service;

public class ReservationNotFoundException extends Throwable {
    public ReservationNotFoundException(String message) {
        super(message);
    }
}
