package Swing.SwingGui;

import Infrastructure.LevelRepo;
import Model.Cells.BaseCell;
import Model.Level;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

class LevelWindow extends JFrame
{
	private static final int CELL_SIZE = 16;
	final JPanel panel;
	SwingKeyListener listener;
	private Level level;

	LevelWindow(String title)
	{
		super(title);
		panel = new JPanel()
		{
			@Override
			public void paintComponent(Graphics g)
			{
				if (level != null)
				{
					SwingPainterVisitor v = new SwingPainterVisitor(g, CELL_SIZE);
					for (int x = 0; x < level.getFieldWidth(); x++)
						for (int y = 0; y < level.getFieldHeight(); y++)
						{
							BaseCell c = level.getFieldCell(x, y);
							c.acceptVisitor(v, x, y);
						}
				}
			}
		};
		add(panel);
		initListener();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public Level getLevel()
	{
		return level;
	}

	public void setLevel(Level level)
	{
		this.level = level;
		setSize(level.getFieldWidth() * CELL_SIZE, level.getFieldHeight() * CELL_SIZE);
	}

	String showLevelSelectingDialog(LevelRepo repo)
	{
		setVisible(false);
		JFileChooser chooser = new JFileChooser(repo.getLevelsDirectory());
		chooser.setDialogTitle("Select Level File");
		chooser.setFileFilter(new FileNameExtensionFilter("level files (*.level)", "level"));
		chooser.showDialog(null, "Chose Level");
		setVisible(true);
		return chooser.getSelectedFile().getName();
	}

	private void initListener()
	{
		listener = new SwingKeyListener();
		addKeyListener(listener);
	}

	void updateGameEnd()
	{
		JOptionPane.showMessageDialog(null, "Game Over", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
	}
}
