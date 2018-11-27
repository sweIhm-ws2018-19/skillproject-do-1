package calendarCompanion.model;

import java.util.List;

public class ToDoList {
    private List<String> ToDos;

    public ToDoList(List<String> toDos){
        this.ToDos = toDos;
    }

    public void setToDos(List<String> toDos) {
        ToDos = toDos;
    }

    public List<String> getToDos() {
        return ToDos;
    }
}
