package calendarCompanion;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import calendarCompanion.model.PhrasesAndConstants;
import calendarCompanion.handlers.GetToDosOnWeekDaysHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import org.junit.Test;

public class GetToDosOnWeekDayHandlerTest {
	
//	Map<String, Object> persistentAttributes = new HashMap<>();
//	persistantAttributes.put(PhrasesAndConstants.TODO_SLOT, "Test");

	private GetToDosOnWeekDaysHandler handler;

	@Before
	public void setup() {
		handler = new GetToDosOnWeekDaysHandler();
	}

	@Test
	public void testCanHandle() {
		final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
		when(inputMock.matches(any())).thenReturn(true);
		assertTrue(handler.canHandle(inputMock));
	}

}
