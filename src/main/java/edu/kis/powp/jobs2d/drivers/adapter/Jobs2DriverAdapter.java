package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.powp.jobs2d.AbstractDriver;
import edu.kis.powp.jobs2d.Job2dDriver;

/**
 * Adapter do FiguresJane:
 * jest AbstractDriver, ale rysuje przez aktualny Job2dDriver.
 */
public class Jobs2DriverAdapter extends AbstractDriver {

    private final Job2dDriver driver;

    public Jobs2DriverAdapter(Job2dDriver driver, int startX, int startY) {
        super(startX, startY);
        this.driver = driver;

        driver.setPosition(startX, startY);
    }

    @Override
    public void operateTo(int x, int y) {
        int sx = getX();
        int sy = getY();

        driver.setPosition(sx, sy);
        driver.operateTo(x, y);

        super.setPosition(x, y);
    }
}
