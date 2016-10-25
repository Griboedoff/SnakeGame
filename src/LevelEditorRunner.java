import Infrastructure.LevelRepo;
import Model.Direction;
import Model.Level;
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
			update(levelEditor, listener, window);
			Thread.sleep(100);
		}
	}

	private static void update(LevelEditor levelEditor, LevelEditorListener listener, LevelEditorWindow window)
    {
        if (listener.needToSet)
        {
            levelEditor.setCell(getCellLocation(listener.getMouseLocation(), LevelEditorWindow.CELL_SIZE),
                    listener.getCurrentSelectedCellType());
            listener.needToSet = false;
        }
        if (listener.needToChangeSize)
        {
            levelEditor.changeSize(inputPoint());
            listener.needToChangeSize = false;
        }
        if (listener.levelDone)
        {
            finishAndSave(levelEditor, listener);
        }
        window.updateField(window.getGraphics(), levelEditor);
    }

	private static void finishAndSave(LevelEditor levelEditor, LevelEditorListener listener)
    {
        levelEditor.setDirection(listener.direction);
        Level level = Level.fromLevelEditor(levelEditor);
        LevelRepo levelRepo = new LevelRepo("/");
        while (true)
        {
            try
            {
                level.setName(inputName());
                levelRepo.saveLevelToFile(level);
                break;
            }
            catch (Exception e)
            {
                showNameErrorMessage(level.getName());
            }
        }
    }

	private static String inputName()
    {
        //Окошко для ввода имени.
        return "name";
    }

    private static void showNameErrorMessage(String name)
    {
        //Окошко, которое говорит, что ты мудак и такое имя уже есть.
    }

    private static Point inputPoint()
    {
        //Окошко слезно просит два целых числа больше одного
        return new Point(12, 12);
    }

	private static Point getCellLocation(java.awt.Point p, int cellSize)
	{
		Point point = new Point(p.x % cellSize, p.y % cellSize);
		return point;
	}
}
