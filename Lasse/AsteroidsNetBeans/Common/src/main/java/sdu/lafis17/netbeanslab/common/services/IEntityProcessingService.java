package sdu.lafis17.netbeanslab.common.services;

import sdu.lafis17.netbeanslab.common.data.GameData;
import sdu.lafis17.netbeanslab.common.data.World;



public interface IEntityProcessingService {

    void process(GameData gameData, World world);
}
