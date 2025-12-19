package edu.kis.powp.jobs2d.command.factory;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class RectangleFactory {
    public static ComplexCommand create(int x, int y, int w, int h) {
        return new ComplexCommand()
                .add(new SetPositionCommand(x, y))
                .add(new OperateToCommand(x + w, y))
                .add(new OperateToCommand(x + w, y + h))
                .add(new OperateToCommand(x, y + h))
                .add(new OperateToCommand(x, y));
    }
}
