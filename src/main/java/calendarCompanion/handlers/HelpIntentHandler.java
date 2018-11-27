package calendarCompanion.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class HelpIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Du kannst ein ToDo, oder einen Termin zu deinem Kalender hinzufügen. Ein ToDo ist zeitunabhängig. Es können beliebig viele ToDos zu jedem Wochentag hinzugefügt werden." +
                "Ein Termin wird zu einem festen Zeitpunkt in deinen Kalender eingetragen. Du kannst Benachrichtigungen zu jedem Termin aktivieren.";
        String repromptText = "Bitte sage mir, ob du ein ToDo, oder einen Termin deinem Kalender hinzufügen möchtest";
        return input.getResponseBuilder()
                .withSimpleCard("CalendarSession", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .withShouldEndSession(false)
                .build();
    }
}
