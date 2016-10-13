package Infrastructure;

import Model.Level;

public interface IRenderer
{
	void renderLevel(Level level);
	void renderGameEnd(boolean isCompleted);
}
