package calendarCompanion.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class DeleteToDoHelpIntentHandler implements RequestHandler {
	 @Override
	    public boolean canHandle(HandlerInput input) {
	        return input.matches(intentName("DeleteToDoHelpIntent"));
	    }
	 
	 @Override
	    public Optional<Response> handle(HandlerInput input) {
	        String speechText = "Sag mir welchen to-do aus welchem Wochentag du löshen möchtest. Du kannst zum Beispiel sagen: lösche Einkaufen am Montag.";
	        return input.getResponseBuilder()
	                .withSimpleCard("CalendarSession", speechText)
	                .withSpeech(speechText)
	                .withShouldEndSession(false)
	                .build();
	    }

}
