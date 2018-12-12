package calendarCompanion;

import static org.junit.Assert.*;

import calendarCompanion.model.PhrasesAndConstants;
import org.apache.http.impl.EnglishReasonPhraseCatalog;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import calendarCompanion.handlers.AddToDoOnWeekDayHandler;
import calendarCompanion.model.ToDoListItemOnWeekDay;

public class AddToDoOnWeekDayHandlerTest {
    private AddToDoOnWeekDayHandler addToDoOnWeekDayHandler;
    private ToDoListItemOnWeekDay toDoListItemWeekDay;
    
    @Before
    public void setup() {
        addToDoOnWeekDayHandler = new AddToDoOnWeekDayHandler();
    }
    
    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(addToDoOnWeekDayHandler.canHandle(inputMock));
    }
    
    @Test
    public void testHandle() {
    	final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
    	final Map<String, Object> sessionAttributes = new HashMap<>();
    	sessionAttributes.put(PhrasesAndConstants.TODO_SLOT, "Test");
    	sessionAttributes.put(PhrasesAndConstants.WOCHENTAG_SLOT, "Montag");
    	
    	
    	
    }


}
