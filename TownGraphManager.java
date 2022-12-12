package application;

import java.util.ArrayList;
import java.util.Collections;

public class TownGraphManager implements TownGraphManagerInterface{

	private Graph graph = new Graph();
	
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		if(graph.addEdge(new Town(town1), new Town (town2), weight, roadName) != null) {
			return true;
		}
		return false;
	}

	@Override
	public String getRoad(String town1, String town2) {
		return graph.getEdge(new Town(town1), new Town(town2)).getName();
		
	}

	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}

	@Override
	public Town getTown(String name) {
		Town t = new Town(name);
		for(Town vertex : graph.towns) {
			if(vertex.equals(t)) {
				return t;
			}
		}
		return null;
	}

	@Override
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(new Town(town1), new Town(town2));
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> allroads = new ArrayList<>();
		for(Road r : graph.roads) {
			allroads.add(r.getName());
		}
		Collections.sort(allroads);
		return allroads;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		return graph.removeEdge(new Town(town1), new Town(town2), 0, road)!= null;
			
	}

	@Override
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> alltowns = new ArrayList<String>();
		for(Town t : graph.towns) {
			alltowns.add(t.getName());
			
		}
		Collections.sort(alltowns);
		return alltowns;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return graph.shortestPath(new Town(town1), new Town(town2));
	}

}
