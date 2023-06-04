import java.time.LocalDate;

public class DebitLedger extends Ledger {
    private double debit;

    public DebitLedger(LocalDate date, String description, double debit) {
        super(date, description);
        setDebit(debit);
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        if (debit < 0) {
            throw new ArithmeticException("Please enter a valid positive number");
        }
        this.debit = debit;
    }

    public String toDataString() {
        return String.format("%s;%s;-%f", this.getDate(), this.getDescription(), this.debit);
    }
}
