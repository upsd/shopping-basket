import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ClockShould {

    @Test
    public void formatTodaysDate() {
        Clock clock = new TestableClock();

        String date = clock.todayAsddmmyyyyString();

        assertThat(date, is("27/07/2018"));
    }

    private class TestableClock extends Clock {

        @Override
        protected LocalDate today() {
            return LocalDate.of(2018, 07, 27);
        }
    }
}
