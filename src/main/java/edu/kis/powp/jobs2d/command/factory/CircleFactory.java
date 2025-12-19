package edu.kis.powp.jobs2d.command.factory;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class CircleFactory {
    public static ComplexCommand create(int cx, int cy, int r, int segments) {
        if (segments < 3) segments = 3;

        ComplexCommand cmd = new ComplexCommand();
        double step = 2.0 * Math.PI / segments;

        int x0 = (int)Math.round(cx + r * Math.cos(0));
        int y0 = (int)Math.round(cy + r * Math.sin(0));

        cmd.add(new SetPositionCommand(x0, y0));

        for (int i = 1; i <= segments; i++) {
            double a = i * step;
            int x = (int)Math.round(cx + r * Math.cos(a));
            int y = (int)Math.round(cy + r * Math.sin(a));
            cmd.add(new OperateToCommand(x, y));
        }
        return cmd;
    }
}
