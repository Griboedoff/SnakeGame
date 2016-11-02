import Infrastructure.LevelRepo;
import Model.Level;
import Model.Point;
import SwingGui.LevelEditor.LevelEditor;
import SwingGui.LevelEditor.LevelEditorListener;
import SwingGui.LevelEditor.LevelEditorWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class LevelEditorRunner
{
	public static void main(String[] args) throws InterruptedException
	{
		LevelEditorWindow window = new LevelEditorWindow("Level Editor");
		LevelEditorListener listener = new LevelEditorListener();
		LevelEditor levelEditor = new LevelEditor();
		window.addKeyListener(listener);
		window.addMouseListener(listener);

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
			levelEditor.changeSize(getNewSize());
			listener.needToChangeSize = false;
		}

		if (listener.levelDone)
			finishAndSave(levelEditor, listener);

		window.updateField(window.getGraphics(), levelEditor);
	}

	private static void finishAndSave(LevelEditor levelEditor, LevelEditorListener listener)
	{
		levelEditor.setDirection(listener.direction);
		levelEditor.setName(getLevelName());
		saveLevel(Level.fromLevelEditor(levelEditor));
	}


	private static void saveLevel(Level level)
	{
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.setVisible(true);

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Save file");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);


		if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION)
		{
			LevelRepo repo = new LevelRepo(chooser.getCurrentDirectory());
			repo.saveLevelToFile(level);
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			frame.setVisible(false);
		}

		frame.add(chooser);
	}

	private static String getLevelName()
	{
		JFrame frame = new JFrame();
		frame.setSize(200, 50);
		frame.setVisible(true);
		frame.setLayout(new GridLayout(1, 2));

		JTextField textField = new JTextField();
		frame.add(textField);

		JButton saveButton = new JButton("Save");

		SaveListener saveListener = new SaveListener(textField, frame);
		saveButton.addActionListener(saveListener);
		frame.add(saveButton);

		while (saveListener.getName() == null)
		{
			try
			{
				Thread.sleep(100);
			} catch (InterruptedException e)
			{
				throw new RuntimeException(e);
			}
		}
		return saveListener.getName();
	}

	private static class SaveListener implements ActionListener
	{
		private JTextField field;
		private JFrame frame;
		private String name;

		String getName()
		{
			return name;
		}

		SaveListener(JTextField field, JFrame frame)
		{
			this.field = field;
			this.frame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			name = field.getText();
			frame.setVisible(false);
		}
	}

	private static Point getNewSize()
	{
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setLayout(new GridLayout(3, 1));

		JSpinner spinnerX = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
		JSpinner spinnerY = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
		JButton saveSize = new JButton("Save");

		SpinnerListener listener = new SpinnerListener(spinnerX, spinnerY);

		saveSize.addActionListener(listener);

		frame.add(spinnerX);
		frame.add(spinnerY);
		frame.add(saveSize);

		while (listener.value.isPositive())
		{
			try
			{
				Thread.sleep(100);
			} catch (InterruptedException e)
			{
				throw new RuntimeException(e);
			}
		}

		return listener.value;
	}

	private static class SpinnerListener implements ActionListener
	{
		public Point getValue()
		{
			return value;
		}

		private Point value;
		private JSpinner spinnerX;
		private JSpinner spinnerY;


		SpinnerListener(JSpinner spinnerX, JSpinner spinnerY)
		{
			this.spinnerX = spinnerX;
			this.spinnerY = spinnerY;
			this.value = new Point(0, 0);
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			value = new Point((int) spinnerX.getValue(), (int) spinnerY.getValue());
		}
	}

	private static Point getCellLocation(java.awt.Point p, int cellSize)
	{
		return new Point(p.x / cellSize, p.y / cellSize);
	}
}
