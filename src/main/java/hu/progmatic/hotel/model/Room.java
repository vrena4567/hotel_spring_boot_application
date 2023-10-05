package hu.progmatic.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Set;

@Entity
public class Room {
    @Id
    private Integer id;
    private Integer capacity;
    private Integer price;
    private Boolean jakuzzi;
    private Boolean sauna;

    public Room(Integer id, Integer capacity, Integer price, Boolean jakuzzi, Boolean sauna) {
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        this.jakuzzi = jakuzzi;
        this.sauna = sauna;
    }

    public Room(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getJakuzzi() {
        return jakuzzi;
    }

    public void setJakuzzi(Boolean jakuzzi) {
        this.jakuzzi = jakuzzi;
    }

    public Boolean getSauna() {
        return sauna;
    }

    public void setSauna(Boolean sauna) {
        this.sauna = sauna;
    }

}
