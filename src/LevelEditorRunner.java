import Model.Point;
import SwingGui.LevelEditor.LevelEditor;
import SwingGui.LevelEditor.LevelEditorListener;
import SwingGui.LevelEditor.LevelEditorWindow;

public class LevelEditorRunner
{
	public static void main(String[] args) throws InterruptedException
	{
		LevelEditorWindow window = new LevelEditorWindow("Level Editor");
		LevelEditorListener listener = new LevelEditorListener();
		LevelEditor levelEditor = new LevelEditor();
		window.setSize(500, 500);
		window.setUndecorated(true);
		window.addKeyListener(listener);
		window.addMouseListener(listener);
		window.setVisible(true);

		while (true)
		{
			if (listener.needToSet)
			{
				levelEditor.setCell(getCellLocation(listener.getMouseLocation(), LevelEditorWindow.CELL_SIZE),
						listener.getCurrentSelectedCellType());
				listener.needToSet = false;
			}
			window.updateField(window.getGraphics(), levelEditor);
			Thread.sleep(100);
		}
	}

	private static Point getCellLocation(java.awt.Point p, int cellSize)
	{
//		return new Point(0, 0);
		Point point = new Point(p.x % cellSize, p.y % cellSize);
		return point;
	}
}
