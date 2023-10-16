package hu.progmatic.hotel.service;

public class RoomNotFoundException extends Throwable {
    public RoomNotFoundException(String message) {
        super(message);
    }
}
