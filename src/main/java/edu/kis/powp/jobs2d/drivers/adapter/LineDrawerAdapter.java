package edu.kis.powp.jobs2d.drivers.adapter;

import java.util.Objects;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.powp.jobs2d.Job2dDriver;

public class LineDrawerAdapter implements Job2dDriver {

    private int startX = 0, startY = 0;

    private final DrawPanelController controller;
    private final LineDrawStrategy strategy;

    public LineDrawerAdapter(
            DrawPanelController controller,
            LineDrawStrategy strategy
    ) {
        this.controller = Objects.requireNonNull(controller);
        this.strategy = Objects.requireNonNull(strategy);
    }

    @Override
    public void setPosition(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    @Override
    public void operateTo(int x, int y) {
        strategy.draw(controller, startX, startY, x, y);
        setPosition(x, y);
    }

    @Override
    public String toString() {
        return "LineDrawerAdapter";
    }
}
