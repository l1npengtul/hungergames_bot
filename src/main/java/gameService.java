import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Thread.sleep;
import static java.util.Collections.shuffle;
import static java.util.Collections.sort;


/*
TODO: Make smart algorithem for counting kills/day -- DONE
TODO: Healthkits and Environmental Death
TODO: Custom Death Messages
TODO: Make threaded, make gameID
TODO: PFP and automatic graphic creation
*/

//who is rem
//i love emilia


public class gameService implements Runnable{

    List<String> playerNames = new ArrayList<String>();
    String gameID;
    MessageReceivedEvent messageReceivedEvent;
    int mKillRound;

    public gameService(List<String> names, String assignedGameID, MessageReceivedEvent msgrevent, int kpr){

    }

    public void run(){

    }


    public void startGame(String @NotNull [] players, MessageReceivedEvent event) throws InterruptedException {

        RandomNumberGenerate rngGen = new RandomNumberGenerate();

        boolean winnerDecided = false;
        List<Player> player_list = new ArrayList<Player>();

        for (String player : players) {
            player_list.add(new Player(player));
        }

        TextChannel currentChannel = event.getTextChannel();

        int currentlyAlive = 0;
        int currentDay = 1;
        int playersKilled = player_list.size();
        MessageChannel channel = event.getChannel();


        while (!winnerDecided){
            if(currentDay % 2 == 0){
                channel.sendMessage( currentDay / 2 + " Day:")
                        .queue();
            }
            else{
                channel.sendMessage(currentDay / 2 + " Night:")
                        .queue();
            }

            System.out.println("Current Day" + currentDay);
            shuffle(player_list);

            List<Player> playerDeadToday = new ArrayList<Player>();
            List<Player> player_alive = new ArrayList<Player>();
            for (Player p : player_list){
                if (p.isAlive()) {
                    player_alive.add(p);
                }
            }

            int toDie = numberToKill(player_alive.size(), 4);
            int deadToday = 0;

            shuffle(player_alive);

            for (int i = 0; toDie > i; i++){
                // PvP Death
                int pvpOrEnv = rngGen.generateRandomNumber(0,9);
                System.out.println(pvpOrEnv);
                if(pvpOrEnv < 8){
                    boolean rngPicked = false;
                    int randomKiller = rngGen.generateRandomNumber(1, player_alive.size());;
                    while (!rngPicked){
                        randomKiller = rngGen.generateRandomNumber(1, player_alive.size());
                        if (randomKiller == i){
                            rngPicked = false;
                        }
                        else {
                            rngPicked = true;
                        }
                    }

                    player_alive.get(i).die(currentDay, playersKilled, player_alive.get(randomKiller));
                    player_alive.get(randomKiller).kill(player_alive.get(i));
                    playerDeadToday.add(player_alive.get(i));
                    playersKilled--;
                    System.out.println(killMessage.generateBasicKillMessage(player_alive.get(i).getName(), player_alive.get(i).killer.getName()));
                    // i dont know why the fuck its like this, but it prints swapped for some god forsaken reason
                    sendMessage.sendMessageusingEvent(currentChannel, killMessage.generateBasicKillMessage(player_alive.get(i).killer.getName(), player_alive.get(i).getName()));
                }
                else{
                    int randomEnvDeath = rngGen.generateRandomNumber(0,2);
                    System.out.println("RAnd Env Death:" + randomEnvDeath);
                    switch (randomEnvDeath){
                        //Pit Death
                        case 0:
                            player_alive.get(i).die(currentDay, playersKilled, new Player("Pit"));
                            playersKilled--;
                            playerDeadToday.add(player_alive.get(i));
                            sendMessage.sendMessageusingEvent(currentChannel,killMessage.generatePitDeath(player_alive.get(i).getName()));
                            System.out.println(killMessage.generatePitDeath(player_alive.get(i).getName()));
                            break;
                        //Isekai Truck Fuffilment Death
                        case 1:
                            player_alive.get(i).die(currentDay, playersKilled, new Player("The Isekai Truck"));
                            playersKilled--;
                            playerDeadToday.add(player_alive.get(i));
                                 sendMessage.sendMessageusingEvent(currentChannel,killMessage.generateIsekaiDeath(player_alive.get(i).getName()));
                            System.out.println(killMessage.generateIsekaiDeath(player_alive.get(i).getName()));
                            break;
                        //Witch Cult Stabbing
                        case 2:
                            player_alive.get(i).die(currentDay, playersKilled, new Player("Witch Cult"));
                            playersKilled--;
                            playerDeadToday.add(player_alive.get(i));
                            sendMessage.sendMessageusingEvent(currentChannel,killMessage.generateWitchDeath(player_alive.get(i).getName()));
                            System.out.println(killMessage.generateWitchDeath(player_alive.get(i).getName()));
                            break;
                    }
                }
            }

            int currentAlive = 0;
            Player winningPlayer = new Player("");
            for (Player p : player_list){
                if (p.isAlive()){
                    currentAlive++;
                    winningPlayer = p;
                }
            }
            if (currentAlive == 1){
                sendMessage.sendMessageusingEvent(currentChannel, "The winner is: " + winningPlayer.getName());
                winnerDecided = true;
            }
            // Summary:
            /*
            if (!winnerDecided) {
                Collections.sort(playerDeadToday);
                sendMessage.sendMessageusingEvent(currentChannel, "Summary Day" + currentDay);
                for (Player p : playerDeadToday) {
                    if (!(p.isAlive())) {
                        sendMessage.sendMessageusingEvent(currentChannel, p.getName() + " was killed by " + p.killer.getName() + " and placed #" + p.getPlacement());
                    }
                }
            }*/
            currentDay = currentDay + 1;
            sleep(1000);
        }
        sort(player_list);
        for (Player p : player_list){
            if(p.isAlive()){
                sendMessage.sendMessageusingEvent(currentChannel, p.getName() + " won with " + p.killedPlayers.size() + "kill(s).");
            }
            else{
                sendMessage.sendMessageusingEvent(currentChannel, p.getName() + " got " + p.killedPlayers.size() + " kill(s) and placed #" + p.getPlacement());
            }
        }
    }

    public int numberToKill(int remainingAlive,int maxKillRound){
        RandomNumberGenerate rngGen = new RandomNumberGenerate();
        if (remainingAlive > maxKillRound-1){
            return rngGen.generateRandomNumber(1,maxKillRound);
        }
        else if (remainingAlive > 2){
            return rngGen.generateRandomNumber(1,remainingAlive);
        }
        else{
            return 1;
        }
    }
}

