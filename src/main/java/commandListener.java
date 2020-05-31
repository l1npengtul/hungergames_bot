import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class commandListener extends ListenerAdapter {
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
            List<Player> argumentPlayer = new ArrayList<Player>();
            for (int i = 0; i < individualMessage.length; i++) {
                switch (i){
                    case 0:
                        break;
                    case 1:
                        if()
                }
            }

            /*
            try {
                gameService.startGame(testNames, msge);

            }catch (InterruptedException i){
                i.printStackTrace();
            }*/
        }

    }
}


