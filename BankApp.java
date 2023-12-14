package banks;

import java.io.IOException;

public class BankApp {
    public static void main(String[] args) throws IOException {
        BankUa bank = new BankUa();
        bank.runApp();
    }
}
