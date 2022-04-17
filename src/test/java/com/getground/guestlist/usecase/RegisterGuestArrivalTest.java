package com.getground.guestlist.usecase;

import com.getground.guestlist.usecase.model.RegisterGuestArrival;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.getground.guestlist.util.JSONUtil.fileToBean;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:guest-list;MODE=MySQL",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.liquibase.enabled=false"
})
@RunWith(SpringRunner.class)
@Transactional
public class RegisterGuestArrivalTest {
    private static final String TEST_CASES_BASE_PATH = "/test-cases/use-case/register-guest-arrival/%s";

    @Autowired
    private com.getground.guestlist.usecase.RegisterGuestArrival underTest;

    @Test
    @Sql("classpath:test-cases/use-case/register-guest-arrival/when-table-has-enough-seats/insert.sql")
    public void when_NumberOfAccompanyingMatchesNumberOfTableSeats_Then_ShouldSuccessfullyRegisterGuestArrival() {
        //given
        final var guestArrivalFullPath = format(TEST_CASES_BASE_PATH, "when-table-has-enough-seats/input.json");
        final var guestArrival = fileToBean(guestArrivalFullPath, RegisterGuestArrival.class);
        //when
        try {
            underTest.register(guestArrival);
        } catch (final Exception exception) {
            //then
            fail("No error should happen while performing arrival registration of valid guest data.");
        }
    }

    @Test
    @Sql("classpath:test-cases/use-case/register-guest-arrival/when-table-has-not-enough-seats/insert.sql")
    public void when_NumberOfAccompanyingDoesNotMatchNumberOfTableSeats_Then_ShouldThrowException() {
        //given
        final var guestArrivalFullPath = format(TEST_CASES_BASE_PATH, "when-table-has-not-enough-seats/input.json");
        final var guestArrival = fileToBean(guestArrivalFullPath, RegisterGuestArrival.class);
        //when
        final var actual = assertThrows(IllegalArgumentException.class, () -> underTest.register(guestArrival));
        //then
        final var expected = "There are not enough seats for the number of accompanying guests.The table total seats are 5 and the provided number of people is 10.";
        assertEquals(expected, actual.getMessage());
    }

    @Test
    @Sql("classpath:test-cases/use-case/register-guest-arrival/when-there-is-no-table-associated-to-guest/insert.sql")
    public void when_ThereIsNotAnyTableAssociatedToTheGuest_Then_ShouldThrowException() {
        //given
        final var guestArrivalFullPath = format(TEST_CASES_BASE_PATH, "when-there-is-no-table-associated-to-guest/input.json");
        final var guestArrival = fileToBean(guestArrivalFullPath, RegisterGuestArrival.class);
        //when
        final var actual = assertThrows(IllegalArgumentException.class, () -> underTest.register(guestArrival));
        //then
        final var expected = "There is not any table registered for the guest with name Wanda Hum.";
        assertEquals(expected, actual.getMessage());
    }
}
