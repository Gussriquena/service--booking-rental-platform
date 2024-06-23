package com.service.booking.rental.platform.transportlayers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.booking.rental.platform.entities.Booking;
import com.service.booking.rental.platform.entities.enums.BookingStatus;
import com.service.booking.rental.platform.interactors.service.BookingService;
import com.service.booking.rental.platform.transportlayers.http.request.BookingUpdateRequest;
import com.service.booking.rental.platform.transportlayers.http.response.BookingResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.service.booking.rental.platform.util.BookingFactory.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookingControllerImpl.class)
class BookingControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BOOKING_PATH = "/booking";

    @Test
    void when_book_property_then_return_201_created() throws Exception {
        String requestJson = objectMapper.writeValueAsString(mockBookingCreateRequest());
        String responseJson = objectMapper.writeValueAsString(mockBookingResponse());
        Booking booking = mockBooking();

        when(bookingService.bookProperty(any(Booking.class))).thenReturn(booking);

        this.mockMvc.perform(
                        post(BOOKING_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(responseJson));
    }

    @Test
    void when_cancel_booking_then_return_200_ok() throws Exception {
        doNothing().when(bookingService).updateStatus(anyLong(), any(BookingStatus.class));

        this.mockMvc.perform(
                        post(BOOKING_PATH + "/{id}/cancel", 1L)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void when_reopen_booking_then_return_200_ok() throws Exception {
        doNothing().when(bookingService).updateStatus(anyLong(), any(BookingStatus.class));

        this.mockMvc.perform(
                        post(BOOKING_PATH + "/{id}/reopen", 1L)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void when_close_booking_then_return_200_ok() throws Exception {
        doNothing().when(bookingService).updateStatus(anyLong(), any(BookingStatus.class));

        this.mockMvc.perform(
                        post(BOOKING_PATH + "/{id}/close", 1L)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void when_get_by_id_then_return_200_ok() throws Exception {
        Booking booking = mockBooking();
        BookingResponse bookingResponse = mockBookingResponse();

        when(bookingService.getById(anyLong())).thenReturn(booking);

        String responseJson = objectMapper.writeValueAsString(bookingResponse);

        this.mockMvc.perform(
                        get(BOOKING_PATH + "/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    @Test
    void when_update_by_id_then_return_200_ok() throws Exception {
        BookingUpdateRequest request = mockBookingUpdateRequest();
        Booking booking = mockBooking();
        BookingResponse bookingResponse = mockBookingResponse();

        when(bookingService.updateById(anyLong(), any(Booking.class))).thenReturn(booking);

        String requestJson = objectMapper.writeValueAsString(request);
        String responseJson = objectMapper.writeValueAsString(bookingResponse);

        this.mockMvc.perform(
                        put(BOOKING_PATH + "/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    @Test
    void when_delete_by_id_then_return_200_ok() throws Exception {
        doNothing().when(bookingService).deleteById(anyLong());

        this.mockMvc.perform(
                        delete(BOOKING_PATH + "/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}