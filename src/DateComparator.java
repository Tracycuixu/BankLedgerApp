import java.util.Comparator;

public class DateComparator implements Comparator<Ledger> {

    @Override
    public int compare(Ledger o1, Ledger o2) {
        return o2.getDate().compareTo(o1.getDate());
    }
}
