package application;


public class Town implements Comparable<Town>{

	public String name;
	
	public Town(String name) {
		this.name = name;
	}
	
	public Town(Town templateTown) {
		this.name = templateTown.name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public int compareTo(Town o) {
		if(this.name.equals(o.name)) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(obj == this) {
			return true;
		}
		if(!(obj instanceof Town)) {
			return false;
		}
		Town town = (Town) obj;
		return this.name.equals(town.name);
	}
	
}
