import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class killMessage {
    public static String generateBasicKillMessage(String killer, String killed){
        return killed + " was killed by " + killer + ".";
    }
    public static String generateBasicEnvKillMessage(String killed){
        return killed + " got too a bit too close to pit-kun.";
    }
    public static String generateIdleMessage(String idler){
        return idler + " daydreams of being Isekai'd.";
    }
    public static String generatePitDeath(String killed){
        return killed + " fell into the pit-of-death";
    }
    public static String generateIsekaiDeath(String killed){
        return killed + " got Isekai'd by Truck-kun with an AK-47";
    }
    @Contract(pure = true)
    public static @NotNull String generateWitchDeath(String killed){
        return killed + " got Hunted Down by the Witch Cult";
    }
}
