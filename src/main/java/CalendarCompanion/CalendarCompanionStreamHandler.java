package main.java.CalendarCompanion;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import main.java.colorpicker.handlers.FallbackIntentHandler;
import main.java.colorpicker.handlers.HelpIntentHandler;
import main.java.colorpicker.handlers.LaunchRequestHandler;
import main.java.colorpicker.handlers.SessionEndedRequestHandler;
import main.java.colorpicker.handlers.WhatsMyColorIntentHandler;
import main.java.colorpicker.handlers.CancelandStopIntentHandler;
import main.java.colorpicker.handlers.MyColorIsIntentHandler;

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
    //khjkjgj,hg

    public CalendarCompanionStreamHandler() {
        super(getSkill());
    }

}