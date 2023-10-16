package hu.progmatic.hotel.service;

public class GuestNotFoundException extends Throwable {
    public GuestNotFoundException(String message) {
        super(message);
    }
}
