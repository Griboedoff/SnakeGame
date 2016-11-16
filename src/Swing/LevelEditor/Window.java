package Swing.LevelEditor;

import Infrastructure.LevelRepo;
import Model.Cells.*;
import Model.Direction;
import Model.Level;
import Swing.SwingGui.PainterVisitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.File;

public class Window extends JFrame
{
	private static final int CELL_SIZE = 16;
	private JPanel root;
	private JButton saveButton;
	private JButton Validate;
	private JButton snakeCellButton;
	private JButton wallCellButton;
	private JButton emptyCellButton;
	private JButton foodCellButton;
	private JButton resizeFieldButton;
	private JSpinner xValueSpinner;
	private JSpinner yValueSpinner;
	private JPanel field;
	private JComboBox directionBox;
	private JTextField levelNameField;
	private JButton portalCellButton;
	private JButton poisonCellButton;
	private JButton reverseCellButton;

	private LevelEditor editor;
	private Class<?> selectedCellType;
	private LevelRepo levelRepo;

	private Window(File levelFile)
	{
		getLevelEditor(levelFile);

		setSize(500, 500);

		initializeListeners();

		levelNameField.setText(editor.getName());
		xValueSpinner.setValue(editor.getField().getWidth());
		yValueSpinner.setValue(editor.getField().getHeight());
	}

	private void getLevelEditor(File levelFile)
	{
		levelRepo = new LevelRepo(levelFile.getParentFile());
		if (levelFile.length() != 0)
			try
			{
				editor = new LevelEditor(levelRepo.getLevelFromFile(levelFile.getName()));
			} catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, "Can't open file " + levelFile, "InfoBox", JOptionPane.INFORMATION_MESSAGE);
				dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
		else
			editor = new LevelEditor();
	}

	private void initializeListeners()
	{
		snakeCellButton.addActionListener(e -> handleButtonClick(SnakeCell.class, snakeCellButton));
		wallCellButton.addActionListener(e -> handleButtonClick(WallCell.class, wallCellButton));
		emptyCellButton.addActionListener(e -> handleButtonClick(EmptyCell.class, emptyCellButton));
		foodCellButton.addActionListener(e -> handleButtonClick(FoodCell.class, foodCellButton));
		poisonCellButton.addActionListener(e -> handleButtonClick(PoisonCell.class, poisonCellButton));
		portalCellButton.addActionListener(e -> handleButtonClick(PortalCell.class, portalCellButton));
		reverseCellButton.addActionListener(e -> handleButtonClick(ReverseCell.class, reverseCellButton));
		resizeFieldButton.addActionListener(e ->
		{
			try
			{
				this.editor.changeSize((int) xValueSpinner.getValue(), (int) yValueSpinner.getValue());
				field.repaint();
			} catch (IllegalArgumentException ex)
			{
				JOptionPane.showMessageDialog(null, ex, "InfoBox", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		Validate.addActionListener(e ->
		{
			fillFields();
			JOptionPane.showMessageDialog(null, this.editor.validate(), "InfoBox", JOptionPane.INFORMATION_MESSAGE);
		});
		saveButton.addActionListener(e ->
		{
			try
			{
				fillFields();
				Level level = Level.fromLevelEditor(this.editor);
				levelRepo.saveLevelToFile(level);

				JOptionPane.showMessageDialog(null, "Success", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			} catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage(), "InfoBox", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		field.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{

			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				editor.setCell(getCellLocationFromAwtPoint(e.getPoint()), selectedCellType);
				field.repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{

			}

			@Override
			public void mouseEntered(MouseEvent e)
			{

			}

			@Override
			public void mouseExited(MouseEvent e)
			{

			}
		});
	}

	private void fillFields()
	{
		editor.setName(levelNameField.getText());
		editor.setDirection(Direction.fromString(directionBox.getSelectedItem()));
	}

	private void resetAllButtonsColor()
	{
		snakeCellButton.setEnabled(true);
		wallCellButton.setEnabled(true);
		emptyCellButton.setEnabled(true);
		foodCellButton.setEnabled(true);
		poisonCellButton.setEnabled(true);
		portalCellButton.setEnabled(true);
		reverseCellButton.setEnabled(true);
	}

	private void handleButtonClick(Class<?> cellClass, JButton button)
	{
		selectedCellType = cellClass;
		resetAllButtonsColor();
		button.setEnabled(false);
	}

	private Model.Point getCellLocationFromAwtPoint(java.awt.Point awtPoint)
	{
		return new Model.Point(awtPoint.x / CELL_SIZE, awtPoint.y / CELL_SIZE);
	}

	private void createUIComponents()
	{
		field = new JPanel()
		{
			@Override
			public void paintComponent(Graphics g)
			{
				PainterVisitor v = new PainterVisitor(g, CELL_SIZE);
				for (int x = 0; x < editor.getField().getWidth(); x++)
					for (int y = 0; y < editor.getField().getHeight(); y++)
					{
						BaseCell c = editor.getCell(x, y);
						c.acceptVisitor(v, x, y);
						g.setColor(Color.BLACK);
						g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
					}
			}
		};
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Window");
		Window window = new Window(selectLevelFile());
		frame.setContentPane(window.root);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private static File selectLevelFile()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.showDialog(null, "Choose file");
		return chooser.getSelectedFile();
	}
}
