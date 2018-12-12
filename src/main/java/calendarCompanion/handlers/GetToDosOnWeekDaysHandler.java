package calendarCompanion.handlers;

import calendarCompanion.DynamoDBOperations.DynamoDBAccess;
import calendarCompanion.model.PhrasesAndConstants;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;

import java.util.List;
import java.util.Map;
import java.util.Optional;


import static com.amazon.ask.request.Predicates.intentName;

public class GetToDosOnWeekDaysHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("GetToDosOnWeekDayIntent"));
    }

    private DynamoDBAccess dynamoDBAccess;

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
            List<String> toDos;
            dynamoDBAccess = new DynamoDBAccess(wochenTag.getValue());
            toDos = dynamoDBAccess.queryToDos();

            if (toDos.isEmpty())
                responseText = String.format("am %s hast du rein gar nichts vor!", wochenTag.getValue());
            else {
                toDoItemString = String.join(", ", toDos);
                responseText =
                        String.format("folgende ToDos stehen am %s auf der Liste : %s", wochenTag.getValue(), toDoItemString);
            }
        } else {
            responseText = "Bitte einen Wochentag nennen, von dem die ToDoListe aufgerufen werden soll. z.B: was steht am Montag an?";
        }
        responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, responseText)
                .withSpeech(responseText)
                .withShouldEndSession(false);
        return responseBuilder.build();
    }
}
