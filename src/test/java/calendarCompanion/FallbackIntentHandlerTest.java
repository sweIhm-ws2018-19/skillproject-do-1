package calendarCompanion;

import calendarCompanion.handlers.FallbackIntentHandler;
import calendarCompanion.handlers.LaunchRequestHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FallbackIntentHandlerTest {
    private FallbackIntentHandler handler;
    private final static String Fallback_PHRASE = "Tut mir leid, das weiss ich nicht. Sage einfach Hilfe.";

    @Before
    public void setup() {
        handler = new FallbackIntentHandler();
    }

    @Test
    public void testCtor() {
        Object launch = new FallbackIntentHandler();
        assertEquals(launch.getClass(), FallbackIntentHandler.class);
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
        assertTrue(response.getOutputSpeech().toString().contains(Fallback_PHRASE));
    }


}
