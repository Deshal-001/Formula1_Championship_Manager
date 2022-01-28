package task3;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;


public class Pane2 implements ActionListener {

    private JTable jt;
    private LocalDateTime[] keys;
    private String selectValue;
    Formula1ChampionshipManager formula1ChampionshipManager;
    String[] columns;
    Object[][] raceData;
    JLabel label = new JLabel();
    JTable dataTable;
    String[] dtCols;
    Object[][] dtRows;
    JButton refresh;

    Pane2(Formula1ChampionshipManager formula1ChampionshipManager) {
        this.formula1ChampionshipManager = formula1ChampionshipManager;
    }

    Component getPane2() {
        JPanel controls = new JPanel();
        getTable();
        jt.setRowHeight(20);
        JScrollPane jScrollPane = new JScrollPane(jt);
        // create set data table
        setDataTable();
        controls.setBackground(Color.LIGHT_GRAY);
        JScrollPane jScrollPane2 = new JScrollPane(dataTable);
        //set pane layout
        controls.setLayout(null);
        //create a border
        Border border = BorderFactory.createLineBorder(Color.white, 3);

        label.setBounds(600, 390, 300, 100);
        label.setBorder(border);
        //set label font color to black
        label.setForeground(Color.BLACK);
        label.setBackground(Color.white);
        label.setHorizontalAlignment(SwingConstants.CENTER);


        jScrollPane.setBounds(20, 30, 550, 500);
        jScrollPane2.setBounds(600, 30, 300, 350);
        refresh=new JButton("Refresh");
        refresh.setBackground(Color.GRAY);
        refresh.setBounds(600, 500, 300, 35);

        //add tables and labels to the pane
        controls.add(refresh);
        controls.add(jScrollPane2);
        controls.add(jScrollPane);
        controls.add(label);

        return controls;
    }


    void getTable() {
        //pass values to columns and race data
        columns = new String[]{"Race No", "Date"};
        raceData = new Object[this.formula1ChampionshipManager.SeasonRaceData.size()][2];
        setKeys();
        //add data to keys
        for (int i = 0; i < this.formula1ChampionshipManager.SeasonRaceData.size(); i++) {
            getKeys()[i] = (LocalDateTime) this.formula1ChampionshipManager.SeasonRaceData.keySet().toArray()[i];
        }
        //sort keys
        Arrays.sort(getKeys());

        //parsing data to columns
        for (int i = 0; i < this.formula1ChampionshipManager.SeasonRaceData.size(); i++) {
            raceData[i][0] = " " + (i + 1);
            raceData[i][1] = getKeys()[i];
        }
        //create new border
        Border border = BorderFactory.createLineBorder(Color.black, 1);

        jt = new JTable(raceData, columns);
        jt.setLayout(null);
        jt.setGridColor(Color.black);
        jt.setBorder(border);

        //create new data model
        DefaultTableModel tableModel = new DefaultTableModel(raceData, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jt.setModel(tableModel);
        //set cells selectable
        jt.setCellSelectionEnabled(true);
        ListSelectionModel select = jt.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(e -> {
            int row = jt.getSelectedRow();
            int column = jt.getSelectedColumn();
            this.selectValue = String.valueOf(jt.getModel().getValueAt(row, column));
            System.out.println("Table element selected is: " + selectValue);

            try {

                String value = this.selectValue;
                //passing values to value var and change its type to date and time
                LocalDateTime localTimeObj = LocalDateTime.parse(value);
                //find the value,weather it is already added to the hashmap or not
                if (formula1ChampionshipManager.SeasonRaceData.containsKey(localTimeObj)) {
                    setDataCols(localTimeObj);
                    TableModel driverTableModel = new DefaultTableModel(this.dtRows, this.dtCols);
                    dataTable.setModel(driverTableModel);
                    //set labels name
                    label.setText(String.valueOf(localTimeObj));

                } else {
                    System.out.println("not founded");
                }

            } catch (Exception ex) {
                System.out.println("Not founded !");
            }
        });


    }

    public LocalDateTime[] getKeys() {
        return keys;
    }

    public void setKeys() {
        //Key equals to local date and time
        this.keys = new LocalDateTime[this.formula1ChampionshipManager.SeasonRaceData.size()];
    }

    void setDataTable() {
        //set header names
        dtCols = new String[]{"Place", "Name"};
        dtRows = new Object[formula1ChampionshipManager.getPlayers().size()][2];
        dataTable = new JTable(dtRows, dtCols);
        dataTable.setBounds(600, 20, 300, 400);
        dataTable.setRowHeight(30);

    }

    void setDataCols(LocalDateTime localTimeObj) {
        //passing values to the column
        for (int i = 0; i < formula1ChampionshipManager.getPlayers().size(); i++) {
            dtRows[i][0] = (i + 1);
            dtRows[i][1] = String.valueOf(formula1ChampionshipManager.SeasonRaceData.get(localTimeObj).get(i + 1));

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==refresh){
            try {
                formula1ChampionshipManager.Read();
                setKeys();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}
