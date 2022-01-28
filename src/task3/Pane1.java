package task3;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

public class Pane1 implements ActionListener {
    private JTable jt;
    private Object[][] playerData;
    private String[] columns;
    private Object[][] showDataRw;
    private String[] showDataCol;
    private JTable showJt;
    private final JButton addRace = new JButton("Add race");
    private final Formula1ChampionshipManager formula1ChampionshipManager;
    private final JButton button1 = new JButton("Sort by First Places");
    private final JButton button2 = new JButton("Sort by Total Points");
    private final JButton probButton = new JButton("Probability Button");

    Pane1(Formula1ChampionshipManager formula1ChampionshipManager) {
        this.formula1ChampionshipManager = formula1ChampionshipManager;
    }

    Component getPane1() {
        JPanel controls = new JPanel();
        controls.setBackground(Color.LIGHT_GRAY); //set background color as light gray
        Border border = BorderFactory.createLineBorder(Color.black);

        controls.setBorder(border);  //set created border
        setJt(); //call set jt method
        setAddDataTable(); //call setAddDataTable method
        jt.setRowHeight(20);  //set table raw height
        controls.add(new JScrollPane(jt));
        JScrollPane sp = new JScrollPane(showJt);
        jt.getTableHeader().setBackground(Color.decode("#e7ebe8")); //set table header
        showJt.getTableHeader().setBackground(Color.decode("#e7ebe8")); //set table header color
        sp.setBounds(240, 400, 10, 10);
        controls.add(sp);




        //set row height
        jt.setRowHeight(40);
        //set raw color
        setRawColor(jt,Color.LIGHT_GRAY);

        //add action listener
        addRace.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        probButton.addActionListener(this);
        addRace.setBounds(250, 40, 20, 20);
        controls.add(addRace);
        controls.add(button1);
        controls.add(button2);
        controls.add(probButton);
        return controls;
    }


    private void setJt() {
        try {
            formula1ChampionshipManager.setnumberofDrivers(100);
            //read saved file
            formula1ChampionshipManager.Read();

        } catch (Exception e) {
            System.err.println("Invalid Order !");
        }
        this.columns = new String[]{"Place", "Name", "Location", "Team", "Total", "First Places"};
        setColumns();  //call a method
        jt = new JTable(this.playerData, this.columns);  //add data
        jt.setBounds(30, 40, 200, 300);


    }

    void setColumns() {
        this.playerData = new Object[this.formula1ChampionshipManager.getPlayers().size()][6];
        //add data to the cols in table
        for (int i = 0; i < this.formula1ChampionshipManager.getPlayers().size(); i++) {
            this.playerData[i][0] = Integer.toString(i + 1);
            this.playerData[i][1] = this.formula1ChampionshipManager.getPlayers().get(i).getNameOfTheDriver();
            this.playerData[i][2] = this.formula1ChampionshipManager.getPlayers().get(i).getLocation();
            this.playerData[i][3] = this.formula1ChampionshipManager.getPlayers().get(i).getTeam();
            this.playerData[i][4] = Integer.toString(this.formula1ChampionshipManager.getPlayers().get(i).getTotalPoints());
            this.playerData[i][5] = Integer.toString(this.formula1ChampionshipManager.getPlayers().get(i).getStatistics().get(0));

        }
    }


    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addRace) {
            //set number of max-drivers
            formula1ChampionshipManager.setnumberofDrivers(100);
            try {
                //read all data
                formula1ChampionshipManager.Read();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            //call addRace method
            addRace(formula1ChampionshipManager);
            setColumns();
            //create table model and add model to the main table
            TableModel driverTableModel1 = new DefaultTableModel(this.playerData, this.columns);
            jt.setModel(driverTableModel1);
            setRawColor(jt,Color.LIGHT_GRAY);
            //set show data table cols
            setShowDataCols();
            TableModel driverTableModel = new DefaultTableModel(this.showDataRw, this.showDataCol);
            showJt.setModel(driverTableModel);


        }
        if (e.getSource() == button1) {
            //sort data by first positions
            Collections.sort(formula1ChampionshipManager.getPlayers(), new firstPositionsComparator());
            setColumns();
            //create table model and add model to the main table
            TableModel driverTableModel = new DefaultTableModel(this.playerData, this.columns);
            jt.setModel(driverTableModel);
        }

        if (e.getSource() == button2) {
            //sort data by points
            Collections.sort(formula1ChampionshipManager.getPlayers(), new pointComparator());
            setColumns();
            //create table model and add model to the main table
            TableModel driverTableModel = new DefaultTableModel(this.playerData, this.columns);
            jt.setModel(driverTableModel);
        }

        if (e.getSource() == probButton) {
            //set positions > call set position method
            setPosition();

            //call addRace method
            setColumns();
            //create new table model
            TableModel driverTableModel1 = new DefaultTableModel(this.playerData, this.columns);
            jt.setModel(driverTableModel1);
            setRawColor(jt,Color.LIGHT_GRAY);

            TableModel driverTableModel = new DefaultTableModel(this.showDataRw, this.showDataCol);
            showJt.setModel(driverTableModel);
        }
    }


    public void setAddDataTable() {
        try {
            formula1ChampionshipManager.setnumberofDrivers(100);
            formula1ChampionshipManager.Read();

        } catch (Exception e) {
            System.err.println("Invalid Order !");

        }

        //set show data table
        showDataCol = new String[]{"Place", "Name"};

        setShowDataCols();
        showJt = new JTable(this.showDataRw, showDataCol);
        showJt.setRowHeight(30);
        showJt.setRowHeight(50);

        showJt.setLayout(null);
        showJt.setGridColor(Color.black);
        Border border = BorderFactory.createLineBorder(Color.red);
        showJt.setBorder(border);
        showJt.setBounds(250, 40, 20, 20);


    }

    void setShowDataCols() {
        //set cols of show data table
        this.showDataRw = new Object[this.formula1ChampionshipManager.getPlayers().size()][2];
        for (int i = 0; i < this.formula1ChampionshipManager.getPlayers().size(); i++) {
            this.showDataRw[i][0] = Integer.toString(i + 1);
            this.showDataRw[i][1] = this.formula1ChampionshipManager.getPlaces().get(i + 1);
        }

    }


    public void addRace(Formula1ChampionshipManager formula1ChampionshipManager) {
        HashMap<Integer, String> place = new HashMap<>();
        ArrayList<Integer> positions = new ArrayList<>();
        formula1ChampionshipManager.setnumberofDrivers(100);

        try {
            //read saved data
            formula1ChampionshipManager.Read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < formula1ChampionshipManager.getPlayers().size(); i++) {
            int size = formula1ChampionshipManager.getPlayers().size();
            boolean done = true;
            while (done) {
                Random randInt = new Random();
                //get random number between 0 and player array size
                int input = randInt.nextInt(size);
                input += 1;

                //check whether position is already added
                if (positions.contains(input)) {
                    System.out.println("Position already assigned ");
                } else {
                    positions.add(input);
                    //get player old position count
                    Integer oldValue = formula1ChampionshipManager.getPlayers().get(i).getStatistics().get(input - 1);

                    if (oldValue != null) {
                        Integer newValue = 1 + oldValue;
                        //replace new value instead of old value
                        formula1ChampionshipManager.getPlayers().get(i).getStatistics().replace(input - 1, newValue);
                        //calculate new total points
                        formula1ChampionshipManager.getPlayers().get(i).setTotalPoints(formula1ChampionshipManager.getPlayers().get(i).getStatistics());
                        place.put(input, formula1ChampionshipManager.getPlayers().get(i).getNameOfTheDriver());
                        formula1ChampionshipManager.getPlayers().get(i).setNumberofRacesParticipated(formula1ChampionshipManager.getPlayers().get(i).getNumberofRacesParticipated() + 1);
                    }

                    done = false;

                }
            }
        }
        //add data to race data map
        formula1ChampionshipManager.getRaceData().put(String.valueOf(LocalDateTime.now()), place);
        formula1ChampionshipManager.setPlaces(place);

        try {
            //save data
            formula1ChampionshipManager.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    void setPosition() {

        HashMap<Integer, String> place = new HashMap<>();
        ArrayList<Integer> positions = new ArrayList<>();
        String[] arr = new String[9];
        formula1ChampionshipManager.setnumberofDrivers(100);

        try {
            //read all data
            formula1ChampionshipManager.Read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        calculateRandomPosition(arr, 9);
        System.out.println("positions :" + Arrays.toString(arr));


        //Passing the value of array's x index
        String name = probabilityCal(arr);

        for (int i = 0; i < formula1ChampionshipManager.getPlayers().size(); i++) {
            int size = formula1ChampionshipManager.getPlayers().size();
            boolean done = true;
            while (done) {
                Random randInt = new Random();
                int input = randInt.nextInt(size);
                input += 1;
                //check whether name is added in get player list
                if (Objects.equals(formula1ChampionshipManager.getPlayers().get(i).getNameOfTheDriver(), name)) {
                    //set as 1st position
                    positions.add(1);
                    //get old value
                    Integer oldValue = formula1ChampionshipManager.getPlayers().get(i).getStatistics().get(0);
                    Integer newValue = 1 + oldValue;
                    //replace old first place value with new value
                    formula1ChampionshipManager.getPlayers().get(i).getStatistics().replace(0, newValue);
                    //calculate new total points
                    formula1ChampionshipManager.getPlayers().get(i).setTotalPoints(formula1ChampionshipManager.getPlayers().get(i).getStatistics());
                    place.put(1, formula1ChampionshipManager.getPlayers().get(i).getNameOfTheDriver());
                    done = false;
                } else if (positions.contains(input)) {
                    System.out.print("");
                } else {
                    // Passing values to players (except first place)
                    if (input != 1) {
                        positions.add(input);
                        //get old value of the position (randomly counted)
                        Integer oldValue = formula1ChampionshipManager.getPlayers().get(i).getStatistics().get(input - 1);
                        if (oldValue != null) {
                            Integer newValue = 1 + oldValue;
                            formula1ChampionshipManager.getPlayers().get(i).getStatistics().replace(input - 1, newValue);
                            formula1ChampionshipManager.getPlayers().get(i).setTotalPoints(formula1ChampionshipManager.getPlayers().get(i).getStatistics());
                            place.put(input, formula1ChampionshipManager.getPlayers().get(i).getNameOfTheDriver());
                        }
                        done = false;
                    }
                }
            }

        }
        //Passing data to hashmap
        formula1ChampionshipManager.getRaceData().put(String.valueOf(LocalDateTime.now()), place);
        //set places of each player
        formula1ChampionshipManager.setPlaces(place);
        System.out.println(place);

        try {
            //save data
            formula1ChampionshipManager.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //positino

        showDataCol = new String[]{"Place", "Name", "Starting Position"};
        Integer[] startingPositions = new Integer[this.formula1ChampionshipManager.getPlayers().size()];
        int count = 0;


        this.showDataRw = new Object[this.formula1ChampionshipManager.getPlayers().size()][3];
        for (int i = 0; i < this.formula1ChampionshipManager.getPlayers().size(); i++) {
            this.showDataRw[i][0] = Integer.toString(i + 1);
            this.showDataRw[i][1] = this.formula1ChampionshipManager.getPlaces().get(i + 1);
            for (int j = 0; j < 9; j++) {
                String pName = arr[j];
                if (place.get(i + 1).equals(pName)) {
                    startingPositions[count] = (j + 1);
                    ++count;
                }

            }


        }
        for (int i = 0; i < this.formula1ChampionshipManager.getPlayers().size(); i++) {
            int pos = 10;
            this.showDataRw[i][2] = " " + startingPositions[i];
            if (startingPositions[i] == null) {
                this.showDataRw[i][2] = " " + pos;
                ++pos;
            }

        }

        System.out.println(Arrays.toString(startingPositions));
        //create new table model
        TableModel driverTableModel1 = new DefaultTableModel(this.playerData, this.columns);
        jt.setModel(driverTableModel1);


    }

    static String myRand(String[] arr, int[] freq, int n) {
        // Create and fill prefix array
        int[] prefix = new int[n];
        int i;
        prefix[0] = freq[0];
        for (i = 1; i < n; ++i)
            prefix[i] = prefix[i - 1] + freq[i];

        // prefix[n-1] is sum of all frequencies.
        // Generate a random number with
        // value from 1 to this sum
        int r = ((int) (Math.random() * (323567)) % prefix[n - 1]) + 1;

        // Find index of ceiling of r in prefix array
        int index = findCeil(prefix, r, 0, n - 1);
        System.out.println("index " + index);
        // Passing value to global var x
        x = index;
        return arr[index];
    }

    static int x = 0;

    // Utility function to find ceiling of r in arr[l..h]
    static int findCeil(int[] arr, int r, int l, int h) {
        int mid;
        while (l < h) {
            mid = l + ((h - l) >> 1); // Same as mid = (l+h)/2
            if (r > arr[mid])
                l = mid + 1;
            else
                h = mid;
        }
        return (arr[l] >= r) ? l : -1;
    }


    void calculateRandomPosition(String[] arr, int range) {

        LinkedList<String> linkedList = new LinkedList<>();

        for (int i = 0; i < formula1ChampionshipManager.getPlayers().size(); i++) {
            //add name of the driver into linked list
            linkedList.add(formula1ChampionshipManager.getPlayers().get(i).getNameOfTheDriver());
        }
        //calculate first 10 starting positions randomly
        for (int i = 0; i < range; i++) {
            Random num = new Random();
            //creates random number between 0 and linked list size
            int number = num.nextInt(linkedList.size());
            //add data to arr array
            arr[i] = linkedList.get(number);
            //remove the data which is already added to arr array
            linkedList.remove(number);

        }
    }


    String probabilityCal(String[] arr) {
        String[] probability = new String[100];

        for (int i = 0; i < 40; i++) {
            //passing first position to array 40%
            probability[i] = arr[0];
        }
        for (int i = 40; i < 70; i++) {
            //passing second position to array 30%
            probability[i] = arr[1];
        }
        for (int i = 70; i < 90; i++) {
            if (i < 80) {
                //passing third position to array 10%
                probability[i] = arr[2];
            } else {
                //passing fourth position to array 10%
                probability[i] = arr[3];
            }
        }
        for (int i = 90; i < 100; i++) {
            //passing fifth position to array 2%
            if (i < 92) {
                probability[i] = arr[4];
            }
            //passing sixth position to array 2%
            else if (i < 94) {
                probability[i] = arr[5];
            }
            //passing seventh position to array 2%
            else if (i < 96) {
                probability[i] = arr[6];
            }
            //passing eight position to array 2%
            else if (i < 98) {
                probability[i] = arr[7];
            }
            //passing ninth position to array 2%
            else {
                probability[i] = arr[8];
            }
        }
        //shuffle the data in array
        List<String> intList = Arrays.asList(probability);
        Collections.shuffle(intList);

        intList.toArray(probability);
        System.out.println((Arrays.toString(probability)));

        Random num = new Random();
        //creates random number between 0 and linked list size
        int number = num.nextInt(probability.length);
        System.out.println("Winner winner chicken dinner : " + number + " " + probability[number]);
        //return the first place
        return probability[number];

    }

    void setRawColor(JTable table,Color color){

        DefaultTableCellRenderer rendar1 = new DefaultTableCellRenderer();
        rendar1.setBackground(color);

        //set raw color
        table.getColumnModel().getColumn(1).setCellRenderer(rendar1);
        table.getColumnModel().getColumn(3).setCellRenderer(rendar1);
        table.getColumnModel().getColumn(5).setCellRenderer(rendar1);

    }


}
