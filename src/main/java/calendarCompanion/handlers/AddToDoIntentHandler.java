package calendarCompanion.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class AddToDoIntentHandler implements RequestHandler {
    public static final List<String> TO_DOS = new ArrayList<>();
    public static final String COLOR_KEY = "COLOR";
    public static final String COLOR_SLOT = "Color";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AddToDoIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;
        String favoriteColor = (String) input.getAttributesManager().getSessionAttributes().get(COLOR_KEY);

        if (favoriteColor != null && !favoriteColor.isEmpty()) {
            speechText = String.format("Deine Lieblingsfarbe ist %s. Auf Wiedersehen.", favoriteColor);
        } else {
            // Since the user's favorite color is not set render an error message.
            speechText = "Ich weiss nicht welches Deine Lieblingsfarbe ist. Sag mir Deine Lieblingsfarbe. Sage zum Beispiel: ich mag rot.";
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("ColorSession", speechText)
                .build();
    }
}
