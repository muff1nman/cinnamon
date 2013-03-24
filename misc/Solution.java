package misc;

public class Solution {
	private String person;
	private String weapon;
	private String room;
	

	public Solution(String person, String weapon, String room) {
		super();
		this.person = person;
		this.weapon = weapon;
		this.room = room;
	}
	
	public boolean checkSolution(String person, String weapon, String room) {
		if(!this.person.equals(person))
			return false;
		if(!this.weapon.equals(weapon))
			return false;
		if(!this.room.equals(room))
			return false;
		return true;
	}
}
