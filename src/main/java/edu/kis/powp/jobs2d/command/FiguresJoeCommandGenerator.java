package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;

public class FiguresJoeCommandGenerator {

    public static ComplexCommand generateFigure1() {
        ComplexCommand command = new ComplexCommand();

        FiguresJoe.figureScript1(new Job2dDriver() {
            @Override
            public void setPosition(int x, int y) {
                command.add(new SetPositionCommand(x, y));
            }

            @Override
            public void operateTo(int x, int y) {
                command.add(new OperateToCommand(x, y));
            }
        });

        return command;
    }

    public static ComplexCommand generateFigure2() {
        ComplexCommand command = new ComplexCommand();

        FiguresJoe.figureScript2(new Job2dDriver() {
            @Override
            public void setPosition(int x, int y) {
                command.add(new SetPositionCommand(x, y));
            }

            @Override
            public void operateTo(int x, int y) {
                command.add(new OperateToCommand(x, y));
            }
        });

        return command;
    }
}
