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
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        try {
            dateTimeDe.httpGetTimeAndDate();
        }catch(Exception e)
        {
            System.out.println("httpGet: " + e.getMessage());
        }
        String month = dateTimeDe.getMonth();
        String day = dateTimeDe.getDay();
        String time = dateTimeDe.getTime();

        String speechText = "Hallo, es ist " + month + " der " + day + ", "+ time + " Uhr. Ich bin dein persönlicher Calendar Companion. Mit mir kannst du deinen persönlichen Kalender erstellen und managen. Möchtest du ein ToDo, oder einen Termin hinzufügen?";
        String repromtText = "Bitte sage mir, ob du einen Termin erstellen willst, oder ein ToDo zu deiner Liste hinzufügen möchtest. Du kannst auch deine Termine und ToDoS für heute erfragen. Wenn du Hilfe brauchst, sag Hilfe";


        return input.getResponseBuilder()
                .withSimpleCard("CalendarSession", speechText)
                .withSpeech(speechText)
                .withReprompt(repromtText)
                .build();
    }
}
