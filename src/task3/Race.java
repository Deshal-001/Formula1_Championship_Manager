package task3;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Race implements Serializable {
   private HashMap<Integer, String> places = new HashMap<>();

   Race() {
   }
   Race(HashMap<Integer, String> places){
      this.places=places;
   }


   public void addRace(int size, List<Formula1Driver> players, Map<String, HashMap> raceData) {
      HashMap<Integer, String> place = new HashMap<>();
      Scanner userInput = new Scanner(System.in);
      System.out.println("Add player position :");
      ArrayList<Integer> positions = new ArrayList<>();
      for (int i = 0; i < size; i++) {
         boolean done = true;
         while (done == true) {
            System.out.print(players.get(i).getNameOfTheDriver() + ": ");
            Integer input = userInput.nextInt();


            if (positions.contains(input)) {
               System.out.println("Position already assigned ");
            } else {

               positions.add(input);
               Integer oldValue = players.get(i).getStatistics().get(input - 1);


               Integer newValue = 1 + oldValue;
               players.get(i).getStatistics().replace(input - 1, newValue);
               players.get(i).getNumberofRacesParticipated();
               players.get(i).setTotalPoints(players.get(i).getTotalPlaces());
               place.put(input,players.get(i).getTeam() + " " +players.get(i).getNameOfTheDriver());
               done = false;


            }
         }

      }

      LocalDateTime myDateObj = LocalDateTime.now();

      DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy,MM,dd,HH,mm,ss");


      String formattedDate = myDateObj.format(myFormatObj);


      raceData.put(formattedDate, place);
      setPlaces(place);


      System.out.println("Race was added successfully !");


   }

   public Map<Integer, String> getPlaces() {
      return places;
   }

   public void setPlaces(HashMap<Integer, String> places) {
      this.places = places;
   }


}