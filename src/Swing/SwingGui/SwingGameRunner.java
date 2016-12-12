package Swing.SwingGui;

import Model.Engine;

class SwingGameRunner
{
	public static void main(String[] args)
	{
		SwingGUI gui = new SwingGUI();
		Engine engine = new Engine(gui, gui);
		try
		{
			engine.run();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}