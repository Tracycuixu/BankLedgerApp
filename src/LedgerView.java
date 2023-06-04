import java.util.ArrayList;

public class LedgerView {
    public void printLedgerDetails(ArrayList<Ledger> ledgersList) {
        if (ledgersList.isEmpty()) {
            System.out.println("There are no records in the system. please add a new one");
            return;
        }
        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-15s\n", "no.", "Date", "Description", "Debit", "Credit", "Balance");
        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-15s\n", "---", "----", "-----------", "-----", "------", "-------");
        int i = 1;
        double balance = 0;
        for (Ledger ledger : ledgersList) {
            System.out.printf("%-5d %-15tF %-15s", i, ledger.getDate(), ledger.getDescription());
            if (ledger instanceof DebitLedger) {
                double debit = ((DebitLedger) ledger).getDebit();
                balance -= debit;
                System.out.printf("-%-15.2f %-15s %-15.2f\n", debit, "", balance);
            }
            if (ledger instanceof CreditLedger) {
                double credit = ((CreditLedger) ledger).getCredit();
                balance += credit;
                System.out.printf("%-15s +%-15.2f %-15.2f\n", "", credit, balance);
            }
            i++;
        }
    }
}
