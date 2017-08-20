

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.*;
import static org.junit.Assert.assertEquals;


public class Java8FormatDate {

  @Test
  public void testFormatDate() {

    //create a date object by using Calendar
    Calendar calendar = new GregorianCalendar(2020, Calendar.AUGUST, 9);
    Date date = calendar.getTime();

    //create a pattern
    String pattern = "dd/MM/yyyy";
    SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
    // format the date
    String olympicDate = dateFormatter.format(date);
    //Validate
    assertEquals("09/08/2020", olympicDate);

  }

  @Test
  public void testFormatDateLongPattern() {
    //create a date
    int year = 2020;
    int month = Calendar.AUGUST;
    int day = 9;
    int hourOfDay = 23;
    int minute = 59;
    int second = 59;
    Calendar calendar = new GregorianCalendar(year, month, day, hourOfDay,
            minute, second);

    Date date = calendar.getTime();

    //create a long pattern with en_US locale
    String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSS";
    SimpleDateFormat dateFormatter =
            new SimpleDateFormat(pattern, new Locale("en", "US"));

    //format the date
    String olympicDate = dateFormatter.format(date);

    //validate
    assertEquals("Sunday August 2020 23:59:59.000", olympicDate);

  }
  @Test
  public void testFormatLocalDate() {

    //create a LocalDate object
    LocalDate date = LocalDate.of(2020, Month.AUGUST, 9);

    //create a pattern
    String pattern = "dd/MM/yyyy";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    // format the date
    String olympicDate = formatter.format(date);

    //Validate
    assertEquals("09/08/2020", olympicDate);

  }
  @Test
  public void testFormatLocalDateUsingPreDefinedFormatter() {

    //create a LocalDate object
    LocalDate date = LocalDate.of(2020, Month.AUGUST, 9);

    // format the date
    String olympicDate =  ISO_DATE.format(date);
    assertEquals("2020-08-09", olympicDate);

    //or use as a pattern
    olympicDate =  date.format(ISO_DATE);
    assertEquals("2020-08-09", olympicDate);

  }
  @Test
  public void testFormatLocalDateTime() {

    //create a LocalDate object
    LocalDateTime dt = LocalDateTime.of(2020, Month.AUGUST, 9, 23, 59, 59);

    //create a pattern
    String pattern = "yyyy/MM/dd HH:mm:ss";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    // format the date
    String olympicDate = formatter.format(dt);
    assertEquals("2020/08/09 23:59:59", olympicDate);

  }
  @Test
  public void testFormatLocalDateTimeUsingPreDefinedFormatters() {

    //create a LocalDate object
    LocalDateTime dt = LocalDateTime.of(2020, Month.AUGUST, 9, 23, 59, 59);

    // format the date
    String olympicDate = ISO_LOCAL_DATE_TIME.format(dt);
    assertEquals("2020-08-09T23:59:59", olympicDate);

    // or using as a pattern
    olympicDate = dt.format(ISO_LOCAL_DATE_TIME);
    assertEquals("2020-08-09T23:59:59", olympicDate);

  }

  @Test
  public void testFormatZonedDateTime() {

    //create a LocalDate object
    LocalDateTime dt = LocalDateTime.of(2020, Month.AUGUST, 9, 23, 59, 59);
    ZonedDateTime japanZonedDT = ZonedDateTime.of(dt, ZoneId.of("Asia/Tokyo"));
    // format the date
    String pattern = "yyyy/MM/dd HH:mm:ssXXX";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    String olympicDate = formatter.format(japanZonedDT);
    assertEquals("2020/08/09 23:59:59+09:00", olympicDate);

  }
  @Test
  public void testFormatZonedDateTimeUsingPreDefinedFormatters() {

    //create a LocalDate object
    LocalDateTime dt = LocalDateTime.of(2020, Month.AUGUST, 9, 23, 59, 59);
    ZonedDateTime japanZonedDT = ZonedDateTime.of(dt, ZoneId.of("Asia/Tokyo"));
    // format the date
    String olympicDate = ISO_OFFSET_DATE_TIME.format(japanZonedDT);
    assertEquals("2020-08-09T23:59:59+09:00", olympicDate);

    // or using as a pattern
    olympicDate = japanZonedDT.format(ISO_OFFSET_DATE_TIME);
    assertEquals("2020-08-09T23:59:59+09:00", olympicDate);

  }
}
