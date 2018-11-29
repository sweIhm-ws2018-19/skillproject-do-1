package calendarCompanion;

import calendarCompanion.handlers.*;
import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;


public class CalendarCompanionStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new LaunchRequestHandler(),
                        new CancelandStopIntentHandler(),
                        new HelpIntentHandler(),
                        new FallbackIntentHandler(),
                        new AddToDoOnWeekDayHandler(),
                        new GetToDosOnWeekDaysHandler())
                .withTableName("toDoData")
                .withAutoCreateTable(true)
                // Add your skill id below
                //.withSkillId("")
                .build();
    }

    public CalendarCompanionStreamHandler() {
        super(getSkill());
    }

}