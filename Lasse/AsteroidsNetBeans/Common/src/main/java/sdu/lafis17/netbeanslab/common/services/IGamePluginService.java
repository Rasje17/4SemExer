package sdu.lafis17.netbeanslab.common.services;

import sdu.lafis17.netbeanslab.common.data.GameData;
import sdu.lafis17.netbeanslab.common.data.World;



public interface IGamePluginService {
    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}
