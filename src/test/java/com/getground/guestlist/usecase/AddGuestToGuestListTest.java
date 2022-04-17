package com.getground.guestlist.usecase;

import com.getground.guestlist.usecase.model.Guest;
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
public class AddGuestToGuestListTest {
    private static final String TEST_CASES_BASE_PATH = "/test-cases/use-case/add-guest-to-guest-list/%s";

    @Autowired
    private AddGuestToGuestList underTest;

    @Test
    @Sql("classpath:test-cases/use-case/add-guest-to-guest-list/when-table-is-available-and-has-enough-seats/insert.sql")
    public void when_TableIsAvailable_And_NumberOfAccompanyingMatchesNumberOfTableSeats_Then_ShouldSuccessfullyAssociateTableWithGuest() {
        //given
        final var guestFullPath = format(TEST_CASES_BASE_PATH, "when-table-is-available-and-has-enough-seats/input.json");
        final var guest = fileToBean(guestFullPath, Guest.class);
        //when
        try {
            underTest.add(guest);
        } catch (final Exception exception) {
            //then
            fail("No error should happen while performing addition of guest to list with valid guest data.");
        }
    }

    @Test
    @Sql("classpath:test-cases/use-case/add-guest-to-guest-list/when-table-is-not-available-and-has-enough-seats/insert.sql")
    public void when_TableIsNotAvailable_And_NumberOfAccompanyingMatchesNumberOfTableSeats_Then_ShouldThrowException() {
        //given
        final var guestFullPath = format(TEST_CASES_BASE_PATH, "when-table-is-not-available-and-has-enough-seats/input.json");
        final var guest = fileToBean(guestFullPath, Guest.class);
        //when
        final var actual = assertThrows(IllegalArgumentException.class, () -> underTest.add(guest));
        //then
        final var expected = "The guest with name Jake Fuller is already associated to the table number 1.";
        assertEquals(expected, actual.getMessage());
    }

    @Test
    @Sql("classpath:test-cases/use-case/add-guest-to-guest-list/when-table-is-available-and-has-not-enough-seats/insert.sql")
    public void when_TableIsAvailable_And_NumberOfAccompanyingDoesNotMatchNumberOfTableSeats_Then_ShouldThrowException() {
        //given
        final var guestFullPath = format(TEST_CASES_BASE_PATH, "when-table-is-available-and-has-not-enough-seats/input.json");
        final var guest = fileToBean(guestFullPath, Guest.class);
        //when
        final var actual = assertThrows(IllegalArgumentException.class, () -> underTest.add(guest));
        //then
        final var expected = "There are not enough seats for the number of accompanying guests.The table total seats are 9 and the provided number of people is 11.";
        assertEquals(expected, actual.getMessage());
    }

    @Test
    public void when_TableDoesNotExist_Then_ShouldThrowException() {
        //given
        final var guestFullPath = format(TEST_CASES_BASE_PATH, "when-table-does-not-exist/input.json");
        final var guest = fileToBean(guestFullPath, Guest.class);
        //when
        final var actual = assertThrows(IllegalArgumentException.class, () -> underTest.add(guest));
        //then
        final var expected = "There is not any existing table with number 1.";
        assertEquals(expected, actual.getMessage());
    }
}
