package calendarCompanion.handlers;

import calendarCompanion.model.ToDoListItemOnWeekDay;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;

import calendarCompanion.model.PhrasesAndConstants;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.*;


import static com.amazon.ask.request.Predicates.intentName;

public class AddToDoOnWeekDayHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AddToDoOnWeekDayIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();  //holt sich den request
        IntentRequest intentRequest = (IntentRequest) request;      //castet den request in einen intentrequest
        Intent intent = intentRequest.getIntent();      //welcher Intent ist es
        Map<String, Slot> slots = intent.getSlots();    //holt sich die slotListe
 
        // holt sich den Slot aus dem intent. zB Montag
        Slot wochenTag = slots.get(PhrasesAndConstants.WOCHENTAG_SLOT);
        Slot toDo = slots.get(PhrasesAndConstants.TODO_SLOT);

        ResponseBuilder responseBuilder = input.getResponseBuilder();


        if (wochenTag.getValue() != null && wochenTag.getResolutions().toString().contains("ER_SUCCESS_MATCH")) {
            AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
            DynamoDBMapper mapper = new DynamoDBMapper(client);

            //TodoListItemOnWeekDay in Datenbank speichern
            ToDoListItemOnWeekDay toDoListItemOnWeekDay = new ToDoListItemOnWeekDay();
            toDoListItemOnWeekDay.setWeekDay(wochenTag.getValue());
            toDoListItemOnWeekDay.setToDo(toDo.getValue());
            mapper.save(toDoListItemOnWeekDay);

            String speechText =
                    String.format("%s wurde zu deiner ToDoListe am %s hinzugef�gt.", toDo.getValue(), wochenTag.getValue());
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText)
                    .withSpeech(speechText)
                    .withShouldEndSession(false);


        } else {
            String speechText = "bitte Wochentag nennen, an dem das ToDo hinzugef�gt werden soll.";
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText)
                    .withSpeech(speechText)
                    .withShouldEndSession(false);
        }

        return responseBuilder.build();
    }
}

