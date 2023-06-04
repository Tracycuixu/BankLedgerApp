import java.time.LocalDate;

public class Ledger {
    private LocalDate date;
    private String description;

    public static double balance = 0.0;

    public Ledger(LocalDate date, String description) throws IllegalArgumentException {
        setDate(date);
        setDescription(description);
    }

    public void setDate(LocalDate date) throws IllegalArgumentException {
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("the date can not be after today");
        }
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
