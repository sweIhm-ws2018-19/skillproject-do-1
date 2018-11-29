package calendarCompanion.handlers;

import calendarCompanion.HttpRequests.DateTimeDE;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;


import java.io.IOException;
import java.util.Optional;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	
    	   GregorianCalendar now = new GregorianCalendar(); 
           df = DateFormat.getDateInstance(DateFormat.MEDIUM);

        String speechText = "Hallo. Es ist " +df +  "Ich bin dein persönlicher Calendar Companion. Mit mir kannst du deinen persönlichen Kalender erstellen und managen. Möchtest du ein ToDo zu deiner Liste hinzufügen?";
        String repromtText = "Sage so etwas, wie: füge Spazierengehen am Donnerstag hinzu.";

        return input.getResponseBuilder()
                .withSimpleCard("CalendarSession", speechText)
                .withSpeech(speechText)
                .withReprompt(repromtText)
                .build();
    }
}
