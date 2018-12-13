package calendarCompanion;
import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;

import calendarCompanion.model.PhrasesAndConstants;

import org.mockito.Mockito;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TestUtil {
//	public static HandlerInput mockHandlerInput(String favoriteColor, Map<String, Object> sessionAttributes,
//			Map<String, Object> persistentAttributes, Map<String, Object> requestAttributes) {
//		final AttributesManager attributesManagerMock = Mockito.mock(AttributesManager.class);
//		when(attributesManagerMock.getSessionAttributes()).thenReturn(sessionAttributes);
//		when(attributesManagerMock.getPersistentAttributes()).thenReturn(persistentAttributes);
//		when(attributesManagerMock.getRequestAttributes()).thenReturn(requestAttributes);
// Mock Slots
//		final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder()
//				.withRequest(IntentRequest.builder().withIntent(Intent.builder()
//						.putSlotsItem(PhrasesAndConstants.COLOR_SLOT, Slot.builder()
//								.withName(PhrasesAndConstants.COLOR_SLOT).withValue(favoriteColor).build())
//						.build()).build())
//				.build();
// Mock Handler input attributes
//		final HandlerInput input = Mockito.mock(HandlerInput.class);
//		when(input.getAttributesManager()).thenReturn(attributesManagerMock);
//		when(input.getResponseBuilder()).thenReturn(new ResponseBuilder());
//		//when(input.getRequestEnvelope()).thenReturn(requestEnvelopeMock);
//		return input;
//	}

	public static HandlerInput mockHandlerInput(Map<String, String> slots, Map<String, Object> sessionAttributes, Map<String, Object> persistentAttributes, Map<String, Object> requestAttributes) {
		final AttributesManager attributesManagerMock = Mockito.mock(AttributesManager.class);
		when(attributesManagerMock.getSessionAttributes()).thenReturn(sessionAttributes);
		when(attributesManagerMock.getPersistentAttributes()).thenReturn(persistentAttributes);
		when(attributesManagerMock.getRequestAttributes()).thenReturn(requestAttributes);
		final Intent.Builder intentBuilder = Intent.builder();
		if (slots != null){
			slots.forEach((key, value) ->
					intentBuilder.putSlotsItem(key, Slot.builder().withName(key).withValue(value).build())
			);
		}

		// Mock Slots
		final RequestEnvelope requestEnvelopeMock = RequestEnvelope.builder()
				.withRequest(IntentRequest.builder()
						.withIntent(intentBuilder.build())
						.build())
				.build();
		// Mock Handler input attributes
		final HandlerInput input = Mockito.mock(HandlerInput.class);
		when(input.getAttributesManager()).thenReturn(attributesManagerMock);
		when(input.getResponseBuilder()).thenReturn(new ResponseBuilder());
		when(input.getRequestEnvelope()).thenReturn(requestEnvelopeMock);
		return input;
	}

	public static Response standardTestForHandle(RequestHandler handler) {
		final Map<String, Object> sessionAttributes = new HashMap<>();
		final Map<String, Object> persistentAttributes = new HashMap<>();
		sessionAttributes.put(PhrasesAndConstants.TODO_SLOT, "Test");
		persistentAttributes.put(PhrasesAndConstants.TODO_SLOT, "Test");
		final HandlerInput inputMock = TestUtil.mockHandlerInput(null, sessionAttributes, persistentAttributes, null);
		final Optional<Response> res = handler.handle(inputMock);
		assertTrue(res.isPresent());
		final Response response = res.get();
//assertFalse(response.getShouldEndSession());
		assertNotEquals("Test", response.getReprompt());
		assertNotNull(response.getOutputSpeech());
		return response;
	}
	
    public static HandlerInput mockEmptyInput() {
        //HandlerInput Mock
        HandlerInput inputMock = HandlerInput.builder()
                .withRequestEnvelope(RequestEnvelope.builder()
                        .withSession(Session.builder().build())
                        .build())
                .build();
        return inputMock;
    }
    
    public static HandlerInput mockInputWithoutSlot() {
        //HandlerInput Mock
        HandlerInput inputMock = HandlerInput.builder()
                .withRequestEnvelope(RequestEnvelope.builder()
                        .withRequest(IntentRequest.builder()
                                .withIntent(Intent.builder().build())
                                .build())
                        .withSession(Session.builder().build())
                        .build())
                .build();
        return inputMock;
    }

	public static Response sessionEndedTestForHandle(RequestHandler handler) {
		final HandlerInput inputMock = TestUtil.mockHandlerInput(null, null, null, null);
		final Optional<Response> res = handler.handle(inputMock);
		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getShouldEndSession());
		return response;
	}
}
