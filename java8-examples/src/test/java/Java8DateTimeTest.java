

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class Java8DateTimeTest {

  @Test
  public void testConvertLocalDateToDate() {
    LocalDate localDate = LocalDate.of(2017, 8, 10);


    Date date = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    assertEquals(localDate.getYear(), calendar.get(Calendar.YEAR));
    assertEquals(localDate.getMonth().getValue(), calendar.get(Calendar.MONTH) + 1);
    assertEquals(localDate.getDayOfMonth(), calendar.get(Calendar.DAY_OF_MONTH));
  }

  @Test
  public void testConvertLocalDateTimeToDate() {
    LocalDateTime localDateTime = LocalDateTime.of(2017, 8, 10, 23, 10, 15);


    Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    assertEquals(localDateTime.getYear(), calendar.get(Calendar.YEAR));
    assertEquals(localDateTime.getMonth().getValue(), calendar.get(Calendar.MONTH) + 1);
    assertEquals(localDateTime.getDayOfMonth(), calendar.get(Calendar.DAY_OF_MONTH));
  }

  @Test
  public void testConvertDateToLocalDate() {

    Calendar calendar = new GregorianCalendar(2017, Calendar.AUGUST, 20);
    Date date = calendar.getTime();


    //Convert Date to LocalDate
    LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(
            ZoneId.systemDefault()).toLocalDate();


    assertEquals(calendar.get(Calendar.YEAR), localDate.getYear());
    assertEquals(calendar.get(Calendar.MONTH) + 1, localDate.getMonth()
            .getValue());
    assertEquals(calendar.get(Calendar.DAY_OF_MONTH), localDate.getDayOfMonth());

  }

  @Test
  public void testConvertDateToLocalDateTime() {
    Calendar calendar = new GregorianCalendar(2017, Calendar.AUGUST, 20);
    Date date = calendar.getTime();


    //Convert Date to LocalDateTime
    LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId
            .systemDefault()).toLocalDateTime();


    assertEquals(calendar.get(Calendar.YEAR), localDateTime.getYear());
    assertEquals(calendar.get(Calendar.MONTH) + 1, localDateTime.getMonth()
            .getValue());
    assertEquals(calendar.get(Calendar.DAY_OF_MONTH), localDateTime.getDayOfMonth());
    assertEquals(calendar.get(Calendar.HOUR_OF_DAY), localDateTime.getHour());
    assertEquals(calendar.get(Calendar.MINUTE), localDateTime.getMinute());
    assertEquals(calendar.get(Calendar.SECOND), localDateTime.getSecond());

  }
}
