package com.galvanize;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.http.MediaType;

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
    public void getAll_UnDone_Todos() throws Exception {
        toDo.perform(get("/todo/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"description\":\"Clean my room\",\"complete\":false}," +
                        "{\"id\":2,\"description\":\"Dishes\",\"complete\":false},{\"id\":3,\"description\":\"Walk the Dog\",\"complete\":false}]"));
    }

}
