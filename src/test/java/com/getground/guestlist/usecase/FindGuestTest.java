package com.getground.guestlist.usecase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:guest-list;MODE=MySQL",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.liquibase.enabled=false"
})
@RunWith(SpringRunner.class)
@Transactional
public class FindGuestTest {
    @Autowired
    private FindGuest underTest;

    @Test
    @Sql("classpath:test-cases/use-case/find-guest/when-there-are-guests-for-party/insert.sql")
    public void when_ThereAreGuestsForPartyId_Then_ShouldSuccessfullyReturnAllGuestsForParty() {
        //when
        final var actual = underTest.findAllByPartyId(1);
        //then
        assertEquals(1, actual.size());
    }

    @Test
    public void when_ThereAreNotGuestsForPartyId_Then_ShouldSuccessfullyReturnEmptyGuestListForParty() {
        //when
        final var actual = underTest.findAllByPartyId(1);
        //then
        assertEquals(0, actual.size());
    }

    @Test
    @Sql("classpath:test-cases/use-case/find-guest/when-there-are-arrived-guests-for-party/insert.sql")
    public void when_ThereAreArrivedGuestsForPartyId_Then_ShouldSuccessfullyReturnAllGuestsForParty() {
        //when
        final var actual = underTest.findAllArrivedByPartyId(1);
        //then
        assertEquals(1, actual.size());
    }

    @Test
    @Sql("classpath:test-cases/use-case/find-guest/when-there-are-not-arrived-guests-for-party/insert.sql")
    public void when_ThereAreNotArrivedGuestsForPartyId_Then_ShouldSuccessfullyReturnEmptyGuestListForParty() {
        //when
        final var actual = underTest.findAllArrivedByPartyId(1);
        //then
        assertEquals(0, actual.size());
    }

    @Test
    @Sql("classpath:test-cases/use-case/find-guest/when-there-are-empty-seats/insert.sql")
    public void when_ThereAreEmptySeats_Then_ShouldSuccessfullyReturnTheEmptySeatCount() {
        //when
        final var actual = underTest.findEmptySeats(1);
        //then
        assertEquals(2, actual);
    }
}
