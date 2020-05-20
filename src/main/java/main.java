import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

        /*
        i fucking hate priscilla and she can go suck a cock then stab herself like in romeo and juliet, except everyone
        would stand up and fucking clap if she did so in pure happiness and crab rave would play on all the radios.
        she deserves to get stabbed in her empty ass head, maybe should have eaten those godamn appas with skin on huh?
        she reeks of entitlement and i fucking hate it, thinks the world revolves around her.
        she deserves to go on r/entitledbitches because that's what she is, an entitled bitch.
        then takes a man down on all of his luck in a corner with psychological trauma then jebaites him into licking her disgusting ass rotting toes
        then kicks him in his face, what a bitch, as if its funny.
        go fucking die in a pit please
        that other guy is cool tho, must suck have such a shitty boss
         */

public class main {
    public static void main(String[] args) throws Exception{
        assert configLoader.getDisordAuthToken() != null;
        // this is a sin, this should be a crime, but it works
        JDA api = JDABuilder
                .
                createDefault(configLoader.getDisordAuthToken())
                .
                addEventListeners(new commandListener())
                .
                setActivity(Activity.playing("SUBARU, COME OUT AND PLAAAY"))
                .
                build();
    }
}
