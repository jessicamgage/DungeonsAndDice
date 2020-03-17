import java.util.Scanner;

public class CharacterBuilder {
    private static String playerRace;

    public String getPlayerRace(){
        return playerRace;
    }

    public void setPlayerRace(String playerRace) throws DiceFormatException{
        System.out.println("Hello! What race of character would you like to play as?");

        Scanner raceQuestion = new Scanner(System.in);
        String raceAnswer = raceQuestion.next();

        if(raceAnswer.equalsIgnoreCase("human")){
            playerRace = new Race(raceAnswer, new Dice("3d6+1"), new Dice("3d6+1"),
                    new Dice("3d6+1"), new Dice("3d6+1"), new Dice("3d6+1"), new Dice("3d6+1"))
                    .toString();
        }else if(raceAnswer.equalsIgnoreCase("dwarf")){
            playerRace = new Race(raceAnswer, new Dice("3d6+1"), new Dice("3d6"),
                    new Dice("3d6+2"), new Dice("3d6"), new Dice("3d6"), new Dice("3d6"))
                    .toString();
        }else{
            playerRace = new Race(raceAnswer, new Dice("3d6"), new Dice("3d6"),
                    new Dice("3d6"), new Dice("3d6"), new Dice("3d6"), new Dice("3d6"))
                    .toString();
        }
        CharacterBuilder.playerRace = playerRace.toString();
    }
}
