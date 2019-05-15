/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdu.lafis17.netbeanslab.common.data.entityparts;

import sdu.lafis17.netbeanslab.common.data.Entity;
import sdu.lafis17.netbeanslab.common.data.GameData;



/**
 *
 * @author Alexander
 */
public interface EntityPart {
    void process(GameData gameData, Entity entity);
}
