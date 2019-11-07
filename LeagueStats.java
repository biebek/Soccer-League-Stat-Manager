/**

Date: 11-28-2018

Course: CSCI 2073

Description: This program keeps track of a soccer league. It reads all the soccer games results stored in a comma-separated file. 
             It keeps record of number of wins, defeats, draws, goals scored, goals conceded, and points of any team. The feature
             of this program is that it reads a file to display four different kinds statistics of this league, which are as follows:
             
             1) Stats of any requested team. Stats: wins, draws, losts, goals scored, goals conceded, and the points accumulated.
             2) Stats of the best team; the best team meaning the one that has the maximum points in the league.
             3) The teams who have managed to score more goals in the league than the best team.
             4) Rank of any team in the league.


On my honor, I have neither given nor received unauthorized help while completing this assignment.
-Biebek Chamlagain
*/


/**
   Imports Java util package
*/
import java.util.*;
import java.io.*;


/**
   Creates a public class that holds all the methods to the required methods a soccer league
*/

public class LeagueStats{
   
   HashMap<String, ArrayList<Integer>> map;
   String res;
   
   /**
      Creates a constructor to read the csv file and store the information from each game using the HashMap.
      The team name is stored as the key in the map. The stats realted to team are stored in an array and then passed as value in the map.
      The team scoring more goals than the opponent get a win, earning 3 points in the process. The losing team gets no point.
      If both the teams happen to score equal number of goals, 1 point each is added in their point tally.
      @param csvFile, The file that contains results from the game.
   */   
   public LeagueStats(String csvFile){
      try{
         Scanner input = new Scanner(new File(csvFile));    //Reads the fiile.
         map = new HashMap<>();                             
         while(input.hasNext()){
                  
            String data = input.nextLine();
            String[] dataar = data.split(",");  //Separates the information on file based on comma for a successful data fetch.
            ArrayList<Integer> StatArray; 
            boolean fl1 = false;
            boolean fl2 = false;
            
            //Result update for Home Team
            if(!map.containsKey(dataar[0])){
               StatArray = new ArrayList<>(); 
               fl1=true;
               int sc2 = Integer.parseInt(dataar[2]);
               int sc3 = Integer.parseInt(dataar[3]);
               //Checks if the home team won the game and stores data in the order or no. of wins, no. of draws, no. of loses, goals scored, goals conceded, and points.
               if(sc2>sc3){
                  StatArray.addAll(Arrays.asList(1,0,0,Integer.parseInt(dataar[2]),Integer.parseInt(dataar[3]),3));
               
               }
               //Checks if the home team drew the game and stores data in the order or no. of wins, no. of draws, no. of loses, goals scored, goals conceded, and points.
               else if(sc2==sc3){
                  StatArray.addAll(Arrays.asList(0,1,0,Integer.parseInt(dataar[2]),Integer.parseInt(dataar[3]),1));
               }
               //In the case when home team loses the game, stores data for the home team in the order or no. of wins, no. of draws, no. of loses, goals scored, goals conceded, and points.
               else{
                  StatArray.addAll(Arrays.asList(0,0,1,Integer.parseInt(dataar[2]),Integer.parseInt(dataar[3]),0));
               
               }
               map.put(dataar[0],StatArray); //Updates the stats of home team.
            }
            
            //Result update for the away team:
            if(!map.containsKey(dataar[1])){
               fl2 =true;
               int sc2 = Integer.parseInt(dataar[2]);
               int sc3 = Integer.parseInt(dataar[3]);
            
               StatArray = new ArrayList<>();
               //Checks if the away team won the game and stores data its in the order or no. of wins, no. of draws, no. of loses, goals scored, goals conceded, and points.
               if(sc3>sc2){
                  StatArray.addAll(Arrays.asList(1,0,0,sc3,sc2,3));
               }
               //Checks if the away team won the game and stores data in the order or no. of wins, no. of draws, no. of loses, goals scored, goals conceded, and points.
               else if(sc3==sc2){
                  StatArray.addAll(Arrays.asList(0,1,0,sc3,sc2,1));
               }
               //In the case when the away team loses the game, stores data for the home team in the order or no. of wins, no. of draws, no. of loses, goals scored, goals conceded, and points.
               else{
                  StatArray.addAll(Arrays.asList(0,0,1,sc3,sc2,0));
                  
               }
               map.put(dataar[1],StatArray); //Updates the stats of the away team.
            }
            
            int won = 0;
            int draw = 0;
            int loss = 0;
            int gf = 0;
            int ga = 0;
            int pts=0;
            int sc2 = Integer.parseInt(dataar[2]);
            int sc3 = Integer.parseInt(dataar[3]);
            
            //If home team is already stored on the record, checks the goals and updates the stats accordingly.
            if(!fl1){
              
               ArrayList<Integer> myar = map.get(dataar[0]);
               StatArray = new ArrayList<Integer>();
               won = myar.get(0);
               draw = myar.get(1);
               loss = myar.get(2);
               gf = myar.get(3);
               ga = myar.get(4);
               pts = myar.get(5);
               if(sc2>sc3){
                  StatArray.addAll(Arrays.asList(won+1,draw,loss,gf+sc2,ga+sc3,pts+3));
               
               }
               else if(sc2==sc3){
                  StatArray.addAll(Arrays.asList(won,draw+1,loss,gf+sc2,ga+sc3,pts+1));
                  
               }
               else{
                  StatArray.addAll(Arrays.asList(won,draw,loss+1,gf+sc2,ga+sc3,pts));
               
               }
               map.put(dataar[0],StatArray);
            }
            
            //If away team is already stored on the record, checks the goals and updates the stats accordingly.
            if(!fl2){
               ArrayList<Integer> myar = map.get(dataar[1]);
               StatArray = new ArrayList<>();
               won = myar.get(0);
               draw = myar.get(1);
               loss = myar.get(2);
               gf = myar.get(3);
               ga = myar.get(4);
               pts = myar.get(5);
               if(sc3>sc2){
                  StatArray.addAll(Arrays.asList(won+1,draw,loss,gf+sc3,ga+sc2,pts+3));
               
               }
               else if(sc3==sc2){
                  StatArray.addAll(Arrays.asList(won,draw+1,loss,gf+sc3,ga+sc2,pts+1));
                  
               }
               else{
                  StatArray.addAll(Arrays.asList(won,draw,loss+1,gf+sc3,ga+sc2,pts));
               
               }
               map.put(dataar[1],StatArray);
              
              
            }
         
               
         }            
            
      }
          
         
      catch(FileNotFoundException e){
      }
   }
   /**
      This method finds the best team, the team with maximum number of points, from all the games played in the league, and returns its stats.
      If teams have equal number of points the team with higher goals difference (the differnce between goals scored and goals conceded) is considered as the best team.
      If teams have equal points and goal difference then the number of goals scored is used to determine the best result. 
      @return, Returns the name along with its stats (Refer the program description for the explanation on stats).
   */   
   private String best(){
      String result = "";
      String team = "";
               int maximum =0;
               int curgd = 0;
               int curgf = 0;
               for(String key: map.keySet()){
                  ArrayList<Integer> arList = map.get(key);
                  int current = arList.get(5);
                  if(current>maximum){
                     curgd = arList.get(3)-arList.get(4);
                     curgf = arList.get(3);
                     team = key;
                     maximum = current;
                  }
                  if(current==maximum)
                     if(arList.get(3)-arList.get(4)>curgd){
                        curgd = arList.get(3)-arList.get(4);
                        curgf = arList.get(3);
                        team = key;
                        maximum = current;
                     }
                     else if(arList.get(3)-arList.get(4)>curgd){
                        if(curgf<arList.get(3)){
                           curgd = arList.get(3)-arList.get(4);
                           curgf = arList.get(3);
                           team = key;
                           maximum = current;
                        
                        }
                     }
               
               }
               result+=team;
        
               
               return result;
   
   
   }
   /**
   This method determines the rank of the given team in the league. The rank is based on the total points of each team. 
   In case of equal points, same approaches are taken when best team was determined in the above method.
   @param name, The name of the team for which the rank is to be calculated.
   @return, Returns the name of the team and its rank, mentioning the total number of teams in the league. 
   */
   int getRanking(String name){
   ArrayList<Integer> dr = map.get(name);
   int ranking = map.size();
   int this_pts = dr.get(5);
   int this_gdf = dr.get(3)-dr.get(4);
   for(String key:map.keySet()){
   if(!key.equals(name)){
      ArrayList<Integer> dr2 = map.get(key);
      int pts = dr2.get(5);
      int gdf = dr2.get(3)-dr2.get(4);
      if(this_pts>pts){
         ranking-=1;
      }
      else if(this_pts==pts){
         if(this_gdf>gdf){
            ranking-=1;
            
            }
      }
      
   
   }
      }
      
      return ranking;
   
   }
   /**
      This method processes the file containg the following requests from a file:
      1) Stats of any team. Displays the team is not found if the team is not in the league.
      2) Best team and its stats.
      3) High Scorers: They are those teams in the league who have scored more goals than the best team.Displays none if no such team is found.
      4) Rank of any team in the league.Displays the given team is not found if it is not in the league.
      @param statsFile, The file that contains the requests for league statistics.
      @return, Returns the details all the requested statistics from the file about the team and the league. 
   */
   String getStats(String statsFile){
      
      try{
         Scanner i = new Scanner(new File(statsFile));
         res = "";
         while(i.hasNext()){
            String lines = i.nextLine();
            String[] line = lines.split(" ");
            String name = "";
            for(int x=1;x<line.length;x++){
               name+=line[x]+" ";
            }
            name = name.trim();
            
            //Displays the stats of the required team. 
            if(line[0].equals("STATS")){
               if(map.containsKey(name)){
                  ArrayList<Integer> myar = map.get(name);
                  int won = myar.get(0);
                  int draw = myar.get(1);
                  int   loss = myar.get(2);
                  int   gf = myar.get(3);
                  int   ga = myar.get(4);
                  int   pts = myar.get(5);
                  res+=String.format("TEAM: %-20s W: %d D: %d L: %d  GF: %d GA: %d PTS: %d\n",name,won,draw,loss,gf,ga,pts);
               }
               else{
                  res+=String.format("TEAM: %s NOT FOUND\n",name);
               }
            
            }
            //Displays the stat of the best team.
            if(line[0].equals("BEST")){
               String team = best();
               res+= String.format("BEST: %-20s W: %d D: %d L: %d  GF: %d GA: %d PTS: %d\n",best(),map.get(team).get(0),map.get(team).get(1),map.get(team).get(2),map.get(team).get(3),map.get(team).get(4),map.get(team).get(5));
            }
            
            //Diplays the name of those teams who have scored more goals than the best team has. 
            String hscoring = "HIGH SCORERS: ";
            if(line[0].equals("HSCORING")){
               String l= best();
               
               int count =0;
               boolean gla = false;
               for(String val:map.keySet()){
                  ArrayList<Integer> StatArray = map.get(val);
                  int goal = StatArray.get(3);
                  if(goal > map.get(l).get(3)){
                 
                  gla = true;
                  if (count ==0){
                     hscoring+=" "+val;
                     count++;}
                  else
                      hscoring+=", "+val;
                  }
               }
               if(!gla){
               res+=hscoring+"NONE\n";
               }
               else{
               res+=hscoring+"\n";
               }
            
            }
            
            //Displays the rank of any given team when asked for it. 
            if(line[0].equals("RANK")){
               
               if (map.containsKey(name)){
                  int rank = getRanking(name);
                  
                  res+= String.format("RANK: %s is ranked %d out of %d\n",name,rank,map.size());}
              else 
                  res+= String.format("RANK: %s NOT FOUND\n",name);
            
            }
         }
      }
               
      catch(FileNotFoundException e){
      
      }
         
      return res;
   }
   
}
   

