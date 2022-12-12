package application;

public class Road implements Comparable<Road>{

	public Town destination;
	public int weight;
	public String name;
	public Town source;
	
	public Road(Town source, Town destination, int degree, String name) {
		this.weight = degree;
		this.destination = destination;
		this.name = name;
		this.source = source;	
	}
	
	public Road(Town source, Town destination, String name) {
		this.weight = 1;
		this.source = source;
		this.destination = destination;
		this.name = name;
	}
	
	public boolean contains(Town town) {		
		if(source.getName().equals(town.getName()) || destination.getName().equals(town.getName())){
			return true;
		}
		else {
			return false;
		}
	}
	
	public Town getSource() {
		return source;
	}

	public void setSource(Town source) {
		this.source = source;
	}

	public Town getDestination() {
		return destination;
	}

	public void setDestination(Town destination) {
		this.destination = destination;
	}

	public int getWeight() {
		return weight;
	}
	public int setWeight() {
		return this.weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return source.getName() + "via" + name + " to " + destination.getName()	+ " " + weight + " mi";
	}
	
	@Override
	public int compareTo(Road o) {
		if(name.equals(o.getName())) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	@Override
	public boolean equals(Object r) {
		if(r == null) {
			return false;
		}
		if(r == this) {
			return true;
		}
		if(!(r instanceof Road)) {
			return false;
		}
		Road road = (Road) r;
		
		if(this.source.equals(road.source) && this.source.equals(road.destination) 
				|| this.source.equals(road.destination) && this.source.equals(road.source)) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
