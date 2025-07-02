import exceptions.IncorrectNominalValue;
import exceptions.InsufficientFunds;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 Банкомат.
 Инициализируется набором купюр и умеет выдавать купюры для заданной суммы, либо отвечать отказом.
 При выдаче купюры списываются с баланса банкомата.
 Допустимые номиналы: 50₽, 100₽, 500₽, 1000₽, 5000₽.
 */
public class Bankomat {

    private int fifty;
    private int hundred;
    private int fiveHundred;
    private int thousand;
    private int fiveThousand;

    public Bankomat(int fifty, int hundred, int fiveHundred, int thousand, int fiveThousand) {
        this.fifty = fifty;
        this.hundred = hundred;
        this.fiveHundred = fiveHundred;
        this.thousand = thousand;
        this.fiveThousand = fiveThousand;
    }

    public int balance() {
        int balance = (50 * fifty) + (100 * hundred) + (500 * fiveHundred) + (1000 * thousand) + (5000 * fiveThousand);
        return balance;
    }

    Map<NominalValue, Integer> bank = new LinkedHashMap<>();

    public Map<NominalValue, Integer> getCash(int sum) throws InsufficientFunds, IncorrectNominalValue {

        int balance = balance();
        if (sum > balance) {
            throw new InsufficientFunds("Недостаточно средств в банкомате");
        }
        Map<NominalValue, Integer> issuedBanknotes = new LinkedHashMap<>();

        bank.put(NominalValue.RUB_5000, fiveThousand);
        bank.put(NominalValue.RUB_1000, thousand);
        bank.put(NominalValue.RUB_500, fiveHundred);
        bank.put(NominalValue.RUB_100, hundred);
        bank.put(NominalValue.RUB_50, fifty);

        for (Map.Entry<NominalValue, Integer> entry : bank.entrySet()) {
            NominalValue nominal = entry.getKey();
            int nominalValue = nominal.getValue();
            int banknotes = entry.getValue();

            if (sum >= nominalValue && banknotes >= 1) {
                int needNotes = sum/nominalValue;
                int giveNotes = Math.min(needNotes,banknotes);
                if (giveNotes > 0) {
                    int giveAmount = needNotes*nominalValue;
                    sum -= giveAmount;
                    banknotes -= giveNotes;
                    balance -= giveAmount;

                    issuedBanknotes.put(nominal, giveNotes);
                    bank.put(nominal, banknotes);
                }
            }
        } if (sum > 0){
            throw new IncorrectNominalValue("Отсутвует нужный номинал");
        }
        fiveThousand = bank.get(NominalValue.RUB_5000);
        thousand = bank.get(NominalValue.RUB_1000);
        fiveHundred = bank.get(NominalValue.RUB_500);
        hundred = bank.get(NominalValue.RUB_100);
        fifty = bank.get(NominalValue.RUB_50);

        return issuedBanknotes;

    }
}
