import exceptions.InsufficientFunds;

/**
 Банкомат.
 Инициализируется набором купюр и умеет выдавать купюры для заданной суммы, либо отвечать отказом.
 При выдаче купюры списываются с баланса банкомата.
 Допустимые номиналы: 50₽, 100₽, 500₽, 1000₽, 5000₽.
 */
public class Bankomat {

    private int balance;

    public Bankomat(int balance) {
        this.balance = balance;
    }

    public void getCash(int sum) {
        if (sum > balance) {
            throw new InsufficientFunds("Недостаточно средств в банкомате");
        }
        for (NominalValue nominalValue : NominalValue.values()) {
            if (nominalValue.getValue() == sum) {
                switch (nominalValue){
                    case RUB_50:
                        balance -= sum;
                        System.out.println("Выдано 50р");
                        break;
                    case RUB_100:
                        balance -= sum;
                        System.out.println("Выдано 100р");
                        break;
                    case RUB_500:
                        balance -= sum;
                        System.out.println("Выдано 500р");
                        break;
                    case RUB_1000:
                        balance -= sum;
                        System.out.println("Выдано 1000р");
                        break;
                    case RUB_5000:
                        balance -= sum;
                        System.out.println("Выдано 5000р");
                        break;
                }
            }
        }
    }
}
