package Infrastructure;

import Model.Direction;

public interface IGameController
{
	Direction getNewDirection();
	int getNewViewCoord();
}
