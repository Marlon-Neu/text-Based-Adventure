package text_Base_Adventure;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
	
	private static World world = new World();
	private static Ending gameEnding = Ending.won;
	private static List<Player> players = new LinkedList<Player>();
	private static List<Updatable> updatables = new ArrayList<Updatable>();

	public static void main(String[] args) {
		// Welt aufbauen

		world = new World();
		updatables.add((Updatable) world);
        gameEnding = Ending.won;
        players = new LinkedList<Player>();
		
        Scanner scanner = new Scanner(System.in);
        
        int numPlayers = 0;
        System.out.println("Wie viele Spieler (4 max): ");
        boolean isValid = false;
        do {
        	numPlayers = scanner.nextInt();
        	if(numPlayers>0 && numPlayers <= 4) {
        		isValid = true;
	        	}
	        else{
	        		System.out.println("Ungültige Eingabge");
	        }
        }while(!isValid);
        
        List<String> starts = world.getStarts();
        starts.add("Random");
        
        for(int i = 1;i<=numPlayers;i++) {
	        // Spieler, beginnt das Spiel am Start-Ort
	        System.out.println("Name des Spielers " + i + ": ");
	        String name = scanner.next();
	        if(name.equals("Debug")) {
	        	players.add(new DebugPlayer(name,world));
	        }
	        else {
	        	players.add(new Player(name));
	        }
	        int ctr = 1;
	        System.out.println("Mögliche Anfänge: ");
	        for (String start : starts) {
	            
	            System.out.println((ctr++)+": "+start);
	        }
	        
	        System.out.println("Wo soll's anfangen?");
	        isValid = false;
	        int startIndex;
	        do {
	        	startIndex = scanner.nextInt();
	        	if(startIndex>0 && startIndex <= ctr) {
		        	
	        		isValid = true;
		        	}
	        	
		        else{
		        		System.out.println("Ungültige Eingabge");
		        }
	        }while(!isValid);
	        
	        world.setStart(starts.get(startIndex-1));
	        players.get(i-1).goTo(world.getStart());
        }
        

        // game loop: spielen, bis wir am Ziel sind
        List<Player> playersFinished = new LinkedList<Player>();
        while (!players.isEmpty()) {
        	for(Player player : players) {
	            // Mal schauen, wo wir sind
	            Place place = player.where();
	            System.out.println( player.toString());
	
	            // Wo kann's hingehen?
	            System.out.println("Mögliche Wege:");
	            Road[] roads = place.getRoads();
	
	            // Auswahl: 1..n, entspricht 0..n-1 im Array
	            for (int i = 0; i < roads.length; i++) {
	                Road road = roads[i];
	                System.out.println((i + 1) + ": " + road.getDescription());
	            }
	            System.out.println("Wohin soll's gehen?");
	
	            // Wo will der Spieler hin?
	            int number = scanner.nextInt();
	            if (player.makeChoice(number, roads)) {
	            	System.out.println(player.getText());
	            	update();
	            } 
	            else {
	                System.out.println("Ungültige Eingabge");
	            }
	            
	            if(world.isEnd(player.where())){
	            	playersFinished.add(player);
	            	gameEnding = Ending.won;
	            	System.out.println(String.format(gameEnding.getMessage(), player.getName()));
	            }
	            if(!player.isAlive()) {
	            	playersFinished.add(player);
	            	gameEnding = Ending.hpLost;
	            	System.out.println(String.format(gameEnding.getMessage(), player.getName()));
	            }
        	}
        	players.removeAll(playersFinished);
        }
        System.out.println("Alle Spieler sind fertig!");
        scanner.close();
    }
	
	private static void update() {
		for( Updatable updatable:updatables) {
			updatable.update();
		}
	}
}


