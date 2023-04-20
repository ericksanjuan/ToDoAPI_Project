package com.galvanize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/todo")
public class ToDoController {
        List<ToDo> toDoList = new ArrayList<>();

        public ToDoController() {
//            toDoList.add(new ToDo(1,"Clean my room"));
//            toDoList.add(new ToDo(2,"Dishes"));
//            toDoList.add(new ToDo(3,"Walk the Dog"));
//            toDoList.add(new ToDo(4,"Brush Teeth"));
//            ToDo todo5 =  new ToDo(5,"Study");
//            todo5.setComplete(true);
//            toDoList.add(todo5);
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

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ToDo> addToDo(@RequestBody ToDo newToDo) {
            toDoList.add(newToDo);
            return ResponseEntity.status(HttpStatus.CREATED).body(newToDo);
    }

    @DeleteMapping("/delete/{id}")
    public List<ToDo> removeToDoById(@PathVariable int id) {
            toDoList.remove(id - 1);
            return toDoList;
    }

    @DeleteMapping("/reset")
    public List<ToDo> resetList() {
                //clear the list
        toDoList.clear();
        toDoList.add(new ToDo(1,"Clean my room"));
        toDoList.add(new ToDo(2,"Dishes"));
        toDoList.add(new ToDo(3,"Walk the Dog"));
        toDoList.add(new ToDo(4,"Brush Teeth"));
        ToDo todo5 =  new ToDo(5,"Study");
        todo5.setComplete(true);
        toDoList.add(todo5);
        return toDoList;
    }
    @PutMapping("/modify/{id}")
    public ToDo putToDo(@PathVariable Integer id, @RequestBody ToDo updatedToDo) {
            ToDo toDo = null;
            for(ToDo item : toDoList) {
                if (item.getId() == id) {
                    item.setDescription(updatedToDo.getDescription());
                    toDo = item;
                }
            }
            return toDo;
    }

    @GetMapping("/{id}")
    public ToDo  getAllTodos(@PathVariable Integer id){
        ToDo toDo = null;
        for(ToDo item : toDoList) {
            if (item.getId() == id) {
                toDo = item;
            }
        }
        return toDo;

    }

    @PatchMapping("/complete/{id}")
    public ToDo markToDoComplete(@PathVariable Integer id) {
            ToDo toDo = null;
        for(ToDo item : toDoList) {
            if (item.getId() == id) {
                item.setComplete(true);
                toDo = item;
            }
        }
        return toDo;
    }

}
