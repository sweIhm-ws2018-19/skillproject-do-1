package calendarCompanion.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class AddToDoHelpIntentHandler implements RequestHandler {
	 @Override
	    public boolean canHandle(HandlerInput input) {
	        return input.matches(intentName("AddToDoHelpIntent"));
	    }
	 
	 @Override
	    public Optional<Response> handle(HandlerInput input) {
	        String speechText = "Sag mir welchen to-do und an welchem Wochentag du hinzufügen möchtest. Du kannst zum Beispiel sagen: am Montag gehe ich einkaufen oder am Dienstag muss ich {ToDo} erledigen"
	        		+ "Du kannst mir auch fragen: Alexa, füge Einkaufen am Mittwoch hinzu oder erstelle ein Einkaufen am Donnerstag";
	        String repromptText = "add help";
	        return input.getResponseBuilder()
	                .withSimpleCard("CalendarSession", speechText)
	                .withSpeech(speechText)
					.withReprompt(repromptText)
	                .withShouldEndSession(false)
	                .build();
	    }

}
