package calendarCompanion.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;

//import calendarCompanion.model.ToDoList;

import java.util.*;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

public class HearToDoIntentHandler implements RequestHandler {
	
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("HearToDoListIntent"));
    }

	@Override
	public Optional<Response> handle(HandlerInput input) {
	    ResponseBuilder responseBuilder = input.getResponseBuilder();
	    
//	    AttributesManager attributesManager = input.getAttributesManager();
//        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
//        String todoList = (String) persistentAttributes.get("ToDoList");
//        
//
//        if(todoList != null){
//            //put stored color in session Attributes
//        	List<String> list = new ArrayList<String>();
//            ToDoList todoListObject = new ToDoList(list);
//            input.getAttributesManager().setSessionAttributes(Collections.singletonMap(PhrasesAndConstants.COLOR_KEY,
//                    lieblingsfarbe.getFarbe()));
//            String speechText =
//                    String.format("%s %s. %s", PhrasesAndConstants.LIEBLINGSFARBE_IS, lieblingsfarbe.getFarbe(),
//                            PhrasesAndConstants.CHANGE_LIEBLINGSFARBE);
//            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText)
//                    .withSpeech(speechText)
//                    .withReprompt(PhrasesAndConstants.HELP_REPROMPT);
//        } else {
//            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.WELCOME)
//                    .withSpeech(PhrasesAndConstants.WELCOME)
//                    .withReprompt(PhrasesAndConstants.HELP_REPROMPT);
//        }
        

	    	return responseBuilder.build();
	}

}
