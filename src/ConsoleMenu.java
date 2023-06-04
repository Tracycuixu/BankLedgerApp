import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleMenu {
    static Scanner input = new Scanner(System.in);

    public static void loginAction() {
        LoginController loginController = new LoginController();

        System.out.println("##########################################");
        System.out.println("#                Login in                #");
        System.out.println("##########################################");
        do {
            System.out.println("\nPlease select one of these options");
            System.out.println("\t\t\t1 login");
            System.out.println("\t\t\t2 register");
            System.out.println("\t\t\t3 Exit");
            System.out.print("Select(1-3): ");
            try {
                int option = input.nextInt();
                switch (option) {
                    case 1:
                        loginController.login();
                        break;
                    case 2:
                        loginController.register();
                        break;
                    case 3:
                        System.exit(1);
                }
            } catch (InputMismatchException ex) {
                input.nextLine();
            }
        } while (!User.isLogin);
    }

    public static void ledgerAction(String currentUser) {
        LedgerController ledgerController = new LedgerController(currentUser);

        System.out.println("##########################################");
        System.out.println("#                                        #");
        System.out.println("#       Welcome to Ledger System         #");
        System.out.println("#                                        #");
        System.out.println("##########################################");
        while (true) {
            System.out.println("\nPlease select one of these options");
            System.out.println("\t\t\t1 Show all records");
            System.out.println("\t\t\t2 Add a credit");
            System.out.println("\t\t\t3 Make a debit");
            System.out.println("\t\t\t4 Modify a record");
            System.out.println("\t\t\t5 Delete a record");
            System.out.println("\t\t\t6 Sort ledger by Date");
            System.out.println("\t\t\t7 Exit and save data");
            System.out.print("Select(1-7): ");
            try {
                int option = input.nextInt();
                switch (option) {
                    case 1:
                        ledgerController.listAllRecords();
                        break;
                    case 2:
                        ledgerController.addCredit();
                        break;
                    case 3:
                        ledgerController.MakeDebit();
                        break;
                    case 4:
                        ledgerController.ModifyRecord();
                        break;
                    case 5:
                        ledgerController.deleteRecord();
                        break;
                    case 6:
                        ledgerController.sortByDate();
                        break;
                    case 7:
                        ledgerController.saveAllRecords();
                        System.exit(1);
                }
            } catch (InputMismatchException ex) {
                input.nextLine();
            }
        }
    }
}
