package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

public class RecordingDriver implements Job2dDriver {

    private final ComplexCommand recorded = new ComplexCommand();

    @Override
    public void setPosition(int x, int y) {
        recorded.add(new SetPositionCommand(x, y));
    }

    @Override
    public void operateTo(int x, int y) {
        recorded.add(new OperateToCommand(x, y));
    }

    public ComplexCommand getRecordedCommand() {
        return recorded;
    }
}
