package calendarCompanion;

import static org.junit.Assert.*;

import org.junit.Test;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.ui.SsmlOutputSpeech;

import calendarCompanion.handlers.LaunchRequestHandler;
import calendarCompanion.model.PhrasesAndConstants;

import org.junit.Before;
import org.mockito.Mockito;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class LaunchRequestHandlerTest {
	private LaunchRequestHandler handler;
	  private final static String launchPhrase = "Hallo. Ich bin dein persönlicher Calendar Companion. Mit mir kannst du deinen persönlichen Kalender erstellen und managen. Möchtest du ein ToDo, oder einen Termin hinzufügen?";


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

    @Test
    public void testHandle() {
        HandlerInput input = TestUtil.mockInputWithoutSlot();
        Response response = handler.handle(input).get();
        SsmlOutputSpeech speech = (SsmlOutputSpeech) response.getOutputSpeech();
        String speechString = speech.toString();
        int indexOfResponse = speechString.indexOf("Hallo");
        String substringOfSSml = speechString.substring(indexOfResponse);
        substringOfSSml = substringOfSSml.substring(0, substringOfSSml.length() - 10); //deletes <speak> from ssml
        System.out.println(substringOfSSml);
        assertEquals(substringOfSSml, launchPhrase);
    }


}
