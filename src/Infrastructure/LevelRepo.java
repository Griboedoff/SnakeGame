package Infrastructure;

import Model.Level;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class LevelRepo
{
	public void setLevelsDir(File levelsDir)
	{
		if (!levelsDir.isDirectory())
			throw new IllegalArgumentException(String.format("%SaveListener is not directory", levelsDir.getAbsolutePath()));
		this.levelsDir = levelsDir;
	}

	private File levelsDir;

	public LevelRepo(String path)
	{
		levelsDir = new File(path);
	}

	public LevelRepo(File levelsDir)
	{
		this.levelsDir = levelsDir;
	}

	public Level getLevelFromFile(String levelName)
	{
		File[] levelsFiles = levelsDir.listFiles();
		if (levelsFiles != null)
			for (File levelFile : levelsFiles)
				if (Objects.equals(levelFile.getName(), levelName))
					try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(levelFile)))
					{
						return (Level) objectInputStream.readObject();
					} catch (IOException | ClassNotFoundException e)
					{
						throw new RuntimeException(e);
					}
		throw new IllegalArgumentException(String.format("%SaveListener not found", levelName));
	}

	public boolean saveLevelToFile(Level level)
	{
		String fileName = level.getName() + ".level";
		Path path = Paths.get(levelsDir.getAbsolutePath(), fileName);
//		try
//		{
//			Files.createFile(path);
//		} catch (Exception e)
//		{
//			System.err.println("already exists: " + e.getMessage());
//			return false;
//		}

		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path.toString())))
		{
			objectOutputStream.writeObject(level);
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		return true;
	}
}