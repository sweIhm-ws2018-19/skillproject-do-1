package calendarCompanion.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;
import calendarCompanion.model.ToDoList;

import calendarCompanion.model.PhrasesAndConstants;

import java.util.*;


import static com.amazon.ask.request.Predicates.intentName;

public class AddToDoIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AddToDoIntent"));
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

            //ToDoListe für einen bestimmten Tag aus Datenbank holen
            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
            ToDoList toDoListOnWeekDay  = (ToDoList)persistentAttributes.get(wochenTag.getValue());
            List<String> newToDoList = toDoListOnWeekDay.getToDos();
            //todoo an Liste anhängen
            newToDoList.add(toDo.getValue());
            //Model für datenbank bauen
            toDoListOnWeekDay.setToDos(newToDoList);

            // Store the user's favorite color in the Session and store in DB then create response.
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap(wochenTag.getValue(), toDoListOnWeekDay));

            //store persistent
            persistentAttributes.put(wochenTag.getValue(), toDoListOnWeekDay);
            attributesManager.setPersistentAttributes(persistentAttributes);
            attributesManager.savePersistentAttributes();

            String speechText =
                    String.format("%s wurde zu deiner ToDoListe am %s hinzugefügt.", toDo.getValue(), wochenTag.getValue());
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText)
                    .withSpeech(speechText)
                    .withShouldEndSession(false);

        } else {
            String speechText = "bitte Wochentag nennen, an dem das ToDo hinzugefügt werden soll.";
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText)
                    .withSpeech(speechText)
                    .withShouldEndSession(false);
        }

        return responseBuilder.build();
    }

}