package pl.eurobusiness.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class GameDTO {
    @NotBlank
    private String player1Name;

    @NotBlank
    private String player2Name;

    @NotBlank
    private String player3Name;

    @NotBlank
    private String player4Name;

    private String name;

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public String getPlayer3Name() {
        return player3Name;
    }

    public void setPlayer3Name(String player3Name) {
        this.player3Name = player3Name;
    }

    public String getPlayer4Name() {
        return player4Name;
    }

    public void setPlayer4Name(String player4Name) {
        this.player4Name = player4Name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
