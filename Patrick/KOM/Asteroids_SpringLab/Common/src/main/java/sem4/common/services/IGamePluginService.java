package sem4.common.services;

import sem4.common.data.GameData;
import sem4.common.data.World;

public interface IGamePluginService {
    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}
