package text_Base_Adventure;

import java.util.List;
import java.util.ArrayList;


public class World implements Updatable{
	private List<Place> places = new ArrayList<Place>();
	private List<String> targets = new ArrayList<String>();
	private List<String> starts = new ArrayList<String>();
	private List<Item> worldItems = new ArrayList<Item>();
	private List<Updatable> updatables = new ArrayList<Updatable>();
    private Place start;
	
	@SuppressWarnings("serial")
	public World() {
		places.add(new Place("Kleines Dörfchen"));	//0
		places.add(new Place("Steiniger Weg"));		//1
		places.add(new Place("Wald"));				//2
		places.add(new Place("See"));				//3
		places.add(new Place("Stadt"));				//4
		
		places.add(new Place("Tiefer Wald"));		//5
		places.add(new Place("Alte Hütte"));		//6
		places.add(new Place("Höhle"));				//7
		places.add(new Place("Insel Anfang"));		//8
		places.add(new Place("Schloss"));			//9
		places.add(new Place("Insel"));				//10
		places.add(new Place("Fluss"));				//11	
		
		
	    Item basicArmor = new Armor("Basis-Rüstung","Lederrüstung, die 5 nichtprozentigen Schaden blockt.",5);
	    Item goodArmor = new Armor("Gepolsterte Rüstung","Gepolsterte Lederrüstung, die 15 nichtprozentigen Schaden blockt.",15);
	    Item riverBrooch = new Item("Brosche","eine Brosche mit einem hellblauen Edelstein, ermöglicht den Zugang zu einem verborgenen Teich im tiefen Wald.");
	    Item fish = new Item("Fisch","ein Flussfisch");
	    Item fishingRod = new Item("Angelrute","eine alte Angelrute");
	    Item fireWard = new Item("Feuerschutz","Vertreibt Feuergeister");
		
	    worldItems.add(basicArmor);
	    worldItems.add(goodArmor);
	    worldItems.add(riverBrooch);
	    worldItems.add(fish);
	    worldItems.add(fishingRod);
	    worldItems.add(fireWard);
		
		Event stone = new BlockableEvent(0.20,"Ein Stein hat Sie umgeworfen.. -20 HP",-20);
		Event wind = new BlockableEvent(0.20,"Ein heilender Wind weht über Sie.. +10 HP",10);
		Event fire = new PercentCurrentEvent(1,"Ein kleiner Feuergeist verbrennt Sie. Halbe HP verlieren",-50);
		Event fireWardCheck = new ItemCheckEvent(0.3,"",fireWard,new BasicEvent(1,"Der Feuerschutz wehrt einen Feuergeist ab."),fire);
		Event water = new PercentCurrentEvent(0.20,"Es regnet plötzlich, aber Sie fühlen sich geheilt.Halbe HP bekommen",25);
		List<Event> eventsElemental = new ArrayList<Event>(){
			{
	    		add(stone);
	    		add(wind);
	    		add(fireWardCheck);
	    		add(water);
	    	}
	    };
	    Event forestEvent1 = new BasicEvent(.5,"Die Bäume wiegen sich, der Wald ist still.");
	    Event forestEvent2 = new BasicEvent(.5,"Sie hören verschiedene Geräusche, sie auf verschiedene Orte zeigen.");
	    Event forestEvent3 = new BasicEvent(.5,"Der Wald ist die Heimat der Elementare, sei vorsichtig, wenn Sie hier bleiben.");
		
	    List<Event> eventsForest = new ArrayList<Event>(){
			{
	    		add(forestEvent1);
	    		add(forestEvent2);
	    		add(forestEvent3);
	    	}
	    };

	    Event armorEvent = new ItemGetEvent(.75,"Du hast eine Basis-Rüstung gefunden.",basicArmor);
	    Event armorEvent2 = new ItemGetEvent(1,"Du hast eine Gepolsterte Rüstung gefunden.",goodArmor);
	    
	    List<Event> eventArmor = new ArrayList<Event>(){
			{
	    		add(armorEvent);
	    		add(armorEvent2);
	    	}
	    };   
	    
	    Event riverEventHPPass = new ItemGetEvent(1,"Ein Wesen kommt aus dem Fluss, gibt dir eine Brosche und sagt: \"Holen Sie Hilfe\".",riverBrooch);
	    Event riverEventBrooch = new LifeCheckEvent(1,"",60,riverEventHPPass,new BasicEvent(1,"Sie haben das Gefühl, dass Sie jemand anstarrt."),true);
	    Event riverEvent1 = new ItemCheckEvent(.25,"",riverBrooch,new BasicEvent(0.5,"Die Brosche leuchtet."),riverEventBrooch);
	    Event riverEventFish = new ItemGetEvent(1,"Nach ein paar ruhigen Momenten haben Sie erfolgreich einen Fisch gefangen.",fish);
	    Event riverEventBreak = new ItemRemoveEvent(1,"Die Angelrute schnappt in zwei Teile.",fishingRod);
	    Event riverEventDurablity = new CounterEvent(1,"",2,riverEventBreak,new BasicEvent(1,"Die Angelrute ist näher an ihrer Sollbruchstelle."));
	    List<Event> eventsFishing = new ArrayList<Event>(){
	    	{
	    		add(riverEventFish);
	    		add(riverEventDurablity);
	    	}
	    };
	    Event riverEventFishing = new MultipleEvents(eventsFishing);
	    Event riverEvent2 = new ItemCheckEvent(1,"Sie haben spontan Lust zu fischen.",fishingRod,riverEventFishing,new BasicEvent(1,"Aber Sie haben keine Angelrute."));
	    
	    Event hutItemEvent = new ItemGetEvent(.75,"",fishingRod);
	    Event hutSearchEvent = new OnceEvent(hutItemEvent,"Sie haben nichts gefunden");
	    Event hutDangerEvent = new BlockableEvent(.25,"Etwas beißt dich und läuft weg. -10 HP",-10);
	    
	    List<Event> eventsHutSearch = new ArrayList<Event>(){
	    	{
	    		add(hutDangerEvent);
	    		add(hutSearchEvent);
	    	}
	    };
	    
		
	    Road[] startRoads = {new Road("verlasse Dorf", places.get(1),eventArmor)};
	    places.get(0).setRoads(startRoads);
	   
	    Road[] wegRoads = {
	    		new Road("betrete Wald", places.get(2),eventsElemental),
	    		new Road("folge Fluss",places.get(11))
	    
	    };
	    places.get(1).setRoads(wegRoads);
	    
	    Road[] waldRoads = {
	            new Road("gehe zu Weg", places.get(1)),
	            new Road("bleibe hier", places.get(2),eventsElemental),
	            new Road("gehe nach Osten", places.get(3)),
	            new Road("gehe nach Westen", places.get(4)),
	            new Road("gehe tiefer", places.get(5)),
	    };
	    places.get(2).setRoads(waldRoads);
	    places.get(2).setEvents(eventsForest);
	    
	    
	    Road[] seeRoads = {
	            new Road("gehe nach Westen", places.get(2),eventsElemental),
	            new Road("gehe nach Norden", places.get(4)),
	            new Road("gehe zu Fluss",places.get(11))
	    };
	    places.get(3).setRoads(seeRoads);
	    
	    
	    Road[] tiefWaldRoads = {
	    		new Road("gehe zurück", places.get(2),eventsElemental),
	    		new Road("erforsche Hütte",places.get(6)),
	    		new Road("gehe nach Norden",places.get(9))
	    };
	    places.get(5).setRoads(tiefWaldRoads);
	    
	    Road[] huetteRoads = {
	    		new Road("gehe zurück",places.get(5)),
	    		new Road("bleibe hier und suche",places.get(6),eventsHutSearch)
	    };
	    places.get(6).setRoads(huetteRoads);
	    
	    Road[] hoehleRoads = {
	    		new Road("verlasse Höhle",places.get(3))
	    };
	    places.get(7).setRoads(hoehleRoads);
	    
	    
	    
	    Road[] inselAnfangRoads = {
	    		new Road("weiter",places.get(10))
	    };
	    places.get(8).setRoads(inselAnfangRoads);
	    
	    Road[] inselRoads = {
	    		new Road("insel erkunden",places.get(10))
	    };
	    places.get(10).setRoads(inselRoads);
	    
	    Event islandChange = new ChangeTargetEvent(1,"",0,inselRoads[0],places.get(3));
	    Event islandCounter = new CounterEvent(1,"",5,islandChange,new BasicEvent(1,""));
	    inselRoads[0].addEvent(islandCounter);
	    
	    
	    
	    Road[] riverRoads = {
	    		new Road("gehe zu Weg",places.get(1)),
	    		new Road("bleibe hier",places.get(11)),
	    		new Road("folge Fluss",places.get(3))
	    };
	    places.get(11).setRoads(riverRoads);
	    places.get(11).addEvent(riverEvent1);
	    places.get(11).addEvent(riverEvent2);
	    
	    
	    updateTargets();
	    updateStarts();
	    start = places.get(0);
	    
	    //------Event Places------ (Should not be Start Points)
	    places.add(new Place("Rest Point"));		//12
	    places.add(new Place("Hidden Pond"));		//13
	    
	    Event restEvent = new ChangeTargetEvent(0.5,"Sie finden einen Ruhepunkt, wollen Sie tiefer gehen?",40,waldRoads[4],places.get(12));
	    waldRoads[4].addEvent(restEvent);
	    
	    Road[] restRoads = {
	    	new Road("zurück in den Wald gehen",places.get(2)),
	    	new Road("tiefer gehen",places.get(5))
	    };
	    Event restEndEvent = new ChangeTargetEvent(1,"",0,waldRoads[4],places.get(5));
	    
	    Event pondRoadEvent = new AddRoadEvent(1,"Die Brosche enthüllt eine verborgene Straße.",places.get(5),
	    		new Road("gehe zu Tiech",places.get(13))
	    		);
	    Event checkBroochEvent = new ItemCheckEvent(1,"",riverBrooch,pondRoadEvent,new BasicEvent(1,""));
	    Event pondEvent = new OnceEvent(checkBroochEvent);
	    waldRoads[4].addEvent(pondEvent);
	    restRoads[1].addEvent(pondEvent);
	    Event greetingsEvent = new OnceEvent(new BasicEvent(1,"Du siehst ein Wesen, es sagt \"Hallo, du bist neu\" und zeigt auf das Schild."));
	    Event signEvent = new BasicEvent(1,"Auf dem Schild steht: 1 Hilfe für 1 Fisch.");
	    Event outOfStockEvent = new BasicEvent(1,"Oben auf einer Matte befindet sich ein \"Kein Bestand\"-Schild.");
	    Event saleCounterEvent = new CounterEvent(1,"",3,outOfStockEvent,new BasicEvent(1,""));
	    Event saleObserveEvent = new CounterObserverEvent("Auf einer Matte befinden sich %d Gegenstand(e).",(CounterEvent) saleCounterEvent);
	    List<Event> eventsShopping = new ArrayList<Event>(){
	    	{
	    		add(greetingsEvent);
	    		add(signEvent);
	    		add(saleObserveEvent);
	    	}
	    };
	    Event saleMultiple = new MultipleEvents(eventsShopping);
	    
	    Event saleRemoveFish = new ItemRemoveEvent(1,"Das Wesen absorbiert den Fisch und gibt dir den Gegenstand.",fish);
	    Event saleArmorEvent = new ItemGetEvent(1,"Sie haben die Rüstung gekauft.",goodArmor);
	    List<Event> eventsArmor = new ArrayList<Event>(){
	    	{
	    		add(saleRemoveFish);
	    		add(saleArmorEvent);
	    		add(saleCounterEvent);
	    	}
	    };
	    Event saleBuyArmor = new MultipleEvents(eventsArmor);
	    Event saleItemArmor = new OnceEvent(new ItemCheckEvent(1,"",fish,saleBuyArmor,new BasicEvent(1,"")),"Sie haben kein Fisch","Kein Bestand");
	    
	    
	    Event saleWardEvent = new ItemGetEvent(1,"Sie haben den Feuerschutz gekauft.",fireWard);
	    List<Event> eventsWard = new ArrayList<Event>(){
	    	{
	    		add(saleRemoveFish);
	    		add(saleWardEvent);
	    		add(saleCounterEvent);
	    	}
	    };
	    Event saleBuyWard = new MultipleEvents(eventsWard);
	    Event saleItemWard = new OnceEvent(new ItemCheckEvent(1,"",fish,saleBuyWard,new BasicEvent(1,"")),"Sie haben kein Fisch","Kein Bestand");
	    
	    Event chaosPotionEvent = new BasicEvent(1,"Sie nehmen den Zaubertrank, aber er explodiert plötzlich!");
	    Event healPotionEvent = new PercentCurrentEvent(.75,"Der Zaubertrank heilt Sie.",100);
	    Event firePotionEvent = new PercentCurrentEvent(1,"Der Feuerstoß trifft Sie!",-50);
	    Event fireWardCheck2 = new ItemCheckEvent(.5,"Ein Feuerstoß erscheint aus dem Nichts!",fireWard,new BasicEvent(1,"Der Feuerschutz hat Sie irgendwie vor der Explosion geschützt."),firePotionEvent);
	    Event summonBearPotionEvent = new BlockableEvent(.50,"Ein Bär wird zu Ihnen teleportiert, schlitzt Sie auf und verschwindet. -100 HP",-100);
	    Event instantDeathPotionEvent = new PercentCurrentEvent(.1,"Ihre Sicht verdunkelt sich, Geräusche verschmelzen und Sie fallen.",-100);
	    
	    List<Event> eventsChaosPotion = new ArrayList<Event>(){
	    	{
	    		add(saleRemoveFish);
	    		add(chaosPotionEvent);
	    		add(healPotionEvent);
	    		add(fireWardCheck2);
	    		add(summonBearPotionEvent);
	    		add(saleCounterEvent);
	    		add(instantDeathPotionEvent);
	    	}
	    };
	    Event saleBuyPotion = new MultipleEvents(eventsChaosPotion);
	    Event saleItemPotion = new OnceEvent(new ItemCheckEvent(1,"",fish,saleBuyPotion,new BasicEvent(1,"")),"Sie haben kein Fisch","Kein Bestand");
	    
	    
	    
	    Road[] pondRoads = {
	    		new Road("gehe zurück",places.get(5)),
	    		new Road("Rüstung kaufen",places.get(13),saleItemArmor),
	    		new Road("Feuerschutz kaufen",places.get(13),saleItemWard),
	    		new Road("Zaubertrank kaufen",places.get(13),saleItemPotion)
	    };
	    
	    
	    
	    
	    Event removeSaleArmor = new ChangeTargetEvent(1,"",0,pondRoads[1],places.get(13),"Rüstung nicht auf Lager");
	    eventsArmor.add(removeSaleArmor);
	    Event removeSaleWard = new ChangeTargetEvent(1,"",0,pondRoads[2],places.get(13),"Feuerschutz nicht auf Lager");
	    eventsWard.add(removeSaleWard);
	    
	    int rand = (int)(Math.random()*(places.size()-1));
	    Event tempEventTele = new ChangeTargetEvent(1,"Die Welt um Sie herum verändert sich, und Sie befinden sich nun an einem neuen Ort. --> "+places.get(rand).toString(),0,pondRoads[3],places.get(rand));
	    Event teleportPotionEvent = new TemporaryEvent((ReversibleEvent) tempEventTele);
	    updatables.add((Updatable) teleportPotionEvent);
	    Event removeSalePotion = new ChangeTargetEvent(1,"",0,pondRoads[3],places.get(13),"Zaubertrank nicht auf Lager");
	    eventsChaosPotion.add(removeSalePotion);
	    eventsChaosPotion.add(teleportPotionEvent);
	    
	    places.get(13).setRoads(pondRoads);
	    places.get(13).addEvent(saleMultiple);    
	    
	    places.get(12).setRoads(restRoads);;
	    places.get(12).addEvent(restEndEvent);
	}
	
	public Place getStart() {
		return start;
	}
	
	private void setStart(Place _start) {
		this.start = _start;
	}
	
	public void setStart(String name) {
		if(name.equals("Random")) {
			int rand = (int)(Math.random()*(starts.size()-1));
			setStart(starts.get(rand));
		}
		else {
			for(Place place : places) {
				if(name.equals(place.getName())) {
				setStart(place);
				}
			}
		}
	}
	
	public Boolean isEnd(Place here){
		if(here.getRoads() == null) {
			return true;
		}
		return false;
	}
	
	private void updateTargets() {
		for(Place place : places) {
			if(place.getRoads() != null) {
				for(Road road : place.getRoads()) {
					targets.add(road.getTarget().getName());
				}
			}
		}
	}
	
	private void updateStarts() {
		String placeName;
		for(Place place : places) {
			placeName = place.getName();
			if(!targets.contains(placeName)) {
				starts.add(placeName);
			}
		}
	}
	
	public List<String> getStarts(){
		return starts;
	}
	
	public void addPlace(Place place) {
		places.add(place);
	}
	
	public List<Place> getPlaces(){
		return places;
	}
	
	public List<Item> getItems(){
		return worldItems;
	}
	
	public void update() {
		for( Updatable updatable:updatables) {
			updatable.update();
		}
	}
}