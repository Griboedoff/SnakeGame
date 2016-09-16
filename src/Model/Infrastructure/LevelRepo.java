package Model.Infrastructure;

import Model.Level;
import com.google.gson.Gson;

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

	public Level getLevelFromJsonFile(String levelName)
	{
		File[] levelsFiles = levelsDir.listFiles();
		if (levelsFiles != null)
			for (File levelFile : levelsFiles)
				if (Objects.equals(levelFile.getName(), levelName + ".txt"))
					try (BufferedReader br = new BufferedReader(new FileReader(levelFile)))
					{
						return new Gson().fromJson(br, Level.class);
					} catch (IOException e)
					{
						e.printStackTrace();
					}
		throw new IllegalArgumentException(String.format("%s not found", levelName));
	}

	public boolean saveLevel(Level level)
	{

		String fileName = level.getName() + ".json";
		Path path = Paths.get(levelsDir.getAbsolutePath(), fileName);
		try
		{
			Files.createFile(path);
		} catch (Exception e)
		{
			System.err.println("already exists: " + e.getMessage());
			return false;
		}

		try (FileWriter writer = new FileWriter(path.toString()))
		{
			writer.write(new Gson().toJson(level));
		} catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}