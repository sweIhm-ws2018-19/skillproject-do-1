package calendarCompanion.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "toDoData")
public class ToDoListItemOnWeekDay {
    private String weekDay;
    private String toDo;

    @DynamoDBHashKey
    public String getWeekDay() { return weekDay; }
    public void setWeekDay(String weekDay) { this.weekDay = weekDay; }

    @DynamoDBAttribute
    public String getToDo() { return toDo; }
    public void setToDo(String toDo) { this.toDo = toDo; }
}
