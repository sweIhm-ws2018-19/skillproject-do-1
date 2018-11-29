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

    //public static DateTimeDE dateTimeDe;

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {


        //String dateString = "heute ist der" + dateTimeDe.getDay() + "te" + dateTimeDe.getMonth() + dateTimeDe.getYear()+".";
        String speechText = "Hallo, Ich bin dein pers�nlicher Calendar Companion. Mit mir kannst du deinen pers�nlichen Kalender erstellen und managen. M�chtest du ein ToDo zu deiner Liste hinzuf�gen?";
        String repromtText = "Sage so etwas, wie: f�ge Spazierengehen am Donnerstag hinzu.";

        return input.getResponseBuilder()
                .withSimpleCard("CalendarSession", speechText)
                .withSpeech(speechText)
                .withReprompt(repromtText)
                .build();
    }
}
