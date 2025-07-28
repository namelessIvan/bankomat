import exceptions.IncorrectNominalValue;
import exceptions.InsufficientFunds;
import Enum.Currency;
import java.util.LinkedHashMap;
import java.util.Map;
import Enum.DollarNominalValue;

public class BankomatUSD {
    private int hundredUSD;
    private int fiftyUSD;
    private int twentyUSD;
    private int tenUSD;
    private int fiveUSD;
    private int twoUSD;
    private int oneUSD;

    public BankomatUSD(int hundredUSD, int fiftyUSD, int twentyUSD, int tenUSD, int fiveUSD, int twoUSD, int oneUSD) {
        this.hundredUSD = hundredUSD;
        this.fiftyUSD = fiftyUSD;
        this.twentyUSD = twentyUSD;
        this.tenUSD = tenUSD;
        this.fiveUSD = fiveUSD;
        this.twoUSD = twoUSD;
        this.oneUSD = oneUSD;
    }

    public int balance() {
        int balance = (100*hundredUSD)+(50*fiftyUSD)+(20*twentyUSD)+(10*tenUSD)+(5*fiveUSD)+(2*twoUSD)+(oneUSD);
        return balance;
    }
    Map<CurrencyNominal, Integer> bank = new LinkedHashMap<>();

    public Map<CurrencyNominal, Integer> getCash(int sum) throws InsufficientFunds, IncorrectNominalValue {
        int balance = balance();
        if (sum > balance) {
            throw new InsufficientFunds("Недостаточно средств в банкомате");
        }
        Map<CurrencyNominal, Integer> issuedBanknotes = new LinkedHashMap<>();

        bank.put(new CurrencyNominal(100, Currency.USD), hundredUSD);
        bank.put(new CurrencyNominal(50, Currency.USD), fiftyUSD);
        bank.put(new CurrencyNominal(20, Currency.USD), twentyUSD);
        bank.put(new CurrencyNominal(10, Currency.USD), tenUSD);
        bank.put(new CurrencyNominal(5, Currency.USD), fiveUSD);
        bank.put(new CurrencyNominal(2, Currency.USD), twoUSD);
        bank.put(new CurrencyNominal(1, Currency.USD), oneUSD);

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
        hundredUSD = bank.get(new CurrencyNominal(100, Currency.USD));
        fiftyUSD = bank.get(new CurrencyNominal(50, Currency.USD));
        twentyUSD = bank.get(new CurrencyNominal(20, Currency.USD));
        tenUSD = bank.get(new CurrencyNominal(10, Currency.USD));
        fiveUSD = bank.get(new CurrencyNominal(5, Currency.USD));
        twoUSD = bank.get(new CurrencyNominal(2, Currency.USD));
        oneUSD = bank.get(new CurrencyNominal(1, Currency.USD));

        return issuedBanknotes;

    }
}
