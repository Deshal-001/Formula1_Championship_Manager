package task3;//file: TabbedPaneFrame.java

import javax.swing.*;


public class PanePage{
    JFrame frame = new JFrame("𝙁𝙄  Championship Manager");
    Formula1ChampionshipManager formula1ChampionshipManager;


    PanePage(Formula1ChampionshipManager formula1ChampionshipManager){
        this.formula1ChampionshipManager=formula1ChampionshipManager;
    }

    void getFrame(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JTabbedPane jTabbedPane = new JTabbedPane();
        // create the controls pane and add sub panes
        jTabbedPane.addTab("Table",new Pane1(this.formula1ChampionshipManager).getPane1());
        jTabbedPane.addTab("Season", new Pane2(this.formula1ChampionshipManager).getPane2() );
        jTabbedPane.addTab("Search", new Pane3(this.formula1ChampionshipManager).getPane3() );

        frame.getContentPane().add(jTabbedPane);

        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}