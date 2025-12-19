package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;

public class ComplexCommand implements DriverCommand {
    private final List<DriverCommand> commands = new ArrayList<>();

    public ComplexCommand add(DriverCommand cmd) {
        commands.add(cmd);
        return this;
    }

    public List<DriverCommand> getCommands() {
        return Collections.unmodifiableList(commands);
    }

    @Override
    public void execute(Job2dDriver driver) {
        for (DriverCommand c : commands) {
            c.execute(driver);
        }
    }
}
