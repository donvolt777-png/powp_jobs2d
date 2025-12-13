package edu.kis.powp.jobs2d;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.kis.legacy.drawer.panel.DefaultDrawerFrame;
import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.adapter.DrawPanelControllerAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.LineDrawStrategy;
import edu.kis.powp.jobs2d.events.SelectChangeVisibleOptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigureOptionListener;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.drivers.adapter.LineDrawerAdapter;
import edu.kis.powp.jobs2d.AbstractDriver;
import edu.kis.powp.jobs2d.drivers.adapter.Jobs2DriverAdapter; // Twoja nazwa klasy
import edu.kis.powp.jobs2d.magicpresets.FiguresJane;          // jeśli taki pakiet

public class TestJobs2dPatterns {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Setup test concerning preset figures in context.
	 * 
	 * @param application Application context.
	 */
	private static void setupPresetTests(Application application) {
		DriverManager driverManager = DriverFeature.getDriverManager();

		application.addTest("Figure Joe 1", new SelectTestFigureOptionListener(driverManager, 1));
		application.addTest("Figure Joe 2", new SelectTestFigureOptionListener(driverManager, 2));

		application.addTest("Figure Jane 1", e -> {
			Job2dDriver current = driverManager.getCurrentDriver();
			AbstractDriver adapted = new Jobs2DriverAdapter(current, 0, 0);
			FiguresJane.figureScript(adapted);
		});
	}


	/**
	 * Setup driver manager, and set default driver for application.
	 * 
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {

		Job2dDriver loggerDriver = new LoggerDriver();
		DriverFeature.addDriver("Logger Driver", loggerDriver);
		DriverFeature.getDriverManager().setCurrentDriver(loggerDriver);
		Job2dDriver buggy = new DrawPanelControllerAdapter();
		DriverFeature.addDriver("Buggy Simulator", buggy);

		DrawPanelController controller = DrawerFeature.getDrawerController();

		LineDrawStrategy solidLine = (ctrl, x1, y1, x2, y2) -> {
			ILine line = LineFactory.getBasicLine();
			line.setStartCoordinates(x1, y1);
			line.setEndCoordinates(x2, y2);
			ctrl.drawLine(line);
		};


		LineDrawStrategy dashedLine = (ctrl, x1, y1, x2, y2) -> {
			int dashLen = 12;
			int gapLen  = 6;

			double dx = x2 - x1;
			double dy = y2 - y1;
			double dist = Math.hypot(dx, dy);
			if (dist == 0) return;

			double ux = dx / dist;
			double uy = dy / dist;
			double pos = 0;

			while (pos < dist) {
				double draw = Math.min(dashLen, dist - pos);

				int sx = (int)(x1 + pos * ux);
				int sy = (int)(y1 + pos * uy);
				int ex = (int)(x1 + (pos + draw) * ux);
				int ey = (int)(y1 + (pos + draw) * uy);

				ILine seg = LineFactory.getBasicLine();
				seg.setStartCoordinates(sx, sy);
				seg.setEndCoordinates(ex, ey);
				ctrl.drawLine(seg);

				pos += dashLen + gapLen;
			}
		};

		DriverFeature.addDriver(
				"Line – solid",
				new LineDrawerAdapter(controller, solidLine)
		);

		DriverFeature.addDriver(
				"Line – dashed(12/6)",
				new LineDrawerAdapter(controller, dashedLine)
		);

		DriverFeature.updateDriverInfo();
	}


	/**
	 * Auxiliary routines to enable using Buggy Simulator.
	 * 
	 * @param application Application context.
	 */
	private static void setupDefaultDrawerVisibilityManagement(Application application) {
		DefaultDrawerFrame defaultDrawerWindow = DefaultDrawerFrame.getDefaultDrawerFrame();
		application.addComponentMenuElementWithCheckBox(DrawPanelController.class, "Default Drawer Visibility",
				new SelectChangeVisibleOptionListener(defaultDrawerWindow), true);
		defaultDrawerWindow.setVisible(true);
	}

	/**
	 * Setup menu for adjusting logging settings.
	 * 
	 * @param application Application context.
	 */
	private static void setupLogger(Application application) {
		application.addComponentMenu(Logger.class, "Logger", 0);
		application.addComponentMenuElement(Logger.class, "Clear log",
				(ActionEvent e) -> application.flushLoggerOutput());
		application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
		application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
		application.addComponentMenuElement(Logger.class, "Warning level",
				(ActionEvent e) -> logger.setLevel(Level.WARNING));
		application.addComponentMenuElement(Logger.class, "Severe level",
				(ActionEvent e) -> logger.setLevel(Level.SEVERE));
		application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("2d jobs Visio");
				DrawerFeature.setupDrawerPlugin(app);
				setupDefaultDrawerVisibilityManagement(app);

				DriverFeature.setupDriverPlugin(app);
				setupDrivers(app);
				setupPresetTests(app);
				setupLogger(app);

				app.setVisibility(true);
			}
		});
	}

}
