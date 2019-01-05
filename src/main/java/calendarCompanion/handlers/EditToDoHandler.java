package calendarCompanion.handlers;

import calendarCompanion.DynamoDBOperations.DynamoDBAccess;
import calendarCompanion.model.PhrasesAndConstants;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class EditToDoHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("EditToDoIntent"));
    }

    private DynamoDBAccess dynamoDBAccess = new DynamoDBAccess();
    private String responseText;

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();  //holt sich den request
        IntentRequest intentRequest = (IntentRequest) request;      //castet den request in einen intentrequest
        Intent intent = intentRequest.getIntent();      //welcher Intent ist es
        Map<String, Slot> slots = intent.getSlots();    //holt sich die slotListe
        Slot weekDaySource = slots.get(PhrasesAndConstants.WOCHENTAG_SLOT);
        Slot weekDayTarget = slots.get(PhrasesAndConstants.WOCHENTAG_ZIEL_SLOT);
        Slot toDo = slots.get(PhrasesAndConstants.TODO_SLOT);
        ResponseBuilder responseBuilder = input.getResponseBuilder();

        if ((weekDaySource.getValue() != null && weekDaySource.getResolutions().toString().contains("ER_SUCCESS_MATCH")) &&
                (weekDayTarget.getValue() != null && weekDayTarget.getResolutions().toString().contains("ER_SUCCESS_MATCH"))) {
            dynamoDBAccess.moveToDo(weekDaySource.getValue(), weekDayTarget.getValue(), toDo.getValue());
            responseText = String.format("%s wurde von %s auf %s verschoben. Glückwunsch du aufschieber!", toDo.getValue(), weekDaySource.getValue(), weekDayTarget.getValue());
        } else {
            responseText = "bitte sage etwas wie: verschiebe Sport von Dienstag auf Mittwoch.";
        }
        responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, responseText)
                .withSpeech(responseText)
                .withShouldEndSession(false);
        return responseBuilder.build();
    }
}
