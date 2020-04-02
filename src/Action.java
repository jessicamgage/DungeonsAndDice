public class Action {
    private Race race;
    private CharacterClass characterClass;
    private Character character;

    private long takenDamage;
    private long dealtDamage;
    private long restoredHitPoints;

    public String attack(Character attacker, Weapon weapon, Character attackee) throws Exception{
        String actionString = "";

        if(attacker.itemHeld(weapon)){
            long attackRoll = new Dice("1d20").roll();

            if(attackRoll >= attackee.getArmorClass()){
                setDealtDamage(attackee, attacker.useHeldWeapon(weapon));
            }else{
                actionString = "The attack missed!";
            }
        }else{
            actionString = "That weapon is not currently being held...";
        }
        return actionString;
    }

    public long getTakenDamage(){
        return takenDamage;
    }

    public void setTakenDamage(Character character, long takenDamage){
        long newHP = character.getHitPoints();
        newHP -= takenDamage;
        character.setHitPoints(newHP);
    }

    public long getDealtDamage(Character attacker){
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


    public void walk(int walkFeet){
        move(walkFeet);
    }

    public void dash(){
        long dashFeet = race.getWalkSpeed()*2;
        move(dashFeet);
    }

    public void move(long feet){

    }

    public void dodge(){}
    public void help(){}
    public void makeSkillCheck(){}
    public void die(){}
    public void talk(){}

}
