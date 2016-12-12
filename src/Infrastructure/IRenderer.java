package Infrastructure;

import Model.Level;

public interface IRenderer
{
	Level selectLevel(LevelRepo repo);

	void renderLevel(Level level);

	void renderGameEnd();
}
