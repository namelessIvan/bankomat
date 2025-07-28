import exceptions.IncorrectNominalValue;
import exceptions.InsufficientFunds;
import Enum.NominalValue;
import Enum.Currency;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 Банкомат.
 Инициализируется набором купюр и умеет выдавать купюры для заданной суммы, либо отвечать отказом.
 При выдаче купюры списываются с баланса банкомата.
 Допустимые номиналы: 50₽, 100₽, 500₽, 1000₽, 5000₽.
 */
public class BankomatRUB {

    private int fiftyRUB;
    private int hundredRUB;
    private int fiveHundredRUB;
    private int thousandRUB;
    private int fiveThousandRUB;

    public BankomatRUB(int fiftyRUB, int hundredRUB, int fiveHundredRUB, int thousandRUB, int fiveThousandRUB) {
        this.fiftyRUB = fiftyRUB;
        this.hundredRUB = hundredRUB;
        this.fiveHundredRUB = fiveHundredRUB;
        this.thousandRUB = thousandRUB;
        this.fiveThousandRUB = fiveThousandRUB;
    }

    public int balance() {
        int balance = (50 * fiftyRUB) + (100 * hundredRUB) + (500 * fiveHundredRUB) + (1000 * thousandRUB) + (5000 * fiveThousandRUB);
        return balance;
    }

    Map<CurrencyNominal, Integer> bank = new LinkedHashMap<>();

    public Map<CurrencyNominal, Integer> getCash(int sum) throws InsufficientFunds, IncorrectNominalValue {

        int balance = balance();
        if (sum > balance) {
            throw new InsufficientFunds("Недостаточно средств в банкомате");
        }
        Map<CurrencyNominal, Integer> issuedBanknotes = new LinkedHashMap<>();

        bank.put(new CurrencyNominal(5000, Currency.RUB), fiveThousandRUB);
        bank.put(new CurrencyNominal(1000, Currency.RUB), thousandRUB);
        bank.put(new CurrencyNominal(500, Currency.RUB), fiveHundredRUB);
        bank.put(new CurrencyNominal(100, Currency.RUB), hundredRUB);
        bank.put(new CurrencyNominal(50, Currency.RUB), fiftyRUB);

        for (Map.Entry<CurrencyNominal, Integer> entry : bank.entrySet()) {
            CurrencyNominal nominal = entry.getKey();
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
        fiveThousandRUB = bank.get(new CurrencyNominal(5000, Currency.RUB));
        thousandRUB = bank.get(new CurrencyNominal(1000, Currency.RUB));
        fiveHundredRUB = bank.get(new CurrencyNominal(500, Currency.RUB));
        hundredRUB = bank.get(new CurrencyNominal(100, Currency.RUB));
        fiftyRUB = bank.get(new CurrencyNominal(50, Currency.RUB));

        return issuedBanknotes;

    }
}
