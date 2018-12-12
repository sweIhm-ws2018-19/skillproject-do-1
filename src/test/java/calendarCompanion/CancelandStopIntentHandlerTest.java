package calendarCompanion;

import calendarCompanion.handlers.CancelandStopIntentHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CancelandStopIntentHandlerTest {
    private CancelandStopIntentHandler handler;
    private final static String END_PHRASE = "Auf Wiedersehen";


    @Before
    public void setup() {
        handler = new CancelandStopIntentHandler();
    }

    @Test
    public void testCtor() {
        Object end = new CancelandStopIntentHandler();
        assertEquals(end.getClass(), CancelandStopIntentHandler.class);
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
        assertTrue(response.getOutputSpeech().toString().contains(END_PHRASE));
    }
}
