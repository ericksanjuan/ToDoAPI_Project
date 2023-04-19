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

    @GetMapping("/uncomplete")
    public List<ToDo> getUndoneTodos(@RequestParam Boolean complete){
            List<ToDo> noComplete = new ArrayList<>();
        for(int i = 0; i < toDoList.size();i++) {
            if (toDoList.get(i).getComplete() == false) {
                noComplete.add(toDoList.get(i));
            }
        }

        return noComplete;
    }
}
