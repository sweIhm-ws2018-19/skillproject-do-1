package calendarCompanion.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;


import java.util.Optional;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        String speechText = "Hallo. Ich bin dein pers�nlicher Calendar Companion. Mit mir kannst du deinen persönlichen Kalender erstellen und managen. Möchtest du ein ToDo, oder einen Termin hinzufügen?";
        String repromtText = "Bitte sage mir, ob du einen Termin erstellen willst, oder ein ToDo zu deiner Liste hinzufügen möchtest. Du kannst auch deine Termine und ToDoS für heute erfragen.";

        return input.getResponseBuilder()
                .withSimpleCard("CalendarSession", speechText)
                .withSpeech(speechText)
                .withReprompt(repromtText)
                .build();
    }
}
