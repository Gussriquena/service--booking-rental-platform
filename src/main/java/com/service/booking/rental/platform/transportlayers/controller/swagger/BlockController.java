package com.service.booking.rental.platform.transportlayers.controller.swagger;

import com.service.booking.rental.platform.entities.GeneralError;
import com.service.booking.rental.platform.transportlayers.http.request.BlockRequest;
import com.service.booking.rental.platform.transportlayers.http.response.BlockResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name="Block", description = "Managing blocks on properties")
public interface BlockController {

    @Operation(
            summary = "Create new block",
            description = "Create new block for property",
            tags = {"Block"},
            responses = {
                    @ApiResponse(
                            description = "Created", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = BlockResponse.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error", responseCode = "500",
                            content = @Content(schema = @Schema(implementation = GeneralError.class))
                    )
            }
    )
    ResponseEntity<BlockResponse> create(BlockRequest request);

    @Operation(
            summary = "List Blocks by property ID",
            description = "List Blocks by property ID",
            tags = {"Block"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = BlockResponse.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Internal server error", responseCode = "500",
                            content = @Content(schema = @Schema(implementation = GeneralError.class))
                    )
            }
    )
    ResponseEntity<List<BlockResponse>> listByPropertyId(Long idProperty);

    @Operation(
            summary = "Get Block",
            description = "Retrieve block by ID",
            tags = {"Block"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BlockResponse.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error", responseCode = "500",
                            content = @Content(schema = @Schema(implementation = GeneralError.class))
                    )
            }
    )
    ResponseEntity<BlockResponse> getById(Long id);

    @Operation(
            summary = "Update block",
            description = "Update block by ID",
            tags = {"Block"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BlockResponse.class))
                    ),
                    @ApiResponse(
                            description = "Internal server error", responseCode = "500",
                            content = @Content(schema = @Schema(implementation = GeneralError.class))
                    )
            }
    )
    ResponseEntity<BlockResponse> updateById(Long id, BlockRequest request);

    @Operation(
            summary = "Delete block",
            description = "Delete block by ID",
            tags = {"Block"},
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
