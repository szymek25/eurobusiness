package pl.eurobusiness.dto;

import org.hibernate.validator.constraints.NotBlank;

public class PayForm {
    @NotBlank
    private Integer value;

    private String destination;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
