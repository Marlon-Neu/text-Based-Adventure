package text_Base_Adventure;

public enum Ending {
	won("Gratulation %s sie haben das Spiel gemeistert"),
    hpLost("%s keine HP mehr hat. Spiel Vorbei."),
    eventLost("%s, etwas passiert. Spiel Vorbei");
	
	private String message;
	
	Ending(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
