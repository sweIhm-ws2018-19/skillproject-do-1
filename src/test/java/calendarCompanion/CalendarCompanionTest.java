package calendarCompanion;

import org.junit.Assert;
import org.junit.Test;

import calendarCompanion.model.ToDoList;

import java.util.ArrayList;
import java.util.List;

public class CalendarCompanionTest {

    List<String> list = new ArrayList<String>();

    @Test
    public void setToDoList() {

        ToDoList toDoList = new ToDoList(list);
        list.add("testBefore");

        List<String> listAfter = new ArrayList<String>();
        listAfter.add("testAfter");

        toDoList.setToDos(listAfter);

        Assert.assertEquals("[testAfter]", toDoList.getToDos().toString());
        Assert.assertFalse(toDoList.getToDos().contains("testBefore"));
    }
}
