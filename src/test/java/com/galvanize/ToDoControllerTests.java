package com.galvanize;

import org.junit.jupiter.api.BeforeEach;
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

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest
public class ToDoControllerTests {
    @Autowired
    private MockMvc toDo;

// @BeforeEach
//    void setupService() {
//        orderRepository = mock(OrderRepository.class);
//        paymentRepository = mock(PaymentRepository.class);
//        orderService = new OrderService(orderRepository, paymentRepository);
    List<ToDo> toDoList = new ArrayList<>();
    @BeforeEach
    public void populateNewToDoList() throws Exception {
        toDo.perform(delete("/todo/reset"));
    }

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

    @Test
    public void addNewToDoItem() throws Exception {
        String toDoJson = "{ \"id\": \"6\", \"description\": \"Buy groceries\", \"complete\": \"false\" }";
        toDo.perform(post("/todo/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toDoJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(6))
                .andExpect(jsonPath("$.description").value("Buy groceries"))
                .andExpect(jsonPath("$.complete").value(false));
    }

    @Test
    public void deleteToDoItemFromList() throws Exception {
        toDo.perform(delete("/todo/delete/5"))
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

    @Test
    public void modifyExistingToDoDescription() throws Exception {
        String putJson = "{ \"description\": \"Dentist appointment\" }";
        toDo.perform(put("/todo/modify/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(putJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.description").value("Dentist appointment"))
                .andExpect(jsonPath("$.complete").value(false));
    }

   @Test
    public void getASingleTodo_Success() throws Exception{
        toDo.perform(get("/todo/3"))
                .andDo(print())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.description").value("Walk the Dog"))
                .andExpect(jsonPath("$.complete").value(false));
   }

}
