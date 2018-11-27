package main.java.calendarCompanion.handlers;

public class AddToDoList implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AddToDoListIntent"));
    }

}
