import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Clock {

    public String todayAsddmmyyyyString() {
        return today().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    protected LocalDate today() {
        return LocalDate.now();
    }
}
