package com.cs201.barcrawl.util.routing;

import com.cs201.barcrawl.models.Business;

import java.util.*;

public class BusinessGraph {
    private HashMap<Business,BusinessVertex> vertices = new HashMap();
    private ArrayList<BusinessEdge> edges = new ArrayList<BusinessEdge>();

    public int numVertices(){
        return vertices.size();
    }

    public int numEdges(){
        return edges.size();
    }

    public void addVertex(Business business){
        BusinessVertex businessVertex = new BusinessVertex(business);
        vertices.put(business, businessVertex);
    }

    public BusinessVertex getVertex(Business business){
        return vertices.get(business);
    }

    public void addEdge(Business business1, Business business2, int cost){
        if(!vertices.containsKey(business1)){
            addVertex(business1);
        }
        if(!vertices.containsKey(business2)){
            addVertex(business2);
        }

        BusinessVertex v1 = getVertex(business1);
        BusinessVertex v2 = getVertex(business2);
        BusinessEdge edge = new BusinessEdge(v1, v2, cost);

        // Each Vertex is given this edge as it is undirected
        v1.addEdge(v2, edge);
        v2.addEdge(v1, edge);
        edges.add(edge);
    }

    public List<BusinessVertex> nearestNeighbourTraversal(BusinessVertex start){
        List<BusinessVertex> order = new ArrayList<BusinessVertex>();
        Set<BusinessVertex> visited = new HashSet<BusinessVertex>();

        order.add(start);
        visited.add(start);

        BusinessVertex next= start;

        while(visited.size() != numVertices()){
            next = nearestUnvisitedNeighbour(next, visited);
            order.add(next);
            visited.add(next);
        }

        return order;
    }

    private BusinessVertex nearestUnvisitedNeighbour(BusinessVertex start, Set<BusinessVertex> known){
        ArrayList<BusinessVertex> nearestVertices = start.orderByDistance();
        for(BusinessVertex v : nearestVertices){
            if(!known.contains(v)){
                return v;
            }
        }
        return null;
    }
}
