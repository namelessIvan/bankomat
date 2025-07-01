public class Main {
    public static void main(String[] args) {

        Bankomat bank = new Bankomat(5000);
        bank.getCash(50);//Выдано
        bank.getCash(100);//Выдано
        bank.getCash(500);//Выдано
        bank.getCash(1000);//Выдано
        bank.getCash(5000);//Не достаточно



    }
}