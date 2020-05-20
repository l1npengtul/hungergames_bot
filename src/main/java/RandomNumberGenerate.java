import java.util.Random;

public class RandomNumberGenerate {
    Random rngNumbers = new Random();
    public RandomNumberGenerate(){

    }

    public int generateRandomNumber(int min, int max){
        return rngNumbers.nextInt(max - min) + min;
    }
    // In percent integer form (93% = 93)

    public boolean coinTossProbabilities(int probabilityGreat, int probabilityMax){
        int roll = rngNumbers.nextInt(probabilityMax);
        return roll < probabilityGreat;
    }
}
