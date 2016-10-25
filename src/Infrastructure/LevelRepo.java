package Infrastructure;

import Model.Level;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class LevelRepo
{
	private File levelsDir;

	public LevelRepo(String path)
	{
		levelsDir = new File(path);
		if (!levelsDir.isDirectory())
			throw new IllegalArgumentException(String.format("%s is not directory", path));
	}

	public Level getLevelFromFile(String levelName) throws IOException, ClassNotFoundException
	{
		File[] levelsFiles = levelsDir.listFiles();
		if (levelsFiles != null)
			for (File levelFile : levelsFiles)
				if (Objects.equals(levelFile.getName(), levelName + ".level"))
					try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(levelFile)))
					{
						return (Level) objectInputStream.readObject();
					}
		throw new IllegalArgumentException(String.format("%s not found", levelName));
	}

	public boolean saveLevelToFile(Level level) throws IOException
	{
		String fileName = level.getName() + ".level";
		Path path = Paths.get(levelsDir.getAbsolutePath(), fileName);
		try
		{
			Files.createFile(path);
		} catch (Exception e)
		{
			System.err.println("already exists: " + e.getMessage());
			return false;
		}

		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path.toString())))
		{
			objectOutputStream.writeObject(level);
		}
		return true;
	}
}