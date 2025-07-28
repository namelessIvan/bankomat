import java.util.Map;
import Enum.NominalValue;

public class Main {
    public static void main(String[] args) {

        BankomatRUB bank = new BankomatRUB(5, 5, 5, 5, 5);
        Map<CurrencyNominal, Integer> issued = bank.getCash(5150);
        System.out.println("Выдано: " + issued);

        BankomatUSD bank2 = new BankomatUSD(5,5,5,5,5,5,5);
        Map<CurrencyNominal, Integer> issued2 = bank2.getCash(72);
        System.out.println("Выдано: " + issued2);




    }
}