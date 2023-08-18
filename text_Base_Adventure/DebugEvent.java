package text_Base_Adventure;


//TBH This is dumb.
public class DebugEvent implements Event {

	private String choice;
	public DebugEvent(String choice) {
		this.choice = choice;
	}
	@Override
	public String apply(Player player) {
		String msg =  ((DebugPlayer) player).debug(choice);
		return msg;
	}

	@Override
	public String getName() {
		return "Debug";
	}

}
