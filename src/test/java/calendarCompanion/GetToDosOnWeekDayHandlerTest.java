package calendarCompanion;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import calendarCompanion.model.PhrasesAndConstants;

import org.junit.Test;

import calendarCompanion.model.PhrasesAndConstants;

public class GetToDosOnWeekDayHandlerTest {
	
//	Map<String, Object> persistentAttributes = new HashMap<>();
//	persistantAttributes.put(PhrasesAndConstants.TODO_SLOT, "Test");

	private GetToDosOnWeekDayHandler handler;

	@Before
	public void setup() {
		handler = new GetToDosOnWeekDayHandler();
	}

	@Test
	public void testCanHandle() {
		final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
		when(inputMock.matches(any())).thenReturn(true);
		assertTrue(handler.canHandle(inputMock));
	}

}
