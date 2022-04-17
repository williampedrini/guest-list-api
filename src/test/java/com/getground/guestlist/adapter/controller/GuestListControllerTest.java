package com.getground.guestlist.adapter.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.getground.guestlist.adapter.controller.model.AddGuestToListRequest;
import com.getground.guestlist.adapter.controller.model.AddGuestToListResponse;
import com.getground.guestlist.adapter.controller.model.ErrorResponse;
import com.getground.guestlist.adapter.controller.model.GetGuestListResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static com.getground.guestlist.util.JSONUtil.beanToString;
import static com.getground.guestlist.util.JSONUtil.byteArrayToBean;
import static com.getground.guestlist.util.JSONUtil.fileToBean;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:guest-list;MODE=MySQL",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.liquibase.enabled=false"
})
@Transactional
public class GuestListControllerTest {
    private static final String TEST_CASES_BASE_PATH = "/test-cases/adapter/guest-list-controller/%s";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql("classpath:test-cases/adapter/guest-list-controller/when-table-is-available-and-has-enough-seats/insert.sql")
    public void when_TableIsAvailable_And_NumberOfAccompanyingMatchesNumberOfTableSeats_Then_ShouldSuccessfullyAssociateTableWithGuest() throws Exception {
        //given
        final var requestBuilder = request(POST, "/parties/1/guest_list/Sharon Parkinson")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON);

        final var requestFullPath = format(TEST_CASES_BASE_PATH, "when-table-is-available-and-has-enough-seats/input.json");
        final var request = beanToString(requestFullPath, AddGuestToListRequest.class);
        //when
        final var response = mockMvc.perform(requestBuilder.content(request))
                .andExpect(status().isOk())
                .andReturn();
        //then
        final var actualRawResponse = response.getResponse().getContentAsByteArray();
        final var actualResponse = byteArrayToBean(actualRawResponse, AddGuestToListResponse.class);

        final var expectedResponseFullPath = format(TEST_CASES_BASE_PATH, "when-table-is-available-and-has-enough-seats/expected.json");
        final var expectedResponse = fileToBean(expectedResponseFullPath, AddGuestToListResponse.class);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void when_ThereIsNotAnyProvidedAccompanyingGuestsNumber_Then_ShouldThrowFieldException() throws Exception {
        //given
        final var requestBuilder = request(POST, "/parties/1/guest_list/Sharon Parkinson")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON);

        final var requestFullPath = format(TEST_CASES_BASE_PATH, "when-there-is-not-any-provided-accompanying-guests-number/input.json");
        final var request = beanToString(requestFullPath, AddGuestToListRequest.class);
        //when
        final var response = mockMvc.perform(requestBuilder.content(request))
                .andExpect(status().isBadRequest())
                .andReturn();

        final var actualRawResponse = response.getResponse().getContentAsByteArray();
        final var actualResponse = byteArrayToBean(actualRawResponse, new TypeReference<Collection<ErrorResponse>>() {
        });

        final var expectedResponseFullPath = format(TEST_CASES_BASE_PATH, "when-there-is-not-any-provided-accompanying-guests-number/expected.json");
        final var expectedResponse = fileToBean(expectedResponseFullPath, new TypeReference<Collection<ErrorResponse>>() {
        });
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void when_ProvidedAccompanyingGuestsNumberIsNegative_Then_ShouldThrowFieldException() throws Exception {
        //given
        final var requestBuilder = request(POST, "/parties/1/guest_list/Sharon Parkinson")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON);

        final var requestFullPath = format(TEST_CASES_BASE_PATH, "when-provided-accompanying-guests-number-is-negative/input.json");
        final var request = beanToString(requestFullPath, AddGuestToListRequest.class);
        //when
        final var response = mockMvc.perform(requestBuilder.content(request))
                .andExpect(status().isBadRequest())
                .andReturn();

        final var actualRawResponse = response.getResponse().getContentAsByteArray();
        final var actualResponse = byteArrayToBean(actualRawResponse, new TypeReference<Collection<ErrorResponse>>() {
        });

        final var expectedResponseFullPath = format(TEST_CASES_BASE_PATH, "when-provided-accompanying-guests-number-is-negative/expected.json");
        final var expectedResponse = fileToBean(expectedResponseFullPath, new TypeReference<Collection<ErrorResponse>>() {
        });
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void when_ThereIsNotAnyProvidedTableNumber_Then_ShouldThrowFieldException() throws Exception {
        //given
        final var requestBuilder = request(POST, "/parties/1/guest_list/Sharon Parkinson")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON);

        final var requestFullPath = format(TEST_CASES_BASE_PATH, "when-there-is-not-any-provided-table-number/input.json");
        final var request = beanToString(requestFullPath, AddGuestToListRequest.class);
        //when
        final var response = mockMvc.perform(requestBuilder.content(request))
                .andExpect(status().isBadRequest())
                .andReturn();

        final var actualRawResponse = response.getResponse().getContentAsByteArray();
        final var actualResponse = byteArrayToBean(actualRawResponse, new TypeReference<Collection<ErrorResponse>>() {
        });

        final var expectedResponseFullPath = format(TEST_CASES_BASE_PATH, "when-there-is-not-any-provided-table-number/expected.json");
        final var expectedResponse = fileToBean(expectedResponseFullPath, new TypeReference<Collection<ErrorResponse>>() {
        });
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void when_ProvidedTableNumberIsNegative_Then_ShouldThrowFieldException() throws Exception {
        //given
        final var requestBuilder = request(POST, "/parties/1/guest_list/Sharon Parkinson")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON);

        final var requestFullPath = format(TEST_CASES_BASE_PATH, "when-provided-table-number-is-negative/input.json");
        final var request = beanToString(requestFullPath, AddGuestToListRequest.class);
        //when
        final var response = mockMvc.perform(requestBuilder.content(request))
                .andExpect(status().isBadRequest())
                .andReturn();

        final var actualRawResponse = response.getResponse().getContentAsByteArray();
        final var actualResponse = byteArrayToBean(actualRawResponse, new TypeReference<Collection<ErrorResponse>>() {
        });

        final var expectedResponseFullPath = format(TEST_CASES_BASE_PATH, "when-provided-table-number-is-negative/expected.json");
        final var expectedResponse = fileToBean(expectedResponseFullPath, new TypeReference<Collection<ErrorResponse>>() {
        });
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @Sql("classpath:test-cases/adapter/guest-list-controller/when-there-are-guests-for-party/insert.sql")
    public void when_ThereAreGuestsForPartyId_Then_ShouldSuccessfullyReturnAllGuestsForParty() throws Exception {
        //given
        final var requestBuilder = request(GET, "/parties/1/guest_list")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        //when
        final var response = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        //then
        final var actualRawResponse = response.getResponse().getContentAsByteArray();
        final var actualResponse = byteArrayToBean(actualRawResponse, GetGuestListResponse.class);

        final var expectedResponseFullPath = format(TEST_CASES_BASE_PATH, "when-there-are-guests-for-party/expected.json");
        final var expectedResponse = fileToBean(expectedResponseFullPath, GetGuestListResponse.class);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void when_ThereAreNotGuestsForPartyId_Then_ShouldSuccessfullyReturnEmptyGuestListForParty() throws Exception {
        //given
        final var requestBuilder = request(GET, "/parties/1/guest_list")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        //when
        final var response = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        //then
        final var actualRawResponse = response.getResponse().getContentAsByteArray();
        final var actualResponse = byteArrayToBean(actualRawResponse, GetGuestListResponse.class);

        final var expectedResponseFullPath = format(TEST_CASES_BASE_PATH, "when-there-are-not-guests-for-party/expected.json");
        final var expectedResponse = fileToBean(expectedResponseFullPath, GetGuestListResponse.class);
        assertEquals(expectedResponse, actualResponse);
    }
}