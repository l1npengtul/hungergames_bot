import net.dv8tion.jda.api.entities.TextChannel;

public class sendMessage {
    public static void sendMessageusingEvent(TextChannel textChannel, String message){
        textChannel.sendMessage(message).queue();
    }

}
