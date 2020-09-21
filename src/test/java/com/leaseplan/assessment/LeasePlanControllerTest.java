package com.leaseplan.assessment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leaseplan.assessment.pojo.order.Order;
import com.leaseplan.assessment.pojo.pets.Pet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(LeasePlanController.class)
// FIXME in general hard coded urls used
public class LeasePlanControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void create() throws Exception {

        mvc.perform(post("/api/pet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new Pet("5574", "fino", "available"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']").value("5574"));
    }

    @Test
    public void reachNotFound() throws Exception {
        mvc.perform(get("/api/pet/9476"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void reachFound() throws Exception {
        create();

        mvc.perform(get("/api/pet/5574"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']").value("5574"));
    }

    @Test
    public void findFoundOne() throws Exception {
        create();

        mvc.perform(get("/api/pet/findByStatus?status=available"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]['id']").value("5574"));
    }

    @Test
    public void deleteFound() throws Exception {
        create();

        mvc.perform(delete("/api/pet/5574"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNotFound() throws Exception {
        mvc.perform(delete("/api/pet/9476"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void storeOrder()  throws Exception {

        mvc.perform(post("/api/pet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new Order("9976", 1, "placed", true))))
                .andExpect(status().isOk());
    }
}
