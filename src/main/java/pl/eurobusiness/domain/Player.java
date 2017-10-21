package pl.eurobusiness.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToOne
    private Game game;

    @OneToMany(mappedBy="owner", fetch=FetchType.EAGER)
    private List<City> cityList;

    private int accountAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public int getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(int accountAmount) {
        this.accountAmount = accountAmount;
    }
}
