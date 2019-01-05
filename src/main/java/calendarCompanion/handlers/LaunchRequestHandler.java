package calendarCompanion.handlers;

import calendarCompanion.HttpRequests.DateTimeDE;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class LaunchRequestHandler implements RequestHandler {

    private DateTimeDE dateTimeDe = new DateTimeDE();

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
        //input.getAttributesManager().getSessionAttributes();
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        try {
            dateTimeDe.httpGetTimeAndDate();
        }catch(Exception e)
        {
            System.err.println("httpGet: " + e.getMessage());
        }
        String month = dateTimeDe.getMonth();
        String day = dateTimeDe.getDay();
        String time = dateTimeDe.getTime();

        String speechText = "Hallo, es ist " + month + " der " + day + "te, "+ time + " Uhr. Ich bin dein persönlicher Calendar Companion. Mit mir kannst du deinen Kalender erstellen und managen. Möchtest du ein To-Do, oder einen Termin hinzufügen?";
        String repromtText = "Bitte sage mir, ob du einen Termin erstellen willst, oder ein To-Do zu deiner Liste hinzufügen möchtest. Du kannst auch deine Termine und To-DoS für heute erfragen. Wenn du Hilfe brauchst, sag Hilfe";


        return input.getResponseBuilder()
                .withSimpleCard("CalendarSession", speechText)
                .withSpeech(speechText)
                .withReprompt(repromtText)
                .build();
    }
}
