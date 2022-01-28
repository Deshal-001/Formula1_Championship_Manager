package task3;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Formula1DriverTest {
    private Formula1Driver formula1Driver=new Formula1Driver("Kavindu","SL","Ferrari",1);

    @Test
    void viewPlayer() {
            assertEquals("Kavindu",formula1Driver.getNameOfTheDriver());
            assertEquals("Ferrari",formula1Driver.getTeam());
            assertEquals("SL",formula1Driver.getLocation());
    }

    @Test

    void testDriverStatics(){
        HashMap<Integer,Integer> stats=new HashMap<>();

        stats.put(0,1);
        formula1Driver.setStatistics(stats);
        formula1Driver.setTotalPoints(stats);
        assertEquals(25,formula1Driver.getTotalPoints());

        stats.put(1,1);
        formula1Driver.setStatistics(stats);
        formula1Driver.setTotalPoints(stats);
        assertEquals(25+18,formula1Driver.getTotalPoints());

        stats.put(2,1);
        formula1Driver.setStatistics(stats);
        formula1Driver.setTotalPoints(stats);
        assertEquals(25+18+15,formula1Driver.getTotalPoints());

        stats.put(3,1);
        formula1Driver.setStatistics(stats);
        formula1Driver.setTotalPoints(stats);
        assertEquals(25+18+15+12,formula1Driver.getTotalPoints());

        stats.put(4,1);
        formula1Driver.setStatistics(stats);
        formula1Driver.setTotalPoints(stats);
        assertEquals(25+18+15+12+10,formula1Driver.getTotalPoints());

        stats.put(5,1);
        formula1Driver.setStatistics(stats);
        formula1Driver.setTotalPoints(stats);
        assertEquals(25+18+15+12+10+8,formula1Driver.getTotalPoints());

        stats.put(6,1);
        formula1Driver.setStatistics(stats);
        formula1Driver.setTotalPoints(stats);
        assertEquals(25+18+15+12+10+8+6,formula1Driver.getTotalPoints());

        stats.put(7,1);
        formula1Driver.setStatistics(stats);
        formula1Driver.setTotalPoints(stats);
        assertEquals(25+18+15+12+10+8+6+4,formula1Driver.getTotalPoints());

        stats.put(8,1);
        formula1Driver.setStatistics(stats);
        formula1Driver.setTotalPoints(stats);
        assertEquals(25+18+15+12+10+8+6+4+2,formula1Driver.getTotalPoints());

        stats.put(9,1);
        formula1Driver.setStatistics(stats);
        formula1Driver.setTotalPoints(stats);
        assertEquals(25+18+15+12+10+8+6+4+2+1,formula1Driver.getTotalPoints());


    }
}