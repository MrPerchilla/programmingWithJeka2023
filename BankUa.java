package banks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BankUa {
        public void runApp()throws IOException{
            // Creating object of class inside main() method
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            ArrayList<Bank> bankArrayList = new ArrayList<>();
            Bank b = new Bank();
            Account account;
            int numberAccount = 0;
            int numberBank;
            int removeIndex;
            int pickBank;
            int selectedBankIndex = -1; // Add this line outside the while loop
            int i = 0;
            while (i == 0) {
                System.out.println("Please choose an option:");

                System.out.println("""
                        1: Add bank <--- *\s
                        2: Add account <---*
                        3: Get bank clients info <--- *\s
                        4: Get account <--- *\s
                        5: Deposit <--- *\s
                        6: Withdraw <--- *\s
                        7: Remove account <--- *\s
                        8: Change bank <--- *\s
                        9: Get bank list <---*
                        0: Exit <--- *""");
                int a = Integer.parseInt(br.readLine());

                switch (a) {
                    case 1:
                        System.out.println("       * ---> Give bank name <--- *");
                        String bankName = br.readLine();
                        b = new Bank(bankName);
                        bankArrayList.add(b);
                        selectedBankIndex = bankArrayList.size() - 1; // Set the selected bank
                        System.out.println("(!) Bank " + "\"" + bankName + "\"" + " has been added.\n");
                        break;

                    case 2:
                        System.out.println(b.bankAccount.size());
                        if (bankArrayList.contains(b)) {
                            System.out.println("          * ---> Account info  <--- *");
                            System.out.println("Please fill up information below (top to bottom)");
                            System.out.println("Balance: ?");
                            double balance = Double.parseDouble(br.readLine());
                            System.out.println("Name: ?");
                            String name = br.readLine();
                            System.out.println("Id: ?");
                            String id = br.readLine();
                            account = new Account(balance, name, id);

                            System.out.println("Pick a bank to add an account: ");
                            b.getBanks(bankArrayList);
                            pickBank = Integer.parseInt(br.readLine());
                            bankArrayList.get(pickBank).addAccount(account);

                            String formattedBalance = String.format("%,.0f$", account.getBalance());
                            System.out.println("+ Created account " + "\nBalance: " + formattedBalance + "\nName: " + account.getName() +
                                    "\nAccount Id: " + account.getAccount_Id());
                            System.out.println();
                            System.out.println(b.bankAccount.size());
                        } else {
                            System.out.println("(!) You have to declare a bank before adding an account.\n");
                        }
                        break;
                    case 3:
                        if (bankArrayList.isEmpty()) {
                            System.out.println("(!) No banks provided. Add at least one bank.\n");
                            break;
                        }

                        System.out.println("          * ---> Choose bank  <--- *");
                        b.getBanks(bankArrayList);
                        System.out.println("Enter specified bank's number to choose:");
                        numberBank = Integer.parseInt(br.readLine());

                        // Validate the bank index
                        if (numberBank < 0 || numberBank >= bankArrayList.size()) {
                            System.out.println("(!) Invalid bank selection.");
                            break;
                        }

                        Bank selectedBankC = bankArrayList.get(numberBank);
                        System.out.println("* ---> " + selectedBankC.getBankName());

                        if (!selectedBankC.bankAccount.isEmpty()) {
                            // Display account information for the selected bank
                            selectedBankC.getGeneralAccountInfo();
                        } else {
                            System.out.println("(!) Bank \"" + selectedBankC.getBankName() + "\" has no clients yet.\n");
                        }
                        break;

                    case 4:
                        // bankArrayList.get(pickBank).addAccount(account);
                        if (selectedBankIndex >= 0 && selectedBankIndex < bankArrayList.size()) {
                            System.out.println("          * ---> Choose bank  <--- *");
                            System.out.println("Enter specified bank's number to choose:");
                            b.getBanks(bankArrayList);
                            numberBank = Integer.parseInt(br.readLine());
                            System.out.println("* ---> " + bankArrayList.get(numberBank).getBankName());
                        } else {
                            System.out.println("(!) No banks provided. Add at least one bank.\n");
                            break;
                        }
                        if (bankArrayList.get(numberBank).bankAccount.isEmpty()) {
                            System.out.println("(!) Bank \"" + bankArrayList.get(numberBank).getBankName() + "\" has no clients yet.\n");
                        } else {
                            Bank selectedBank = bankArrayList.get(numberBank);
                            System.out.println("Choose an account (enter number):");
                            System.out.println("-----------------------------------------------");
                            selectedBank.getGeneralAccountInfo();
                            numberAccount = Integer.parseInt(br.readLine());
                            Account selectedAccount = selectedBank.bankAccount.get(numberAccount);
                            Bank.getInfo(selectedAccount ,selectedBank);
                            System.out.println();

                        }
                        break;

                    case 5:
                        if (selectedBankIndex >= 0 && selectedBankIndex < bankArrayList.size()) {
                            System.out.println("(@) Number of accounts: " + bankArrayList.get(selectedBankIndex).bankAccount.size());


                            System.out.println("          * ---> Choose bank  <--- *");
                            b.getBanks(bankArrayList);
                            System.out.println("Enter specified bank's number to choose:");
                            numberBank = Integer.parseInt(br.readLine());

                            // Update selectedBankIndex based on the chosen bank
                            if (numberBank >= 0 && numberBank < bankArrayList.size()) {
                                selectedBankIndex = numberBank;
                            } else {
                                System.out.println("(!) Invalid bank selection.");
                                break;
                            }
                            if (bankArrayList.get(selectedBankIndex).bankAccount.isEmpty()) {
                                System.out.println("(!) Bank \"" + bankArrayList.get(selectedBankIndex).getBankName() + "\" has no clients yet.\n");
                            } else {
                                Bank selectedBank = bankArrayList.get(selectedBankIndex);
                                System.out.println("* ---> " + selectedBank.getBankName());

                                System.out.println("Enter account number: ");
                                selectedBank.getGeneralAccountInfo();
                                numberAccount = Integer.parseInt(br.readLine());

                                // Ensure the chosen account is within the valid range for the selected bank
                                if (numberAccount >= 0 && numberAccount < selectedBank.bankAccount.size()) {
                                    Account selectedAccount = selectedBank.bankAccount.get(numberAccount);
                                    Bank.getInfo(selectedAccount, selectedBank);

                                    System.out.println("Enter deposit: ");
                                    int deposit = Integer.parseInt(br.readLine());
                                    selectedAccount.deposit(selectedAccount, deposit);
                                } else {
                                    System.out.println("(!) Invalid account selection.");
                                    break;
                                }
                            }
                        } else {
                            System.out.println("(!) No banks provided. Add at least one bank.\n");
                        }
                        break;
//     System.out.println("(!) Bank: " + bank.getBankName() + " is empty.");

                    case 6:
                        if (selectedBankIndex >= 0 && selectedBankIndex < bankArrayList.size()) {
                            System.out.println("(@) Number of accounts: " + bankArrayList.get(selectedBankIndex).bankAccount.size());

                            System.out.println("          * ---> Choose bank  <--- *");
                            b.getBanks(bankArrayList);
                            System.out.println("Enter specified bank's number to choose:");
                            numberBank = Integer.parseInt(br.readLine());

                            // Update selectedBankIndex based on the chosen bank
                            if (numberBank >= 0 && numberBank < bankArrayList.size()) {
                                selectedBankIndex = numberBank;
                            } else {
                                System.out.println("(!) Invalid bank selection.");
                                break;
                            }
                            if (bankArrayList.get(selectedBankIndex).bankAccount.isEmpty()) {
                                System.out.println("(!) Bank \"" + bankArrayList.get(selectedBankIndex).getBankName() + "\" has no clients yet.\n");
                            } else {
                                Bank selectedBank = bankArrayList.get(selectedBankIndex);
                                System.out.println("* ---> " + selectedBank.getBankName());

                                System.out.println("Enter account number: ");
                                selectedBank.getGeneralAccountInfo();
                                numberAccount = Integer.parseInt(br.readLine());

                                // Ensure the chosen account is within the valid range for the selected bank
                                if (numberAccount >= 0 && numberAccount < selectedBank.bankAccount.size()) {
                                    Account selectedAccount = selectedBank.bankAccount.get(numberAccount);
                                    Bank.getInfo(selectedAccount, selectedBank);

                                    System.out.println("Enter withdrawal: ");
                                    int withdrawal = Integer.parseInt(br.readLine());
                                    selectedAccount.withdraw(selectedAccount, withdrawal);
                                } else {
                                    System.out.println("(!) Invalid account selection.");
                                    break;
                                }
                            }
                        } else {
                            System.out.println("(!) No banks provided. Add at least one bank.\n");
                        }
                        break;

                    case 7:
                        boolean anyBankWithClients = false;

                        for (Bank bank : bankArrayList) {
                            if (!bank.bankAccount.isEmpty()) {
                                anyBankWithClients = true;
                                break;
                            }
                        }

                        if (!anyBankWithClients) {
                            System.out.println("(!) All banks are empty.\n");
                            break;
                        }

                        System.out.println("          * ---> Choose bank  <--- *");
                        b.getBanks(bankArrayList);
                        System.out.println("Enter specified bank's number to choose:");
                        numberBank = Integer.parseInt(br.readLine());

                        // Update selectedBankIndex based on the chosen bank
                        if (numberBank >= 0 && numberBank < bankArrayList.size()) {
                            selectedBankIndex = numberBank;
                        } else {
                            System.out.println("(!) Invalid bank selection.");
                            break;
                        }

                        Bank selectedBank = bankArrayList.get(selectedBankIndex);
                        System.out.println("* ---> " + selectedBank.getBankName());
                        // Update selectedBankIndex based on the chosen bank
                        if (!(numberAccount >= 0 && numberAccount < selectedBank.bankAccount.size())) {
                            System.out.println("(!) Bank \"" + bankArrayList.get(selectedBankIndex).getBankName() + "\" has no clients yet.\n");
                            break;
                        }
                        // Ensure the chosen account is within the valid range for the selected bank
                        Account selectedAccount = selectedBank.bankAccount.get(numberAccount);
                        Bank.getInfo(selectedAccount, selectedBank);
                        System.out.println("Choose account to remove (enter number):");
                        bankArrayList.get(selectedBankIndex).getGeneralAccountInfo();
                        removeIndex = Integer.parseInt(br.readLine());

                        // Get the account before removing it
                        Account removedAccount = bankArrayList.get(selectedBankIndex).bankAccount.get(removeIndex);

                        if (bankArrayList.get(selectedBankIndex).removeAccount(removeIndex)) {
                            System.out.println("(!) Account \"" + removedAccount.getName() + "\" removed successfully.");

                            System.out.println("(@) Number of accounts after removal: " + bankArrayList.get(selectedBankIndex).bankAccount.size());

                            if (bankArrayList.get(selectedBankIndex).bankAccount.isEmpty()) {
                                System.out.println("\n***                 B A N K                 ***");
                                System.out.println("-----------------------------------------------");
                                System.out.println(b.bankName);
                                System.out.println("-----------------------------------------------");
                                System.out.println("                    IS EMPTY");
                                System.out.println("-----------------------------------------------");
                                System.out.println();
                            } else {
                                bankArrayList.get(selectedBankIndex).getGeneralAccountInfo();
                            }
                        } else {
                            System.out.println("(!) Unable to remove the account.");
                        }

                        break;

                    case 8:
                        if (bankArrayList.isEmpty()) {
                            System.out.println("(!) No banks provided. Add at least one bank.\n");
                            break;
                        }

                        System.out.println("          * ---> Choose source bank  <--- *");
                        b.getBanks(bankArrayList);
                        System.out.println("Enter source bank's number to choose:");
                        int sourceBankIndex = Integer.parseInt(br.readLine());

                        // Validate the source bank index
                        if (sourceBankIndex < 0 || sourceBankIndex >= bankArrayList.size()) {
                            System.out.println("(!) Invalid source bank selection.");
                            break;
                        }

                        Bank selectedBankA = bankArrayList.get(sourceBankIndex);
                        System.out.println("* ---> Source Bank: " + selectedBankA.getBankName());

                        System.out.println("Enter number of an account you would like to transfer:");
                        selectedBankA.getGeneralAccountInfo();
                        int sourceAccountIndex = Integer.parseInt(br.readLine());

                        // Validate the source account index
                        if (sourceAccountIndex < 0 || sourceAccountIndex >= selectedBankA.bankAccount.size()) {
                            System.out.println("(!) Invalid source account selection.");
                            break;
                        }

                        Account selectedAccountT = selectedBankA.bankAccount.get(sourceAccountIndex);
                        Bank.getInfo(selectedAccountT, selectedBankA);

                        System.out.println("          * ---> Choose destination bank  <--- *");
                        b.getBanks(bankArrayList);
                        System.out.println("Enter destination bank's number to choose:");
                        int destinationBankIndex = Integer.parseInt(br.readLine());

                        // Validate the destination bank index
                        if (destinationBankIndex < 0 || destinationBankIndex >= bankArrayList.size()) {
                            System.out.println("(!) Invalid destination bank selection.");
                            break;
                        }

                        Bank selectedBankB = bankArrayList.get(destinationBankIndex);
                        System.out.println("* ---> Destination Bank: " + selectedBankB.getBankName());

                        // Perform the transfer
                        Bank.changeBank(selectedBankA, selectedBankB, selectedAccountT);
                        break;
                    case 9:
                        System.out.println("Banks list:");
                        if (bankArrayList.contains(b)) {
                            b.getBanks(bankArrayList);
                            System.out.println();
                        } else {
                            System.out.println("(!) No banks provided. Add at least one bank.\n");

                        }
                        break;
                    case 0:
                        System.out.println("    ***  Exit is complete  ***");
                        i++;
                        break;

                }


            }
        }
    }
