package calendarCompanion;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import calendarCompanion.handlers.FallbackIntentHandler;
import calendarCompanion.handlers.HelpIntentHandler;
import calendarCompanion.handlers.LaunchRequestHandler;
//import main.java.calendarCompanion.handlers.SessionEndedRequestHandler;
import calendarCompanion.handlers.CancelandStopIntentHandler;

public class CalendarCompanionStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new calendarCompanion.handlers.LaunchRequestHandler(),
                      //  new CancelandStopIntentHandler(),
                        new calendarCompanion.handlers.HelpIntentHandler(),
                        new calendarCompanion.handlers.FallbackIntentHandler())
                // Add your skill id below
                //.withSkillId("")
                .build();
    }

    public CalendarCompanionStreamHandler() {
        super(getSkill());
    }

}