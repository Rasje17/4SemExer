package sem4.common.services;

import sem4.common.data.GameData;
import sem4.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService  {
        void process(GameData gameData, World world);
}
