package com.getground.guestlist.usecase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
public class RegisterGuestLeaveTest {
    @Autowired
    private RegisterGuestLeave underTest;

    @Test
    @Sql("classpath:test-cases/use-case/register-guest-leave/when-there-is-table-associated-to-guest/insert.sql")
    public void when_ThereIsTableAssociatedToTheGuest_Then_ShouldRegisterSuccessfullyTheGuestLeave() {
        //given
        final var guestName = "Wanda Hum";
        final var partyId = 1;
        //when
        try {
            underTest.register(partyId, guestName);
        } catch (final Exception exception) {
            //then
            fail("No error should happen while performing leave registration of valid guest data.");
        }
    }

    @Test
    @Sql("classpath:test-cases/use-case/register-guest-leave/when-there-is-no-table-associated-to-guest/insert.sql")
    public void when_ThereIsNotAnyTableAssociatedToTheGuest_Then_ShouldThrowException() {
        //given
        final var guestName = "Wanda Hum";
        final var partyId = 1;
        //when
        final var actual = assertThrows(IllegalArgumentException.class, () -> underTest.register(partyId, guestName));
        //then
        final var expected = "There is not any table registered for the guest with name Wanda Hum.";
        assertEquals(expected, actual.getMessage());
    }
}