package com.galvanize;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest
public class ToDoControllerTests {
    @Autowired
    private MockMvc toDo;

//    {
//  "id": 1,
//  "description": "Clean my room",
//  "complete": false
//}
//    @Test
//    public void getTodoList_noParams() throws Exception {
//            toDo.perform(get("/todo"))
//                    .andDo(print())
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.id").value(1))
//                    .andExpect(jsonPath("$.description").value("Clean my Room"))
//                    .andExpect(jsonPath("$.complete").value(false));
//    }

    @Test
    public void getAll_Todos() throws Exception {
        toDo.perform(get("/todo/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(5)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].description").value("Clean my room"))
                .andExpect(jsonPath("$[0].complete").value(false))
                .andExpect(jsonPath("$[4].id").value(5))
                .andExpect(jsonPath("$[4].description").value("Study"))
                .andExpect(jsonPath("$[4].complete").value(true));

    }

    @Test
    public void getAll_notCompleteTodos() throws Exception {
        toDo.perform(get("/todo/incomplete"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(4)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].description").value("Clean my room"))
                .andExpect(jsonPath("$[0].complete").value(false))
                .andExpect(jsonPath("$[3].id").value(4))
                .andExpect(jsonPath("$[3].description").value("Brush Teeth"))
                .andExpect(jsonPath("$[3].complete").value(false));
    }



}
