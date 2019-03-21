package sem4.common.services;

import sem4.common.data.GameData;
import sem4.common.data.World;

public interface IEntityProcessingService {

    void process(GameData gameData, World world);
}
