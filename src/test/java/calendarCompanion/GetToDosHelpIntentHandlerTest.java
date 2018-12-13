package calendarCompanion;

import calendarCompanion.handlers.GetToDosHelpIntentHandler;
import calendarCompanion.handlers.HelpIntentHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GetToDosHelpIntentHandlerTest {
    GetToDosHelpIntentHandler helpHandler;
    private final static String HELP_PHRASE = "Frag mich, was du an einem bestimmten Wochentag erledigen musst "
            + "oder was an einem bertimmten Wohentag ansteht, zum Beispiel: sage mir meine Todos am Freitag";

    @Before
    public void setup() {
        helpHandler = new GetToDosHelpIntentHandler();
    }

    @Test
    public void testCtor() {
        Object end = new GetToDosHelpIntentHandler();
        assertEquals(end.getClass(), GetToDosHelpIntentHandler.class);
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(helpHandler.canHandle(inputMock));
    }

    @Test
    public void testHandle() {
        HandlerInput input = TestUtil.mockInputWithoutSlot();
        Response response = helpHandler.handle(input).get();
        assertTrue(response.getOutputSpeech().toString().contains(HELP_PHRASE));
    }
}
