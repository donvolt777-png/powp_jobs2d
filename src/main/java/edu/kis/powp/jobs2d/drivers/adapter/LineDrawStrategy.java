package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.legacy.drawer.panel.DrawPanelController;

@FunctionalInterface
public interface LineDrawStrategy {
    void draw(
            DrawPanelController controller,
            int x1, int y1,
            int x2, int y2
    );
}
