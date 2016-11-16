package Infrastructure;

import Model.Level;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class LevelRepo
{
	public void setLevelsDirectory(File levelsDirectory)
	{
		if (!levelsDirectory.isDirectory())
			throw new IllegalArgumentException(String.format("%SaveListener is not directory", levelsDirectory.getAbsolutePath()));
		this.levelsDirectory = levelsDirectory;
	}

	private File levelsDirectory;

	public LevelRepo(String path)
	{
		levelsDirectory = new File(path);
	}

	public LevelRepo(File levelsDirectory)
	{
		this.levelsDirectory = levelsDirectory;
	}

	public Level getLevelFromFile(String levelName)
	{
		File[] levelsFiles = levelsDirectory.listFiles();
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

	public File getLevelsDirectory()
	{
		return levelsDirectory;
	}

	public boolean saveLevelToFile(Level level)
	{
		String fileName = level.getName() + ".level";
		Path path = Paths.get(levelsDirectory.getAbsolutePath(), fileName);

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