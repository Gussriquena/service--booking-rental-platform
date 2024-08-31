package com.service.booking.rental.platform.transportlayers.controller.swagger;

import com.service.booking.rental.platform.entities.GeneralError;
import com.service.booking.rental.platform.transportlayers.http.request.BookingCreateRequest;
import com.service.booking.rental.platform.transportlayers.http.request.BookingUpdateRequest;
import com.service.booking.rental.platform.transportlayers.http.response.BookingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name="Booking", description = "Manage Bookings on properties")
public interface BookingController {

    @Operation(
            summary = "Book property",
            description = "Book period for property",
            tags = {"Booking"},
            responses = {
                    @ApiResponse(
                            description = "Created", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = BookingResponse.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error", responseCode = "500",
                            content = @Content(schema = @Schema(implementation = GeneralError.class))
                    )
            }
    )
    ResponseEntity<BookingResponse> bookProperty(BookingCreateRequest request);

    @Operation(
            summary = "Cancel Booking",
            description = "Cancel booking by id",
            tags = {"Booking"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookingResponse.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error", responseCode = "500",
                            content = @Content(schema = @Schema(implementation = GeneralError.class))
                    )
            }
    )
    ResponseEntity<BookingResponse> cancelBooking(Long id);

    @Operation(
            summary = "Reopen Booking",
            description = "Reopen a closed booking",
            tags = {"Booking"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookingResponse.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error", responseCode = "500",
                            content = @Content(schema = @Schema(implementation = GeneralError.class))
                    )
            }
    )
    ResponseEntity<BookingResponse> reopenBooking(Long id);

    @Operation(
            summary = "Close Booking",
            description = "Close an open booking",
            tags = {"Booking"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookingResponse.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error", responseCode = "500",
                            content = @Content(schema = @Schema(implementation = GeneralError.class))
                    )
            }
    )
    ResponseEntity<BookingResponse> closeBooking(Long id);

    @Operation(
            summary = "Get Booking",
            description = "Get booking by ID",
            tags = {"Booking"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookingResponse.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error", responseCode = "500",
                            content = @Content(schema = @Schema(implementation = GeneralError.class))
                    )
            }
    )
    ResponseEntity<BookingResponse> getById(Long id);

    @Operation(
            summary = "Update Booking",
            description = "Update booking data by ID",
            tags = {"Booking"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookingResponse.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error", responseCode = "500",
                            content = @Content(schema = @Schema(implementation = GeneralError.class))
                    )
            }
    )
    ResponseEntity<BookingResponse> updateById(Long id, BookingUpdateRequest request);

    @Operation(
            summary = "Delete Booking",
            description = "Delete booking by ID",
            tags = {"Booking"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(
                            description = "Internal server error", responseCode = "500",
                            content = @Content(schema = @Schema(implementation = GeneralError.class))
                    )
            }
    )
    ResponseEntity<Void> deleteById(Long id);

}
