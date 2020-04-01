public class Action {
    private Race race;
    private CharacterClass characterClass;
    private Character character;

    private long takenDamage;
    private long dealtDamage;
    private long restoredHitPoints;

//    public String attack(Character attackee) throws Exception{
//        long attackRoll = new Dice("1d20").roll();
//        if(attackRoll >= attackee.getArmorClass()){
//            dealtDamage =
//        }
//    }

    public long getTakenDamage(){
        return takenDamage;
    }

    public void setTakenDamage(Character character, long takenDamage){
        long newHP = character.getHitPoints();
        newHP -= takenDamage;
        character.setHitPoints(newHP);
    }

    public long getDealtDamage(){
        return dealtDamage;
    }

    public void setDealtDamage(Character character, long dealtDamage){
        long newHP = character.getHitPoints();
        newHP -= dealtDamage;
        character.setHitPoints(newHP);
    }

    public long getRestoredHitPoints(){
        return restoredHitPoints;
    }

    public void setRestoredHitPoints(Character character, long restoredHitPoints){
        long newHP = character.getHitPoints();
        newHP += restoredHitPoints;
        character.setHitPoints(newHP);
    }


    public void walk(int walkFeet){}
    public void dash(){}

}
