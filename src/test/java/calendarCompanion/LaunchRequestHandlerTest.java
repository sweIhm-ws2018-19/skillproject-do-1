package calendarCompanion;

import static org.junit.Assert.*;

import calendarCompanion.HttpRequests.DateTimeDE;
import org.junit.Test;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.ui.SsmlOutputSpeech;

import calendarCompanion.handlers.LaunchRequestHandler;
import calendarCompanion.model.PhrasesAndConstants;

import org.junit.Before;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class LaunchRequestHandlerTest {
	private LaunchRequestHandler handler;
    private DateTimeDE dateTimeDe = new DateTimeDE();

@Before
public void setup() {
    handler = new LaunchRequestHandler();
}

@Test
public void testCtor() {
	Object launch = new LaunchRequestHandler();
	assertEquals(launch.getClass(), LaunchRequestHandler.class);
}

@Test
public void testCanHandle() {
    final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
    when(inputMock.matches(any())).thenReturn(true);
    assertTrue(handler.canHandle(inputMock));
}

    @Test
    public void testHandle() {
        try {
            dateTimeDe.httpGetTimeAndDate();
        }
        catch(Exception e)
        {
            System.out.println("httpGet: " + e.getMessage());
        }
        String month = dateTimeDe.getMonth();
        String day = dateTimeDe.getDay();
        String time = dateTimeDe.getTime();
        HandlerInput input = TestUtil.mockInputWithoutSlot();
        Response response = handler.handle(input).get();
        String LAUNCH_PHRASE = "Hallo, es ist " + month + " der " + day + "te, "+ time + " Uhr. Ich bin dein persönlicher Calendar Companion. Mit mir kannst du deinen Kalender erstellen und managen. Möchtest du ein To-Do, oder einen Termin hinzufügen?";
        assertTrue(response.getOutputSpeech().toString().contains(LAUNCH_PHRASE));
    }


}
