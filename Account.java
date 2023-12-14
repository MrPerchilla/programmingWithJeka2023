package banks;

public class Account extends Bank  {
    double balance;
    String name;
    String account_Id;

    public Account(double balance, String name, String account_Id) {
        this.balance = balance;
        this.name = name;
        this.account_Id = account_Id;
    }

    public double getBalance() {
        return balance;


    }

    public String getName() {
        return name;

    }

    public String getAccount_Id() {
        return account_Id;

    }

}