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
 *
 * @author kavindudesilva
 */
public abstract class Driver implements Serializable {
     
    
    private String nameOfTheDriver;
    private  String location;
    private  String team;
    private  int NumberofRacesParticipated;
    
    private  HashMap<Integer, Integer> statistics=new  HashMap<>(9);

     
    
     public Driver(){}
     
     
     Driver(String name,String location,String team,Integer NumberofRacesParticipated){
         this.nameOfTheDriver=name;
         this.team=team;
         this.location=location;
         this.NumberofRacesParticipated=NumberofRacesParticipated;
     }
     
     Driver(String name,String location,String team,int NumberofRacesParticipated, HashMap<Integer, Integer> statistics){
        
         this.nameOfTheDriver=name;
         this.team=team;
         this.location=location;
         this.NumberofRacesParticipated=NumberofRacesParticipated;
         this.statistics=statistics;
     
    
     
     }


    /**
     * @return the nameOfTheDriver
     * 
     */
    
   
    public String getNameOfTheDriver() {
        return nameOfTheDriver;
    }

    /**
     */
    
    public void setNameOfTheDriver( String name) {
        this.nameOfTheDriver = name;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param userlocation the location to set
     */
    public void setLocation(String userlocation) {

        this.location = userlocation;
    }

    /**
     * @return the team
     */
    public String getTeam() {
        return team;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(String team) {
        this.team = team;
        
    }
    
   
    

    /**
     * @return the NumberofRacesParticipated
     */
    public int getNumberofRacesParticipated() {
        return NumberofRacesParticipated;
    }

    
    /**
     */
    public  void setNumberofRacesParticipated(Integer races) {

         
        this.NumberofRacesParticipated = races;
    }
    
    
      
     @Override
    public String toString(){
        return (nameOfTheDriver+" "+team+" "+location+" "+NumberofRacesParticipated+"" +statistics );
    }

    /**
     * @return the statistics
     */
    public   HashMap<Integer, Integer> getStatistics() {
        return statistics;
    }
    
    

    /**
     * @param statistics the statistics to set
     */
    public  void  setStatistics( HashMap<Integer, Integer> statistics) {
        this.statistics = statistics;
    }
 
    
    

       // Haaaaaaash Maaaaapppp
    
    
    
    
    
}
