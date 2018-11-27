package calendarCompanion;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import main.java.colorpicker.handlers.FallbackIntentHandler;
import main.java.colorpicker.handlers.HelpIntentHandler;
import main.java.colorpicker.handlers.LaunchRequestHandler;
import main.java.colorpicker.handlers.SessionEndedRequestHandler;
import main.java.colorpicker.handlers.CancelandStopIntentHandler;

public class CalendarCompanionStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new LaunchRequestHandler(),
                        new CancelandStopIntentHandler(),
                        new SessionEndedRequestHandler(),
                        new HelpIntentHandler(),
                        new FallbackIntentHandler())
                // Add your skill id below
                //.withSkillId("")
                .build();
    }

    public CalendarCompanionStreamHandler() {
        super(getSkill());
    }

}