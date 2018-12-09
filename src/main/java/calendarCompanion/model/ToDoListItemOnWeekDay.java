package calendarCompanion.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "toDoData")
public class ToDoListItemOnWeekDay {
    private String weekDay;
    private String toDo;

    @DynamoDBHashKey(attributeName = "weekDay")
    public String getWeekDay() { return weekDay; }
    public void setWeekDay(String weekDay) { this.weekDay = weekDay; }

    @DynamoDBRangeKey(attributeName = "toDo")
    public String getToDo() { return toDo; }
    public void setToDo(String toDo) { this.toDo = toDo; }
}
