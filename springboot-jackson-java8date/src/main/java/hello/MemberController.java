package hello;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/member")
    public Member getDefaultMember() {
        Member member = new Member();
        member.setId(1L);
        member.setFirstName("Tom");
        member.setLastName("Sawyer");
        member.setBirthDate(LocalDate.of(2001, Month.APRIL, 20));
        member.setRegisteredDateTime(LocalDateTime.of(
                2017, Month.JUNE, 29, 20, 40, 59));

        return member;
    }
}
