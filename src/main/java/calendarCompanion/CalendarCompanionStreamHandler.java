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
                        new GetToDosOnWeekDayHandler(),
                        new DeleteToDoOnWeekDayHandler())
                .withTableName("toDoData")
                .withAutoCreateTable(true)
                .build();
    }

    public CalendarCompanionStreamHandler() {
        super(getSkill());
    }

}