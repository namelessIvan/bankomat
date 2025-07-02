import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Bankomat bank = new Bankomat(0, 5, 5, 5, 5);
        Map<NominalValue, Integer> issued = bank.getCash(50);
        System.out.println("Выдано: " + issued);




    }
}