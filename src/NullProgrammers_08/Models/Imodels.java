/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NullProgrammers_08.Models;

import java.util.Dictionary;
/**
 *
 * @author DRONE003
 */
public interface Imodels {
    String[] loadAllValues();
    <T extends Imodels> T loadClass(T classT);
    Imodels loadModels(Dictionary dict);
    String[] loadAllNames();
    Object loadValues(String name);
}
