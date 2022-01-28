/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package task3;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author kavindudesilva
 */
public interface ChampionshipManager {

    void displayDriverTable();

    void addRace();

    void Read() throws IOException;

    void saveData() throws IOException;

    Integer getnumberofDrivers();
    // void setnumberofDrivers();
}
