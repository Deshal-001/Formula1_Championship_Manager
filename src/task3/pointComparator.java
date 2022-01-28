/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package task3;

import java.util.Comparator;

/**
 *
 * @author kavindudesilva
 */
public class pointComparator implements Comparator <Formula1Driver> {

    @Override
    public int compare(Formula1Driver o1, Formula1Driver o2) {
       if( o1.getTotalPoints()==o2.getTotalPoints())  {
               return 0;  }
            else if( o1.getTotalPoints()>o2.getTotalPoints())  {
                return 1;  }
            else  {
                return -1;  }
    }
    
}
