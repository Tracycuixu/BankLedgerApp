import java.time.LocalDate;

public class CreditLedger extends Ledger {
    private double credit;

    public CreditLedger(LocalDate date, String description, double credit) {
        super(date, description);
        setCredit(credit);
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        if (credit < 0) {
            throw new ArithmeticException("Please enter a valid positive number");
        }
        this.credit = credit;
    }

    public String toDataString() {
        return String.format("%s;%s;+%f", this.getDate(), this.getDescription(), this.credit);
    }
}
