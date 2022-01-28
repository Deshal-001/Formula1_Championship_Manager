package task3;
import javax.swing.*;
import java.awt.*;

public class FirstPage {
    JFrame frame=new JFrame();
    JLabel lbl=new JLabel();
    JProgressBar bar = new JProgressBar(0,100);

    FirstPage(){
    }
   void setFrame(){
        frame=new JFrame();
        frame.getContentPane().setBackground(Color.black);
         setLabel();
        frame.setBackground(Color.black);
        frame.setSize(1000, 700);  // set size
        frame.setLayout(null);
       frame.setVisible(true);
        setBar();
        fill();
        // dispose the frame
       frame.dispose();
       Formula1ChampionshipManager formula1ChampionshipManager = new Formula1ChampionshipManager();
       //create and open pane page
       PanePage panePage = new PanePage(formula1ChampionshipManager);
       panePage.getFrame();
   }

   void setLabel(){
        //create new image icon
        ImageIcon img=new ImageIcon("src/task3/logo.png");
        lbl=new JLabel();
        lbl.setBounds(250,100,450,400);
        lbl.setIcon(img);
        frame.getContentPane().add(lbl);
   }


    public void setBar(){
        //set bar default value as 0
        bar.setValue(0);
        bar.setStringPainted(true);
        bar.setBounds(0, 550, 1000, 150);
        frame.getContentPane().add(bar);

    }

    public void fill() {
        int counter =0;
        while(counter<=100) {
            bar.setValue(counter);

            try {
                //set sleep time
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Passing values to counter
            counter +=10;

        }


    }



}
