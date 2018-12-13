package calendarCompanion;

import calendarCompanion.handlers.DeleteToDoHelpIntentHandler;
import calendarCompanion.handlers.FallbackIntentHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DeleteToDoHelpIntentHandlerTest {
    private DeleteToDoHelpIntentHandler handler;
    private final static String DELETE_PHRASE = "Sag mir welchen to-do aus welchem Wochentag du löshen möchtest. Du kannst zum Beispiel sagen: lösche Einkaufen am Montag.";

    @Before
    public void setup() {
        handler = new DeleteToDoHelpIntentHandler();
    }

    @Test
    public void testCtor() {
        Object launch = new DeleteToDoHelpIntentHandler();
        assertEquals(launch.getClass(), DeleteToDoHelpIntentHandler.class);
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }

    @Test
    public void testHandle() {
        HandlerInput input = TestUtil.mockInputWithoutSlot();
        Response response = handler.handle(input).get();
        assertTrue(response.getOutputSpeech().toString().contains(DELETE_PHRASE));
    }

}
