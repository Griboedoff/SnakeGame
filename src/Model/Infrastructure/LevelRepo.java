package Model.Infrastructure;

import Model.Level;

import java.io.File;
import java.util.Objects;

public class LevelRepo {
	private File levelsDir;

	public LevelRepo(String path) {
		levelsDir = new File(path);
		if (!levelsDir.isDirectory())
			throw new IllegalArgumentException(String.format("%s is not directory", path));
	}

	public Level getLevel(String levelName) {
		File[] levelsFiles = levelsDir.listFiles();
		if (levelsFiles != null)
			for (File levelFile : levelsFiles) {
				if (Objects.equals(levelFile.getName(), levelName + ".txt")) {
					return Level.CreateFromFile(levelFile);
				}
			}
		throw new IllegalArgumentException(String.format("%s not found", levelName));
	}
}
