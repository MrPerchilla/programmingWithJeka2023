package banks;

import java.util.ArrayList;

public class Bank {
    String bankName;

    public String getBankName() {
        return bankName;
    }

    ArrayList<Account> bankAccount;

    public Bank(String name) {
        bankName = name;
        System.out.println("+ Created bank: " + "\"" + bankName + "\"");
        bankAccount = new ArrayList<>();
    }

    public Bank() {
        bankAccount = new ArrayList<>();
    }

    public void addAccount(Account adder) {
        bankAccount.add(adder);
        System.out.println("+ Account \"" + adder.getAccount_Id() + "\" added to " + bankName);
        System.out.println("(!) " + adder.getName() + "  becomes a proud member of " + bankName + ".");
        System.out.println();
    }

    public boolean removeAccount(int index) {
        if (index >= 0 && index < bankAccount.size()) {
            bankAccount.remove(index);
            System.out.println("- Account removed from " + bankName);
            return true;
        } else {
            System.out.println("(!) Invalid index. No account removed.\n");
            return false;
        }
    }

    public void deposit(Account balanceAccount, double deposit) {
        balanceAccount.balance += deposit;
        String formattedDeposit = String.format("%,.0f", deposit);
        System.out.println("Deposit $: " + formattedDeposit);
        String formattedBalance = String.format("%,.0f", balanceAccount.balance);
        System.out.println("Current balance $: " + formattedBalance);
        System.out.println();

    }

    public void withdraw(Account acc, double moneyToWithdraw) {
        acc.balance -= moneyToWithdraw;
        String formattedBalance = String.format("%,.0f", acc.balance);
        String formattedWithdraw = String.format("%,.0f", moneyToWithdraw);
        System.out.println("Withdraw $: " + formattedWithdraw);
        System.out.println("Current balance $: " + formattedBalance);
        System.out.println();
    }

   static public void getInfo(Account account, Bank bank) {
        if (bank.bankAccount.contains(account)) {
            System.out.println(bank.bankName + ":");
            String formattedBalance = String.format("%,.0f$", account.getBalance());
            System.out.println("Balance: " + formattedBalance);
            System.out.println("Name: " + account.getName());
            System.out.println("Account Id: " + account.getAccount_Id());
            System.out.println();
        } else {
            System.out.println("Account with ID: " + "\"" + account.getAccount_Id() + "\"" + " hasn't been found in: " + "\"" + bank.bankName + "\"" + ".");
            System.out.println();
        }

    }

    public void getGeneralAccountInfo() {
        System.out.println("\n***                 B A N K                 ***");
        System.out.println("-----------------------------------------------");
        System.out.println(bankName);
        System.out.println("-----------------------------------------------");
        for (Account accounts : bankAccount) {
            System.out.println("Balance: " + String.format("%,.0f$", accounts.getBalance()));
            System.out.println("Name: " + accounts.getName());
            System.out.println("Account Id: " + accounts.getAccount_Id());
            System.out.println("-----------------------------------------------");
        }
        System.out.println();
    }

    static void changeBank(Bank A, Bank B, Account account) {
        if (A.bankAccount.equals(B.bankAccount)) {
            System.out.println("(!) You are trying to change bank to the same one.\n");
        } else {
            A.bankAccount.remove(account);
            System.out.println(account.getName() + " has been removed from " + A.bankName + ".\n");
            B.bankAccount.add(account);
            System.out.println("(!) Account: " + "\"" + account.getAccount_Id() + "\"" + " has been transferred to " + B.bankName + ".\n");
        }


    }



    void getBanks(ArrayList<Bank> bankArrayList) {
        for (int j = 0; j < bankArrayList.size(); j++) {
            System.out.println(j + "#: " + bankArrayList.get(j).getBankName());
        }
    }

}