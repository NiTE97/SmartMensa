package de.stl.saar.internetentw1.model;

public class Room {
	private int building;
	private int floor;
	private int room;
	
	public Room(int building, int floor, int room) {
		super();
		this.building = building;
		this.floor = floor;
		this.room = room;
	}

	public int getBuilding() {
		return building;
	}
	
	public void setBuilding(int building) {
		this.building = building;
	}
	
	public int getFloor() {
		return floor;
	}
	
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public int getRoom() {
		return room;
	}
	
	public void setRoom(int room) {
		this.room = room;
	}
}
