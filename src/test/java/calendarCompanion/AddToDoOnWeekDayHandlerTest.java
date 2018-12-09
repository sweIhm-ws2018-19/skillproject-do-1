package calendarCompanion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import calendarCompanion.handlers.AddToDoOnWeekDayHandler;

public class AddToDoOnWeekDayHandlerTest {
    private AddToDoOnWeekDayHandler addToDoOnWeekDayHandler;
    
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


}
