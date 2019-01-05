package calendarCompanion.DynamoDBOperations;

import calendarCompanion.model.ToDoListItemOnWeekDay;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.ArrayList;
import java.util.List;

public class DynamoDBAccess {
    private AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withRegion(Regions.EU_WEST_1)
            .build();
    private DynamoDBMapper mapper = new DynamoDBMapper(client);
    private ToDoListItemOnWeekDay partitionKey = new ToDoListItemOnWeekDay();

    public DynamoDBAccess(){}

    private String formatDayForDB(String day){
        day = Character.toUpperCase(day.charAt(0)) + day.substring(1);
        return day.trim();
    }

    public List<String> queryToDos(String weekDay) {
        partitionKey.setWeekDay(formatDayForDB(weekDay));
        DynamoDBQueryExpression<ToDoListItemOnWeekDay> queryExpression =
                new DynamoDBQueryExpression<ToDoListItemOnWeekDay>().withHashKeyValues(partitionKey);
        List<ToDoListItemOnWeekDay> toDoList = mapper.query(ToDoListItemOnWeekDay.class, queryExpression);
        List<String> toDos = new ArrayList<>();
        for (ToDoListItemOnWeekDay i : toDoList)
            toDos.add(i.getToDo());
        return toDos;
    }

    public void addToDo(String weekDay,String toDo) {
        partitionKey.setWeekDay(formatDayForDB(weekDay));
        partitionKey.setToDo(toDo);
        mapper.save(partitionKey);
    }

    public void deleteToDo(String weekDay, String toDo) {
        partitionKey.setWeekDay(formatDayForDB(weekDay));
        partitionKey.setToDo(toDo);
        mapper.delete(partitionKey);
    }

    public void deleteAllToDos(String weekDay){
        List<String> ToDos = this.queryToDos(weekDay);
        for (String i : ToDos) {
            partitionKey.setToDo(i);
            mapper.delete(partitionKey);
        }
    }
}
