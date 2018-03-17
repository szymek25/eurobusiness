package pl.eurobusiness.domain;

import javax.persistence.*;

@Entity
public class City {

    @Id
    private int id;

    private String name;

    private int price;

    private int valueWithoutProperty;

    private int valueForOneProperty;

    private int valueForTwoProperty;

    private int valueForThreeProperty;

    private int valueForHotel;

    @Column(columnDefinition = "default '0'")
    private int quantityOfProperty;

    private int propertyPrice;

    private int hotelPrice;

    @ManyToOne
    private Player owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getValueForOneProperty() {
        return valueForOneProperty;
    }

    public void setValueForOneProperty(int valueForOneProperty) {
        this.valueForOneProperty = valueForOneProperty;
    }

    public int getValueForTwoProperty() {
        return valueForTwoProperty;
    }

    public void setValueForTwoProperty(int valueForTwoProperty) {
        this.valueForTwoProperty = valueForTwoProperty;
    }

    public int getValueForThreeProperty() {
        return valueForThreeProperty;
    }

    public void setValueForThreeProperty(int valueForThreeProperty) {
        this.valueForThreeProperty = valueForThreeProperty;
    }

    public int getValueForHotel() {
        return valueForHotel;
    }

    public void setValueForHotel(int valueForHotel) {
        this.valueForHotel = valueForHotel;
    }

    public int getQuantityOfProperty() {
        return quantityOfProperty;
    }

    public void setQuantityOfProperty(int quantityOfProperty) {
        this.quantityOfProperty = quantityOfProperty;
    }

    public int getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(int propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

    public int getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(int hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getValueWithoutProperty() {
        return valueWithoutProperty;
    }

    public void setValueWithoutProperty(int valueWithoutProperty) {
        this.valueWithoutProperty = valueWithoutProperty;
    }
}
