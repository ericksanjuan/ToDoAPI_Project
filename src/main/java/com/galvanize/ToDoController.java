package com.galvanize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/todo")
public class ToDoController {
        List<ToDo> toDoList = new ArrayList<>();
        public ToDoController() {
            toDoList.add(new ToDo(1,"Clean my room"));
            toDoList.add(new ToDo(2,"Dishes"));
            toDoList.add(new ToDo(3,"Walk the Dog"));
            toDoList.add(new ToDo(4,"Brush Teeth"));
            ToDo todo5 =  new ToDo(5,"Study");
            todo5.setComplete(true);
            toDoList.add(todo5);
        }


    @GetMapping("/list")
    public List<ToDo> getAllTodos(){

            return toDoList;
        }

    @GetMapping("/incomplete")
    public List<ToDo> getUndoneTodos(){
            List<ToDo> noComplete = new ArrayList<>();
        for (ToDo toDo : toDoList) {
            if (!toDo.getComplete()) {
                noComplete.add(toDo);
            }
        }

        return noComplete;
    }
}
