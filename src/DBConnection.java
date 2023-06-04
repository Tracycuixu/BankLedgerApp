import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class DBConnection {
    private static final String USERS_FILE = "users.txt";
    private static final String LEDGER_FILE = "/ledger.txt";
    public static ArrayList<User> readUsersFromFile() {
        ArrayList<User> usersList = new ArrayList<>();
        File file = new File(USERS_FILE);
        if (file.exists()) {
            try (Scanner reader = new Scanner(file)) {
                while (reader.hasNextLine()) {
                    String[] line = reader.nextLine().split(";");
                    String userName = line[0];
                    String password = line[1];

                    usersList.add(new User(userName, password));
                }
            } catch (IOException e) {
                System.out.println("there is a problem in reading data " + e.getMessage());
            }
        }
        return usersList;
    }

    public static void saveUsersIntoFile(ArrayList<User> usersList) {
        try (PrintWriter writer = new PrintWriter(USERS_FILE)) {
            for (User user : usersList) {
                writer.println(user.toDataString());
            }
        } catch (IOException e) {
            System.out.println("there is problem in writing the data " + e.getMessage());
        }
    }

    public static ArrayList<Ledger> readUserLedgers(String currentUser) {
        ArrayList<Ledger> ledgersList = new ArrayList<>();
        double balance = 0;

        File file = new File(currentUser + LEDGER_FILE);
        if (file.exists()) {
            try (Scanner reader = new Scanner(file)) {
                while (reader.hasNextLine()) {
                    String[] line = reader.nextLine().split(";");
                    LocalDate date = LocalDate.parse(line[0]);
                    String description = line[1];
                    String debitOrCredit = line[2];
                    if (debitOrCredit.charAt(0) == '+') {
                        double credit = Double.parseDouble(debitOrCredit.substring(1));
                        balance += credit;
                        ledgersList.add(new CreditLedger(date, description, credit));
                    }
                    if (debitOrCredit.charAt(0) == '-') {
                        double debit = Double.parseDouble(debitOrCredit.substring(1));
                        balance -= debit;
                        ledgersList.add(new DebitLedger(date, description, debit));
                    }
                    Ledger.balance = balance;
                }
            } catch (IOException e) {
                System.out.println("there is a problem in reading data " + e.getMessage());
            }
        }
        return ledgersList;
    }

    public static void saveUserLedgers(String currentUser, ArrayList<Ledger> ledgersList) {
        try {
            File ledgerDir = new File(currentUser);
            if (!ledgerDir.exists()) {
                ledgerDir.mkdir();
            }
            PrintWriter writer = new PrintWriter(currentUser + LEDGER_FILE);
            for (Ledger ledger : ledgersList) {
                if (ledger instanceof DebitLedger) {
                    writer.println(((DebitLedger) ledger).toDataString());
                }
                if (ledger instanceof CreditLedger) {
                    writer.println(((CreditLedger) ledger).toDataString());
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("there is problem in writing the data " + e.getMessage());
        }
    }
}
