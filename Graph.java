package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Graph implements GraphInterface<Town,Road>{

	public Town destination;
	public Set<Town> towns = new HashSet<>();
	public Set<Road> roads = new HashSet<>();
	public Stack<Road> shortestPath = new Stack<Road>();
	
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if(sourceVertex == null || destinationVertex == null)
			return null;
		Road road = null;
		for(Road edges : roads ) {
			if(edges.contains(destinationVertex)&& edges.contains(sourceVertex)) {
				road = edges;
			}
				
		}
		return road;
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) throws IllegalArgumentException, NullPointerException{
		if(sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException();
		}
		if(!containsVertex(sourceVertex) || !containsVertex(destinationVertex)) {
			throw new IllegalArgumentException();
		}
		Road road = new Road(sourceVertex, destinationVertex, weight, description);
		roads.add(road);
		return road;
	}

	@Override
	public boolean addVertex(Town v) throws NullPointerException{
		if(v == null) {
			throw new NullPointerException();
		}
		if(towns.contains(v)) {
			return false;
		}
		else {
			Town town = new Town(v);
			towns.add(town);
			return true;
		}
		
		
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		for(Road r : roads) {
			if(r.contains(sourceVertex) && r.contains(destinationVertex)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsVertex(Town v) {
		if(towns.contains(v)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Set<Road> edgeSet() {
		return roads;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		if(vertex== null) {
			throw new NullPointerException();
		}
		if(!towns.contains(vertex)) {
			throw new IllegalArgumentException();
		}
		Set<Road> roadSet = new HashSet<Road>();
		for(Road r  : roads) {
			if(r.contains(vertex)) {
				roadSet.add(r);
			}
		}
		return roadSet;
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road road = null;
		for(Road r : roads) {
			if(r.contains(destinationVertex) && r.contains(sourceVertex) && (weight > -1) && description != null) {
				road = r;
				
			}
		}
		if(roads.remove(road)) {
			return road;
		}
		return null;
	}

	@Override
	public boolean removeVertex(Town v) {
		if(v == null) {
			return false;
		}
		else {
			towns.remove(v);
			return true;
		}
	}

	@Override
	public Set<Town> vertexSet() {
		return towns;
		
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		ArrayList<Road> roadPath = new ArrayList<Road>();
		ArrayList<String> shortPath = new ArrayList<String>();
		
		for(Road r : shortestPath) {
			if(r.getDestination().equals(destinationVertex)) {
				roadPath.add(r);
			}
		}
		for(Road edges : roadPath) {
			if(!destinationVertex.equals(sourceVertex)) {
				shortPath.add(edges.getSource().getName() + " via " + edges.getName() + " to " + edges.getDestination().getName() + " " + edges.getWeight() + " mi");
			}
			else if(destinationVertex.equals(sourceVertex)) {
				break;
			}
			
		}
		return shortPath;
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		shortestPath = new Stack<Road>();
		int count = 0;
		int cost = 999999;
		Town newVertex = sourceVertex;
		
		Set<Road> possRoad = edgesOf(sourceVertex);
		Road aEdge = null;
		while(count <=1) {
			for(Road edges: possRoad) {
				if(edges.getSource().equals(newVertex)) {
					if(edges.getWeight()<cost) {
						aEdge = edges;
					}
				}
			}
			shortestPath.add(aEdge);
			newVertex = aEdge.getDestination();
			possRoad = edgesOf(newVertex);
			
			if(aEdge.getSource().equals(sourceVertex)) {
				count++;
			}
		}
	}

}
