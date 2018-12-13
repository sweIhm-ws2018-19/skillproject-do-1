package calendarCompanion;

import calendarCompanion.handlers.CancelandStopIntentHandler;
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

public class HelpIntentHandlerTest {
    HelpIntentHandler helpHandler;
    private final static String HELP_PHRASE = "Wenn du Hife brauchst, um einen ToDo hinzufügen, sag add Hilfe. Wenn du Hilfe brauchst, um deine ToDos zu hören, sag get Hilfe. Wenn du Hilfe brauchst, um dein ToDos zu löschen, sag delete Hilfe.";

    @Before
    public void setup() {
        helpHandler = new HelpIntentHandler();
    }

    @Test
    public void testCtor() {
        Object end = new HelpIntentHandler();
        assertEquals(end.getClass(), HelpIntentHandler.class);
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
