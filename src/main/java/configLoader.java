import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configLoader {
    public static String getDisordAuthToken(){
        // NOTE that this discord.cfg file will be empty. Populate the
        // DISCORD_AUTH_TOKEN field with your own discord bot auth token.
        // such as
        // DISCORD_AUTH_TOKEN = <token>
        Properties confg = new Properties();
        String cfg_path = "src/main/resources/discord.cfg";
        InputStream cfg_read = null;

        try {
            cfg_read = new FileInputStream(cfg_path);
        } catch (FileNotFoundException fnte){
            System.err.print("CONFIGURATION FILE discord.cfg NOT FOUND. EXITING...\n");
            fnte.printStackTrace();
            System.exit(1);
        }
        try{
            confg.load(cfg_read);
        } catch (IOException ioexcept){
            System.err.print("ERROR READING FILE. EXITING...\n");
            ioexcept.printStackTrace();
            System.exit(1);
        }
        return confg.getProperty("DISCORD_AUTH_TOKEN");
    }

    public static String getCommandPrefix(){
        // NOTE that this field will return "!" if it is empty.
        // DEFAULT: hg!
        Properties confg = new Properties();
        String cfg_path = "src/main/resources/discord.cfg";
        InputStream cfg_read = null;

        try {
            cfg_read = new FileInputStream(cfg_path);
        } catch (FileNotFoundException fnte){
            System.err.print("CONFIGURATION FILE discord.cfg NOT FOUND. EXITING...\n");
            fnte.printStackTrace();
            System.exit(1);
        }
        try{
            confg.load(cfg_read);
        } catch (IOException ioexcept){
            System.err.print("ERROR READING FILE. EXITING...\n");
            ioexcept.printStackTrace();
            System.exit(1);
        }
        if (confg.getProperty("CMD_PREFIX") != null){
            return confg.getProperty("CMD_PREFIX");
        }
        else{
            return "!";
        }
    }
}
