import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Player implements Comparable<Player>{
    String name,pfpUrl;
    int healthKits = 0;
    PLAYER_STATES currentState = PLAYER_STATES.IDLE;
    int placement = 0;
    int deathDay;
    Player killer;
    List<Player> killedPlayers = new ArrayList<Player>();


    public Player(String cname){
        this.name = cname;
    }

    public int getDeathDay() {
        return deathDay;
    }

    public void setDeathDay(int deathDay) {
        this.deathDay = deathDay;
    }

    public int getPlacement() {
        return placement;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }

    public String getName() {
        return name;
    }

    public int getHealthKits() {
        return healthKits;
    }

    public void giveHealthKit() {
        this.healthKits++;
    }

    public PLAYER_STATES getCurrentState() {
        return currentState;
    }

    public void setWounded(Player wounder){
        this.killer = wounder;
        this.currentState = PLAYER_STATES.WOUNDED;
    }


    // Return Boolean = hasDied
    public boolean woundedTick(int currentDay, int currentPlacement){
        if (currentState == PLAYER_STATES.WOUNDED){
            if (healthKits > 0){
                healthKits--;
                killer = null;
                return false;
            }
            else{
                //die(currentDay, currentPlacement);
                return true;
            }
        }
        else{
            return false;
        }
    }

    public boolean isAlive(){
        return currentState != PLAYER_STATES.DEAD;
    }


    public void die(int currentDay, int currentPlacement, Player killerName){
        this.deathDay = currentDay;
        this.placement = currentPlacement;
        this.currentState = PLAYER_STATES.DEAD;
        this.killer = killerName;
    }

    public void kill(Player killedPlayer){
        this.killedPlayers.add(killedPlayer);
    }

    @Override
    public int compareTo(@NotNull Player other) {
        return -(Integer.compare(this.placement, other.placement));
    }
}
