package calendarCompanion;

import calendarCompanion.handlers.AddToDoHelpIntentHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AddToDoHelpIntentHandlerTest {
    AddToDoHelpIntentHandler helpHandler;
    private final static String HELP_PHRASE = "Sag mir welchen toDo und an welchem Wochentag du hinzufügen möchtest. Du kannst zum Beispiel sagen: am Montag gehe ich einkaufen oder füge sport am Freitag hinzu"
            + "Du kannst mir auch fragen: Alexa, füge Einkaufen am Mittwoch hinzu oder erstelle ein Einkaufen am Donnerstag";

    @Before
    public void setup() {
        helpHandler = new AddToDoHelpIntentHandler();
    }

    @Test
    public void testCtor() {
        Object end = new AddToDoHelpIntentHandler();
        assertEquals(end.getClass(), AddToDoHelpIntentHandler.class);
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
