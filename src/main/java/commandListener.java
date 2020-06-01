import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class commandListener extends ListenerAdapter {

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent msge){
        if (msge.getAuthor().isBot()) return;

        Message message = msge.getMessage();
        String content = message.getContentRaw();
        String prefix = configLoader.getCommandPrefix();
        System.out.println(content);



        String[] testNames = {"Emilia", "Subaru", "Beako", "Rem",
                "Ram", "Roswaal", "Crsuch", "Priscilla the Fucking Cunt",
                "Rom", "Felt", "Ms. Let-it-go", "Otto", "Petra", "Wilhelm", "Ferris",
                "Anastasia", "Julius", "Ricardo", "Mimi", "Hetaro", "Tivery",
                "Al", "Reinhard", "Satella", "Echinda", "Petelguese"};


        if (msge.getMessage().getContentRaw().startsWith(prefix + "hg_game")) {
            String messageToParse = msge.getMessage().getContentRaw();
            String[] individualMessage = messageToParse.split(" ");
            boolean isUsingRoleMention = false;
            boolean isUseDefault = false;
            List<Player> argumentPlayer = new ArrayList<Player>();
            for (int i = 0; i < individualMessage.length; i++) {
                switch (i){
                    case 0:
                        break;
                    case 1:
                        if (individualMessage[i].equals("r")){
                            isUsingRoleMention = true;
                        }
                        else if (individualMessage[i].equals("m")){
                            isUsingRoleMention = false;
                        }
                        else{
                            isUseDefault = true;
                            isUsingRoleMention = false;
                        }
                    case 2:
                        if (individualMessage[i].contains(",") && !isUsingRoleMention){
                            //A list of mentions as a CSV
                            String[] mentions = individualMessage[i].split(",");
                            List<String> usersNames = new ArrayList<String>();

                            for(String ping : mentions){
                                StringBuilder idToPass = new StringBuilder();
                                //
                                try{
                                    for (String idIter : ping.split("")){
                                        if (isNumeric(idIter)){
                                            idToPass.append(idIter);
                                        }
                                    }
                                    String nickToAdd = msge.getGuild().getJDA().getUserById(idToPass.toString()).getName();
                                    usersNames.add(nickToAdd);
                                }catch (NullPointerException npe){
                                    sendMessage.sendMessageusingEvent(msge.getTextChannel(), "[ERROR]```" + npe.getClass().getSimpleName()
                                            + "``` User ( "+ idToPass +") likely doesn't exist, has left, or some other error! Continuing anyways... ");
                                }catch (NumberFormatException nfe){
                                    sendMessage.sendMessageusingEvent(msge.getTextChannel(), "[ERROR]```" + nfe.getClass().getSimpleName()
                                            + "``` The ID probably isn't valid or some other error!");
                                }
                            }
                            gameService game = new gameService(usersNames, System.currentTimeMillis()+"gameCSVMENT", msge, 4);
                            new Thread(game).start();

                        }
                        else if (isUsingRoleMention){
                            //do role get here
                        }
                        else{
                            //using testNames
                        }
                }
            }

            /*
            try {
                gameService.startGame(testNames, msge);

            }catch (InterruptedException i){
                i.printStackTrace();
            }*/
        }
        else if (msge.getMessage().getContentRaw().startsWith(prefix+"ping")){
            sendMessage.sendMessageusingEvent(msge.getTextChannel(),"Pong at " +msge.getGuild().getJDA().getGatewayPing() + "ms.");
        }
    }
}


