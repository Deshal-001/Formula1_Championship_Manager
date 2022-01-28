/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package task3;


import java.io.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author kavindudesilva
 */
public class Formula1ChampionshipManager implements ChampionshipManager {
    private int numberofDrivers;
    private ArrayList<Formula1Driver> players;
    private ArrayList<String> teams;
    private HashMap<Integer, String> places = new HashMap<>();
    HashMap<String, HashMap> raceData = new HashMap<>();
    HashMap<LocalDateTime, HashMap> SeasonRaceData = new HashMap<>();

    Formula1ChampionshipManager() {
    }




    public void menu() {
        System.out.println("*".repeat(100) + "\n" + "*>" + " ".repeat(34) + "Formula1 Championship Manager" + " ".repeat(33) + "<*" + "\n" + "*>" + " ".repeat(44) + " Task 2 " + " ".repeat(44) + "<*");
        System.out.println("*".repeat(100));


        try {
            Read();
            boolean done = true;
            while (done) {

                Scanner userInput = new Scanner(System.in);
                System.out.println("\n" + """ 
                        _____MENU______\040\040\040
                         1) Create a new driver
                         2) Delete a driver
                         3) Change the driver (existing constructor team)
                         4) Display the various statistics
                         5) Formula 1 Driver Table
                         6) Add a race
                         7) Saving in a file
                         8) Read
                         9) Exit""");
                System.out.println("Enter Number :");
                int input = userInput.nextInt();

                switch (input) {
                    case 1 -> creataNewDriver();
                    case 2 -> deleteDriver();
                    case 3 -> changeTheDriver();
                    case 4 -> displayStatics();
                    case 5 -> displayDriverTable();
                    case 6 -> addRace();
                    case 7 -> saveData();
                    case 8 -> Read();
                    case 9 -> done = false;
                    default -> System.out.println("Thank You !");
                }
            }
        } catch (Exception e) {
            System.err.println("Invalid Order !");
        }


    }

    @Override
    public Integer getnumberofDrivers() {
        return numberofDrivers;
    }

    public void setnumberofDrivers(int numberDrivers) {
        this.numberofDrivers = numberDrivers;
        setPlayers();
        setTeams(numberDrivers);
    }

    private void creataNewDriver() {
        try {

            if (getPlayers().size() < getnumberofDrivers()) {
                getPlayers().add(new Formula1Driver(getTeams()));

                for (int i = 0; i < getPlayers().size(); i++) {
                    if (getPlayers().get(i).getNameOfTheDriver() == null || getPlayers().get(i).getLocation() == null) {  //find is there a null value in player
                        getPlayers().remove(i); //remove null players
                    }

                }

            } else {
                System.out.println("Players Full !");

            }


        } catch (Exception e) {
            System.err.println("Invalid input !");
        }


    }

    private void deleteDriver() {

        try {
            Scanner input = new Scanner(System.in);

            System.out.println("Players");
            for (int i = 0; i < getPlayers().size(); i++) {
                System.out.println(i + 1 + " ->" + getPlayers().get(i).getNameOfTheDriver());

            }
            System.out.println("Enter player number to delete : ");
            int userInput = input.nextInt();
            System.out.println(getPlayers().get(userInput - 1).getNameOfTheDriver() + " has been REMOVED !");

            getTeams().remove(getPlayers().get(userInput - 1).getTeam());
            getPlayers().remove(userInput - 1);

        } catch (Exception e) {
            System.out.println("Invalid Number !");
        }

    }

    private void changeTheDriver() {

        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter team name :");
            String teamName = input.nextLine();

            if (getTeams().contains(teamName)) {

                for (int i = 0; i < getPlayers().size(); i++) {
                    if (getPlayers().get(i).getTeam().equals(teamName.toLowerCase())) {
                        System.out.println(" Enter new player details below :\n\n");
                        Scanner inputName = new Scanner(System.in);
                        System.out.print("Enter player name : ");
                        String name = inputName.nextLine();
                        getPlayers().get(i).setNameOfTheDriver(name);
                        Scanner inputLocation = new Scanner(System.in);
                        System.out.print("Enter player location : ");
                        String userlocation = inputLocation.nextLine();
                        getPlayers().get(i).setLocation(userlocation);
                        Scanner inputRaces = new Scanner(System.in);
                        System.out.print("Enter player participated races : ");
                        Integer races = inputRaces.nextInt();
                        getPlayers().get(i).setNumberofRacesParticipated(races);
                        getPlayers().get(i).setTotalPlaces();
                        getPlayers().get(i).setTotalPoints(getPlayers().get(i).getTotalPlaces());

                        System.out.println("Player was added successfully !");


                    }

                }


            } else {
                System.out.println("Your team name is invalid !");
            }

        } catch (Exception e) {
            System.err.println("Invalid input !");
        }

    }

    private void displayStatics() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the Player Name :");
            String userInput = input.nextLine();
            boolean done = true;
            int index = 0;
            for (int i = 0; i < getPlayers().size(); i++) {

                if (getPlayers().get(i).getNameOfTheDriver().equals(userInput.toLowerCase())) {
                    index = i;
                    done = false;

                }

            }

            if (!done || getTeams().contains(userInput)) {
                System.out.println(getPlayers().get(index).viewPlayer());

            } else {
                System.out.println("Invalid Name/Team");
            }

        } catch (Exception e) {
            System.out.println("Invalid input !");
        }
    }


    public void displayDriverTable() {
        try {

            Collections.sort(getPlayers(), new firstPositionsComparator());
            Collections.sort(getPlayers(), new pointComparator());

            Formatter fmt = new Formatter();

            System.out.println(fmt.format("%5s %15s %15s %15s %15s+\n", "Place", "Name", "Team", "NoOfRaces", "Total"));


            for (int i = 0; i < getPlayers().size(); i++) {

                System.out.printf("%2s %15s %15s %15s %15s\n", i + 1, getPlayers().get(i).getNameOfTheDriver(), getPlayers().get(i).getTeam(), getPlayers().get(i).getNumberofRacesParticipated(), getPlayers().get(i).getTotalPoints());

            }
        } catch (Exception e) {
            System.err.println("Invalid input !");
        }


    }

    public void addRace() {

        new Race().addRace(getPlayers().size(), getPlayers(), getRaceData());


    }


    public void saveData() throws IOException {
        try {


            FileOutputStream fileOut = new FileOutputStream("savedData.ser");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(getPlayers());
            objOut.close();
            fileOut.close();
            System.out.println("Serialization done !");
        } catch (Exception ex) {
            System.out.println("File not found");
        }


        File data = new File("fromula1Data.csv");  //set file path
        File allData = new File("allData.csv");   //set file path

        if (data.exists() && allData.exists()) {

            try (FileWriter fr = new FileWriter(data, false)) {


                for (int i = 0; i < getPlayers().size(); i++) {

                    Integer v0 = 0, v1 = 0, v2 = 0, v3 = 0, v4 = 0, v5 = 0, v6 = 0, v7 = 0, v8 = 0, v9 = 0;
                    for (int j = 0; j < 10; j++) {
                        switch (j) {
                            case 0 -> v0 = getPlayers().get(i).getStatistics().get(0);
                            case 1 -> v1 = getPlayers().get(i).getStatistics().get(1);
                            case 2 -> v2 = getPlayers().get(i).getStatistics().get(2);
                            case 3 -> v3 = getPlayers().get(i).getStatistics().get(3);
                            case 4 -> v4 = getPlayers().get(i).getStatistics().get(4);
                            case 5 -> v5 = getPlayers().get(i).getStatistics().get(5);
                            case 6 -> v6 = getPlayers().get(i).getStatistics().get(6);
                            case 7 -> v7 = getPlayers().get(i).getStatistics().get(7);
                            case 8 -> v8 = getPlayers().get(i).getStatistics().get(8);
                            case 9 -> v9 = getPlayers().get(i).getStatistics().get(9);
                            default -> throw new IllegalStateException("Unexpected value: " + j);
                        }

                    }

                    fr.write(i + 1 + "," + getPlayers().get(i).getNameOfTheDriver().toString() + "," + getPlayers().get(i).getLocation() + "," + getPlayers().get(i).getTeam() + "," + getPlayers().get(i).getNumberofRacesParticipated() + "," + getPlayers().get(i).getTotalPoints() + "," + v0.toString() + "," + v1.toString() + "," + v2.toString() + "," + v3.toString() + "," + v4.toString() + "," + v5.toString() + "," + v6.toString() + "," + v7.toString() + "," + v8.toString() + "," + v9.toString());

                    fr.write("\n");

                }


            }
        } else {
            data.createNewFile();
            allData.createNewFile();
            System.out.println("File has been created!");
            System.out.println("Please save data again !");
        }
        saveRace();


    }

    public void Read() throws IOException {
        getPlayers().clear();
        getTeams().clear();

        try {
            readAllraces();
            ReadDriversTable();


        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }


    public ArrayList<Formula1Driver> getPlayers() {
        return players;
    }

    public void setPlayers() {
        this.players = new ArrayList<>();
    }


    public ArrayList<String> getTeams() {
        return teams;
    }

    /**
     * @param size
     */
    public void setTeams(int size) {
        this.teams = new ArrayList<>(size);
    }

    /**
     * @return the raceData
     */
    public Map<String, HashMap> getRaceData() {
        return raceData;
    }

    /**
     * @return the places
     */
    public HashMap<Integer, String> getPlaces() {
        return places;
    }

    /**
     * @param places the places to set
     */
    public void setPlaces(HashMap<Integer, String> places) {
        this.places = places;
    }

    void saveRace() {

        try {
            FileOutputStream fileOut = new FileOutputStream("AllData.ser");
            SeasonRaceData.put(LocalDateTime.now(), places);

            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(SeasonRaceData);
            objOut.close();
            fileOut.close();
            System.out.println("Serialization done !");


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    void readAllraces() throws IOException {
        try {


            HashMap<LocalDateTime, HashMap> arrays = null;
            FileInputStream fileIn = new FileInputStream("AllData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            try {
                arrays = (HashMap<LocalDateTime, HashMap>) in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            in.close();
            fileIn.close();

            SeasonRaceData = arrays;

            System.out.println(SeasonRaceData);
        } catch (Exception e) {
            System.out.println("Not Founded !");
        }

    }

    void ReadDriversTable() throws IOException{
        try {

            Formula1Driver formula1Driver = null;
            ArrayList<Formula1Driver> array = null;
            FileInputStream fileIn = new FileInputStream("savedData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            try {
                array = (ArrayList<Formula1Driver>) in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            in.close();
            fileIn.close();

            players = array;

            for (int i = 0; i < players.size(); i++) {
                getTeams().add(players.get(i).getTeam());


            }

        }catch (Exception e){
            System.out.println("Not Founded");
            ReadTextFile();}
    }

    void ReadTextFile() throws IOException{
        String path = "fromula1Data.csv";
        String allDatapath = "allData.csv";
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            int no = 0;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                HashMap<Integer, Integer> stats = new HashMap<>();
                for (int i = 0; i < 10; i++) {
                    stats.put(i, Integer.parseInt(values[i + 6]));

                }

                getPlayers().add(no, new Formula1Driver(values[1], values[2], values[3], Integer.parseInt(values[5]), Integer.parseInt(values[4]), stats));
                getTeams().add(values[3]);

                no += 1;


                BufferedReader br2 = new BufferedReader(new FileReader(allDatapath));

                while ((line = br2.readLine()) != null) {
                    String[] values2 = line.split(",");
                    HashMap<Integer, String> newPlaces = new HashMap<>();


                    for (int j = 1; j < getPlayers().size() + 1; j++) {
                        newPlaces.put(j, values2[j]);

                    }
                    places = newPlaces;
                    SeasonRaceData.put(LocalDateTime.parse(values2[0]), places);

                }

            }}catch (Exception ex){
            System.out.println("File not Founded !");
        }



    }
}


