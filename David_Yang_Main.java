import java.io.*;

public class David_Yang_Main {
	public static void main (String args[]) throws IOException {
		BufferedReader input=new BufferedReader(new FileReader(args[0]));
		BufferedWriter SSSOUT=new BufferedWriter(new FileWriter(args[1]));
		BufferedWriter debugOUT=new BufferedWriter(new FileWriter(args[2]));
		int num=Integer.valueOf(input.readLine());
		input.close();
		for(int i=1;i<=num;i++) {
			input=new BufferedReader(new FileReader(args[0]));
			int reset=Integer.valueOf(input.readLine());
			David_Yang_DijktraSSS graph=new David_Yang_DijktraSSS(num,i);
			graph.loadCostMatrix(input);
			graph.setBest();
			graph.setFather();
			graph.setToDo();
			while(graph.doneToDo()!=true) {
				graph.minNode=graph.findMinNode();
				graph.toDo[graph.minNode]=0;
				graph.debugPrint(debugOUT);
				while(graph.currentNode<=graph.numNodes) {
					if(graph.toDo[graph.currentNode]>0) {
						graph.newCost=graph.computeCost(graph.minNode, graph.currentNode);
						if(graph.newCost<graph.best[graph.currentNode]) {
							graph.best[graph.currentNode]=graph.newCost;
							graph.father[graph.currentNode]=graph.minNode;
							graph.debugPrint(debugOUT);
						}
					}graph.currentNode++;
				}graph.currentNode=1;
			}
			graph.currentNode=1;
			while(graph.currentNode<=graph.numNodes) {
				graph.printShortestPath(graph.currentNode, graph.sourceNode, SSSOUT);
				graph.currentNode++;
			}
			input.close();
		}
		SSSOUT.close();
		debugOUT.close();
	}
}
