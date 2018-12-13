package calendarCompanion;

import calendarCompanion.handlers.AddToDoOnWeekDayHandler;
import calendarCompanion.handlers.DeleteToDoOnWeekDayHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DeleteToDoOnWeekDayHandlerTest {

    private DeleteToDoOnWeekDayHandler deleteToDoOnWeekDayHandler;

    @Before
    public void setup() {
        deleteToDoOnWeekDayHandler = new DeleteToDoOnWeekDayHandler();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(deleteToDoOnWeekDayHandler.canHandle(inputMock));
    }
    @Test
    public void testHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        final Map<String, Object> sessionAttributes = new HashMap<>();
    }
}
