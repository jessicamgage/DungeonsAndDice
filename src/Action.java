public class Action extends Character{
    private Race race;
    private CharacterClass characterClass;
    private Character character;
    private Item item;
    private Weapon weapon;

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


    public long walk(Character character, int distanceWanted) throws Exception{
        this.race = character.getRace();

        if(distanceWanted < (race.getWalkSpeed())){
            character.move(distanceWanted);
        }else{
            throw new Exception("You cannot move that far with the walk action in one round. You can move up to " +
                    race.getWalkSpeed() + " feet.");
        }

        return (distanceWanted);
    }

    public long dash(Character character, int distanceWanted) throws Exception{
        this.race = character.getRace();

        if(distanceWanted < (race.getWalkSpeed()*2)){
            character.move(distanceWanted);
        }else{
            throw new Exception("You cannot move that far with the dash action in one round. You can move up to " +
                    race.getWalkSpeed()*2 + " feet.");
        }

        return distanceWanted;
    }

    public void dodge(Character dodger, Character attacker){
        //make so next attack is made with disadvantage, and DEX saves are made with advantage
    }

    public void help(){}
    public void makeSkillCheck(){}
    public void die(){}
    public void talk(){}

}
