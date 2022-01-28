package task3;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RaceTest {

    @Test
    void getPlaces() {
        HashMap<Integer,String> places=new HashMap<>();
        places.put(1,"Kavindu");
        places.put(2,"Hamilton");
        Race race=new Race(places);
        assertEquals("Kavindu",race.getPlaces().get(1));
        System.out.println(places);
    }
}
