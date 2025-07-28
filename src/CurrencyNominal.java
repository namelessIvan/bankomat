import Enum.Currency;

import java.util.Objects;

public class CurrencyNominal {

    private int value;
    private Currency currency;

    public CurrencyNominal(int value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public int getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyNominal that = (CurrencyNominal) o;
        return value == that.value && currency == that.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public String toString() {
        return "CurrencyNominal{" +
                "value=" + value +
                ", currency=" + currency +
                '}';
    }
}
