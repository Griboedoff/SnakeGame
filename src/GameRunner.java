import Model.Engine;
import SwingGui.SwingGUI;
import SwingGui.SwingKeyListener;

public class GameRunner
{
	public static void main(String[] args)
	{
		SwingKeyListener l = new SwingKeyListener();
		SwingGUI gui = new SwingGUI();
		gui.addKeyListener(l);
		Engine engine = new Engine("./Levels", gui, gui);
		try
		{
			engine.run();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}