package calendarCompanion;

import static org.junit.Assert.*;

import calendarCompanion.model.PhrasesAndConstants;
import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;
import org.apache.http.impl.EnglishReasonPhraseCatalog;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import calendarCompanion.handlers.AddToDoOnWeekDayHandler;
import calendarCompanion.model.ToDoListItemOnWeekDay;

public class AddToDoOnWeekDayHandlerTest {
    private AddToDoOnWeekDayHandler addToDoOnWeekDayHandler;
    private HandlerInput inputMock;
    private ToDoListItemOnWeekDay toDoListItemWeekDay;
    
    @Before
    public void setup() {
        addToDoOnWeekDayHandler = new AddToDoOnWeekDayHandler();
        inputMock = mock(HandlerInput.class);
    }

    
    @Test
    public void testCanHandle() {
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(addToDoOnWeekDayHandler.canHandle(inputMock));
    }
    
    @Test
    public void testHandle() {
    	//final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
    	//final Map<String, Slot> sessionAttributes = new HashMap<>();
    	//sessionAttributes.put(PhrasesAndConstants.TODO_SLOT, "Test");
    	//sessionAttributes.put(PhrasesAndConstants.WOCHENTAG_SLOT, "Montag");
    	//inputMock.getAttributesManager();

        AttributesManager attributeManager = mock(AttributesManager.class);
        when(inputMock.getAttributesManager()).thenReturn(attributeManager);
        final Map<String, Slot> sessionAttributes = new HashMap<>();
        sessionAttributes.put(PhrasesAndConstants.TODO_SLOT, Slot.builder().withValue("Test").build());
      sessionAttributes.put(PhrasesAndConstants.WOCHENTAG_SLOT, Slot.builder().withValue("Montag").build());

        Intent intent = Intent.builder().withSlots(sessionAttributes).build();
        when(inputMock.getRequest()).thenReturn(IntentRequest.builder().withIntent(intent).build());

        when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

        Optional<Response> response = addToDoOnWeekDayHandler.handle(inputMock);

        assertTrue(response.isPresent());
    	
    	
    }


}
