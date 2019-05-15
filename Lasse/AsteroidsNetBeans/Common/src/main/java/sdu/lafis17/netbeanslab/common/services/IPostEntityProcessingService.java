package sdu.lafis17.netbeanslab.common.services;

import sdu.lafis17.netbeanslab.common.data.GameData;
import sdu.lafis17.netbeanslab.common.data.World;



/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService  {
        void process(GameData gameData, World world);
}
