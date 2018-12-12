package calendarCompanion.handlers;

import calendarCompanion.DynamoDBOperations.DynamoDBAccess;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;

import calendarCompanion.model.PhrasesAndConstants;

import java.util.*;


import static com.amazon.ask.request.Predicates.intentName;

public class AddToDoOnWeekDayHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AddToDoOnWeekDayIntent"));
    }

    private DynamoDBAccess dynamoDBAccess;
    private String responseText;

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();  //holt sich den request
        IntentRequest intentRequest = (IntentRequest) request;      //castet den request in einen intentrequest
        Intent intent = intentRequest.getIntent();      //welcher Intent ist es
        Map<String, Slot> slots = intent.getSlots();    //holt sich die slotListe
        Slot wochenTag = slots.get(PhrasesAndConstants.WOCHENTAG_SLOT);         // holt sich den Slot aus dem intent. zB Montag
        Slot toDo = slots.get(PhrasesAndConstants.TODO_SLOT);
        ResponseBuilder responseBuilder = input.getResponseBuilder();

        if (wochenTag.getValue() != null && wochenTag.getResolutions().toString().contains("ER_SUCCESS_MATCH")) {

            dynamoDBAccess = new DynamoDBAccess(wochenTag.getValue(),toDo.getValue());
            dynamoDBAccess.addToDo();
            responseText = String.format("%s wurde zu deiner ToDoListe am %s hinzugefügt.", toDo.getValue(), wochenTag.getValue());
        } else {
            responseText = "bitte Wochentag nennen, an dem das ToDo hinzugefügt werden soll.";
        }
        responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, responseText)
                .withSpeech(responseText)
                .withShouldEndSession(false);
        return responseBuilder.build();
    }
}

