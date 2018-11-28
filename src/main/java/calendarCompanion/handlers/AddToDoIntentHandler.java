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
        return input.matches(intentName("AddToDoListIntent"));
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
            List<String> list = new ArrayList<>();
            list.add(toDo.getValue());
            ToDoList toDoList = new ToDoList(list);
            // Store the user's favorite color in the Session and store in DB then create response.
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap(wochenTag.getValue(), toDoList.getToDos()));

            //Was noch fehlt: Liste für spezifischen WOchentag holen Liste zwischenspeichern und to do anhängen, dann erst wieder abspeichern


            //store persistent
            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
            persistentAttributes.put(wochenTag.getValue(), toDoList.getToDos());
            attributesManager.setPersistentAttributes(persistentAttributes);
            attributesManager.savePersistentAttributes();

//            String speechText =
//                    String.format("%s %s. %s", PhrasesAndConstants.LIEBLINGSFARBE_IS, lieblingsfarbe.getFarbe(), PhrasesAndConstants.WHAT_IS_LIEBLINGSFARBE);
//            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText)
//                    .withSpeech(speechText)
//                    .withShouldEndSession(false);

        } else {
            // Render an error since we don't know what the users favorite color is.
//            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.SAY_LIEBLINGAFARBE_REPROMPT)
//                    .withSpeech(PhrasesAndConstants.SAY_LIEBLINGAFARBE_REPROMPT)
//                    .withReprompt(PhrasesAndConstants.REPROMPT_LIEBINGSFARBE)
//                    .withShouldEndSession(false);
        }

        return responseBuilder.build();
    }

}