package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;

public class FiguresJoeCommandGenerator {

    public static ComplexCommand generateFigure1() {
        RecordingDriver rec = new RecordingDriver();
        FiguresJoe.figureScript1(rec);
        return rec.getRecordedCommand();
    }

    public static ComplexCommand generateFigure2() {
        RecordingDriver rec = new RecordingDriver();
        FiguresJoe.figureScript2(rec);
        return rec.getRecordedCommand();
    }
}
