/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package task3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author kavindudesilva
 */


public class Formula1Driver extends Driver implements Serializable {

    private int totalPoints;
    private HashMap<Integer, Integer> totalPlaces;


    Formula1Driver(ArrayList<String> teams) {
        super();
        creatPlayer(teams);

    }


    Formula1Driver(String name, String location, String team, int totalPoints, int numberofraces, HashMap<Integer, Integer> stats) {

        super(name, location, team, numberofraces, stats);
        this.totalPoints = totalPoints;
        this.totalPlaces = totalPlaces;

    }
    Formula1Driver(String name, String location, String team, int numberofRacesParticipated){
        super(name,location,team,numberofRacesParticipated);
    }


    public int getTotalPoints() {
        return totalPoints;
    }


    public void setTotalPoints(HashMap<Integer, Integer> statistic) {
        this.totalPoints = 0;
        int cal = 0;
        for (int i = 0; i < getStatistics().size(); i++) {
            if (getStatistics().get(i) != null) {
                switch (i) {
                    case 0 -> cal = getStatistics().get(i) * 25;
                    case 1 -> cal = getStatistics().get(i) * 18;
                    case 2 -> cal = getStatistics().get(i) * 15;
                    case 3 -> cal = getStatistics().get(i) * 12;
                    case 4 -> cal = getStatistics().get(i) * 10;
                    case 5 -> cal = getStatistics().get(i) * 8;
                    case 6 -> cal = getStatistics().get(i) * 6;
                    case 7 -> cal = getStatistics().get(i) * 4;
                    case 8 -> cal = getStatistics().get(i) * 2;
                    case 9 -> cal = getStatistics().get(i) * 1;
                }
                totalPoints += cal;

            }

        }
        this.totalPoints = totalPoints;
    }


    private void creatPlayer(ArrayList<String> teams) {
        Scanner inputTeam = new Scanner(System.in);
        System.out.print("Enter player team : ");
        String team = inputTeam.nextLine();
        boolean exist=true;
        for(String item : teams) {
            if (team.equalsIgnoreCase(item)) {
                exist = false;
                break;
            }
        }
        if (!exist) {
            System.out.println("The team already has a player");
        } else {
            setTeam(team);
            teams.add(team.toLowerCase());

            Scanner inputName = new Scanner(System.in);
            System.out.print("Enter player name : ");
            String name = inputName.nextLine();
            setNameOfTheDriver(name);
            Scanner inputLocation = new Scanner(System.in);
            System.out.print("Enter player location : ");
            String userLocation = inputLocation.nextLine();
            setLocation(userLocation);
            Scanner inputRaces = new Scanner(System.in);
            System.out.print("Enter player participated races : ");
            Integer races = inputRaces.nextInt();
            setNumberofRacesParticipated(races);
            setTotalPlaces();
            setTotalPoints(getTotalPlaces());
        }

    }


    String viewPlayer() {

        System.out.println("Player Name     : " + getNameOfTheDriver());
        System.out.println("Player Team     : " + getTeam());
        System.out.println("Player Location : " + getLocation());
        System.out.println("Player Races    : " + getNumberofRacesParticipated());
        System.out.println("Player Stats    : Total Points : " + getTotalPoints() + " Average :" + ((getTotalPoints() / (float) getNumberofRacesParticipated())));
        //set this to double

        return "";

    }


    public HashMap<Integer, Integer> getTotalPlaces() {
        return totalPlaces;
    }

    public void setTotalPlaces() {
        this.totalPlaces = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Scanner inputStatics = new Scanner(System.in);
            System.out.println("Enter player's " + (i + 1) + " places:");
            Integer statics = inputStatics.nextInt();
            totalPlaces.put(i, statics);

            setStatistics(totalPlaces);

        }
    }


}
