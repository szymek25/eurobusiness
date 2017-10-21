package pl.eurobusiness.domain;

import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.List;

@Entity
public class Game {

    @Id
    private String name;
    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    private List<Player> playerList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
