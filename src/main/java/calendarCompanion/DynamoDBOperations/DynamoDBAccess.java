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
    private String toDo;
    private String weekDay;
    private String weekDayForDBAccess;

    public DynamoDBAccess(){}

    public DynamoDBAccess(String weekDay,String toDo) {
        this.toDo = toDo;
        this.weekDay = weekDay;
        this.weekDayForDBAccess = Character.toUpperCase(weekDay.charAt(0)) + weekDay.substring(1);
        this.weekDayForDBAccess = weekDayForDBAccess.trim();
    }

    public DynamoDBAccess(String weekDay){
        this.weekDay = weekDay;
        this.weekDayForDBAccess = Character.toUpperCase(weekDay.charAt(0)) + weekDay.substring(1);
        this.weekDayForDBAccess = weekDayForDBAccess.trim();
    }

    public List<String> queryToDos() {
        partitionKey.setWeekDay(weekDayForDBAccess);
        DynamoDBQueryExpression<ToDoListItemOnWeekDay> queryExpression =
                new DynamoDBQueryExpression<ToDoListItemOnWeekDay>().withHashKeyValues(partitionKey);
        List<ToDoListItemOnWeekDay> toDoList = mapper.query(ToDoListItemOnWeekDay.class, queryExpression);
        List<String> toDos = new ArrayList<>();
        for (ToDoListItemOnWeekDay i : toDoList)
            toDos.add(i.getToDo());
        return toDos;
    }

    public void addToDo() {
        partitionKey.setWeekDay(weekDayForDBAccess);
        partitionKey.setToDo(toDo);
        mapper.save(partitionKey);
    }

    public void deleteToDo() {
        String weekDayUpperCase = Character.toUpperCase(weekDay.charAt(0)) + weekDay.substring(1);
        weekDayUpperCase = weekDayUpperCase.trim();
        partitionKey.setWeekDay(weekDayUpperCase);
        partitionKey.setToDo(toDo);
        mapper.delete(partitionKey);
    }
}
