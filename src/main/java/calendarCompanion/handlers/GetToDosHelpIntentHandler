package calendarCompanion.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class GetToDosHelpIntentHandler implements RequestHandler {
	 @Override
	    public boolean canHandle(HandlerInput input) {
	        return input.matches(intentName("GetToDosHelpIntent"));
	    }
	 
	 @Override
	    public Optional<Response> handle(HandlerInput input) {
	        String speechText = "Frag mich, was du an einem bestimmten Wochentag erledigen musst "
	        		+ "oder was an einem bertimmten Wohentag ansteht, zum Beispiel: sage mir meine Todos am Freitag";
	        return input.getResponseBuilder()
	                .withSimpleCard("CalendarSession", speechText)
	                .withSpeech(speechText)
	                .withShouldEndSession(false)
	                .build();
	    }

}
