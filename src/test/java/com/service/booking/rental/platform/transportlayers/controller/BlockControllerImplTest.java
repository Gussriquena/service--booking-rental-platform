package com.service.booking.rental.platform.transportlayers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.booking.rental.platform.entities.Block;
import com.service.booking.rental.platform.interactors.service.BlockService;
import com.service.booking.rental.platform.transportlayers.http.response.BlockResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.service.booking.rental.platform.util.BlockFactory.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BlockControllerImpl.class)
class BlockControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlockService blockService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BLOCK_PATH = "/block";

    @Test
    void when_create_block_then_return_201_created() throws Exception {
        String requestJson = objectMapper.writeValueAsString(mockBlockRequest());
        String responseJson = objectMapper.writeValueAsString(mockBlockResponse());
        Block block = mockBlock();

        when(blockService.create(any(Block.class))).thenReturn(block);

        this.mockMvc.perform(
                        post(BLOCK_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(responseJson));
    }

    @Test
    void when_list_by_property_id_then_return_200_ok() throws Exception {
        List<Block> blocks = List.of(mockBlock());
        List<BlockResponse> blockResponses = List.of(mockBlockResponse());

        when(blockService.listByPropertyId(anyLong())).thenReturn(blocks);

        String responseJson = objectMapper.writeValueAsString(blockResponses);

        this.mockMvc.perform(
                        get(BLOCK_PATH)
                                .param("idProperty", "1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    @Test
    void when_get_by_id_then_return_200_ok() throws Exception {
        Block block = mockBlock();
        String responseJson = objectMapper.writeValueAsString(mockBlockResponse());

        when(blockService.getById(anyLong())).thenReturn(block);

        this.mockMvc.perform(
                        get(BLOCK_PATH + "/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    @Test
    void when_update_by_id_then_return_200_ok() throws Exception {
        String requestJson = objectMapper.writeValueAsString(mockBlockRequest());
        String responseJson = objectMapper.writeValueAsString(mockBlockResponse());

        Block block = mockBlock();

        when(blockService.updateById(any(Block.class))).thenReturn(block);

        this.mockMvc.perform(
                        put(BLOCK_PATH + "/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    @Test
    void when_delete_by_id_then_return_200_ok() throws Exception {
        this.mockMvc.perform(
                        delete(BLOCK_PATH + "/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}