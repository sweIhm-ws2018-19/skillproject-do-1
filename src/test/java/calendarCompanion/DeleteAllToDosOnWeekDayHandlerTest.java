package calendarCompanion;

import calendarCompanion.handlers.AddToDoOnWeekDayHandler;
import calendarCompanion.handlers.DeleteAllToDosOnWeekDayHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteAllToDosOnWeekDayHandlerTest {

    private DeleteAllToDosOnWeekDayHandler deleteAllToDosOnWeekDayHandler;
    private HandlerInput inputMock;

    @Before
    public void setup() {
        deleteAllToDosOnWeekDayHandler = new DeleteAllToDosOnWeekDayHandler();
        inputMock = mock(HandlerInput.class);
    }


    @Test
    public void testCanHandle() {
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(deleteAllToDosOnWeekDayHandler.canHandle(inputMock));
    }
}
