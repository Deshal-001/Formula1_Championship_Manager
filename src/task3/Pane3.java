package task3;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class Pane3 implements ActionListener {
    Formula1ChampionshipManager formula1ChampionshipManager;
    private JTable jTable;
    JButton search = new JButton("Search");
    JTextField jTextField = new JTextField("Enter Player Name ");
    private String[] columns;
    private Object[][] raceData;


    Pane3(Formula1ChampionshipManager formula1ChampionshipManager) {
        this.formula1ChampionshipManager = formula1ChampionshipManager;
    }

    Component getPane3() {
        JPanel controls = new JPanel();
        controls.setLayout(null);
        controls.setBackground(Color.LIGHT_GRAY);

        Border border = BorderFactory.createLineBorder(Color.black);
        controls.setBorder(border);

        jTextField.setBounds(280, 50, 300, 30);

        search.setBounds(600, 50, 100, 30);
        search.addActionListener(this);
        setJt(" ");

        jTable = new JTable(this.raceData, this.columns);
        controls.add(jTable);

        DefaultTableCellRenderer rendar1 = new DefaultTableCellRenderer();
        rendar1.setBackground(Color.LIGHT_GRAY);
        jTable.getColumnModel().getColumn(1).setCellRenderer(rendar1);

        JScrollPane jScrollPane = new JScrollPane(this.jTable);
        jScrollPane.setBounds(240, 100, 500, 500);
        controls.add(jScrollPane);
        controls.add(jTextField);
        controls.add(search);
        return controls;
    }

    private void setJt(String playersName) {
        try {
            formula1ChampionshipManager.setnumberofDrivers(100);
            formula1ChampionshipManager.Read();

        } catch (Exception e) {
            System.err.println("Invalid Order !");
        }
        this.columns = new String[]{"Race Date", "Place"};
        setColumns(playersName);
    }

    void setColumns(String playerName) {
        try {
            formula1ChampionshipManager.setnumberofDrivers(100);
            formula1ChampionshipManager.Read();

        } catch (Exception e) {
            System.err.println("Invalid Order !");

        }

        LocalDateTime[] keys = new LocalDateTime[formula1ChampionshipManager.SeasonRaceData.size()];

        for (int i = 0; i < formula1ChampionshipManager.SeasonRaceData.size(); i++) {
            keys[i] = (LocalDateTime) formula1ChampionshipManager.SeasonRaceData.keySet().toArray()[i];

        }
        this.raceData = new Object[keys.length][2];

        for (int i = 0; i < formula1ChampionshipManager.SeasonRaceData.size(); i++) {

            if (formula1ChampionshipManager.SeasonRaceData.get(keys[i]).containsValue(playerName)) {
                Object[] keyset;
                keyset = formula1ChampionshipManager.SeasonRaceData.get(keys[i]).keySet().toArray();
                for (int j = 0; j < keyset.length; j++) {
                    String playerName2 = (String) formula1ChampionshipManager.SeasonRaceData.get(keys[i]).get(j + 1);

                    if ((playerName2).equalsIgnoreCase(playerName)) {
                        try {
                            this.raceData[i][0] = keys[i];
                            this.raceData[i][1] = keyset[j];
                        } catch (NullPointerException nullPointerException) {
                            System.out.println("Null value");
                        }
                    }
                }

            } else {
                this.raceData[i][0] = keys[i];
                this.raceData[i][1] = "Not Participated";
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            this.jTable.setAutoCreateRowSorter(true);
            System.out.println("driver name " + this.jTextField.getText());
            setColumns(this.jTextField.getText());
            TableModel driverTableModel = new DefaultTableModel(this.raceData, this.columns);
            jTable.setModel(driverTableModel);
            DefaultTableCellRenderer rendar1 = new DefaultTableCellRenderer();
            rendar1.setBackground(Color.LIGHT_GRAY);
            jTable.getColumnModel().getColumn(0).setCellRenderer(rendar1);


        }

    }
}




