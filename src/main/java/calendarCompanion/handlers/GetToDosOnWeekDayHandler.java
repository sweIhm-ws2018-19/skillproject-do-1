package calendarCompanion.handlers;

import calendarCompanion.model.PhrasesAndConstants;
import calendarCompanion.model.ToDoListItemOnWeekDay;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import static com.amazon.ask.request.Predicates.intentName;

public class GetToDosOnWeekDayHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("GetToDosOnWeekDayIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();  //holt sich den request
        IntentRequest intentRequest = (IntentRequest) request;      //castet den request in einen intentrequest
        Intent intent = intentRequest.getIntent();      //welcher Intent ist es
        Map<String, Slot> slots = intent.getSlots();    //holt sich die slotListe
        String responseText, toDoItemString;

        // holt sich den Slot aus dem intent. zB Montag
        Slot wochenTag = slots.get(PhrasesAndConstants.WOCHENTAG_SLOT);
        ResponseBuilder responseBuilder = input.getResponseBuilder();

        if (wochenTag.getValue() != null && wochenTag.getResolutions().toString().contains("ER_SUCCESS_MATCH")) {
            AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                    .withRegion(Regions.EU_WEST_1)
                    .build();
            DynamoDBMapper mapper = new DynamoDBMapper(client);

            //Liste für bestimmten Wochentag aus DB holen
            ToDoListItemOnWeekDay partitionKey = new ToDoListItemOnWeekDay();
            //todolistitem für items mit dem key "wochentag" aus der DB holen
            partitionKey.setWeekDay(wochenTag.getValue());
            DynamoDBQueryExpression<ToDoListItemOnWeekDay> queryExpression = new DynamoDBQueryExpression<ToDoListItemOnWeekDay>()
                    .withHashKeyValues(partitionKey);

            //toDoItems in eine Liste schreiben
            List<ToDoListItemOnWeekDay> toDoList = mapper.query(ToDoListItemOnWeekDay.class, queryExpression);
            //nur todos in eine liste schreiben und anschließend zu string zusammenfügen
            List<String> toDos = new ArrayList<>();
            for(ToDoListItemOnWeekDay i:toDoList)
                toDos.add(i.getToDo());
            //build responseText:
            toDoItemString = String.join(", ", toDos);

            responseText =
                    String.format("folgende ToDos stehen am %s auf der Liste : %s", wochenTag.getValue(), toDoItemString);
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, responseText)
                    .withSpeech(responseText)
                    .withShouldEndSession(false);
        }
        else {
            String speechText = "bitte Wochentag nennen, von dem die ToDoListe aufgerufen werden soll.";
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText)
                    .withSpeech(speechText)
                    .withShouldEndSession(false);
        }

        return responseBuilder.build();
    }
}
