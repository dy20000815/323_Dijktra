import java.io.*;

public class David_Yang_DijktraSSS {
	int numNodes;
	int sourceNode;
	int minNode;
	int currentNode;
	int newCost;
	int[][] costMatrix;
	int[] father;
	int[] toDo;
	int[] best;
	
	David_Yang_DijktraSSS(int n, int source) {
		newCost=0;
		currentNode=1;
		sourceNode=source;
		numNodes=n;
		costMatrix=new int[n+1][n+1];
		father=new int[n+1];
		toDo=new int[n+1];
		best=new int[n+1];
		for(int i=1;i<=numNodes;i++) {
			for(int j=1;j<=numNodes;j++) {
				costMatrix[i][j]=9999;
			}
			costMatrix[i][i]=0;
		}
	}
	
	void loadCostMatrix(BufferedReader input) throws IOException {
		int cost;
		int row, col;
		String line;
		while ((line=input.readLine())!=null) {
			String words[]=line.split("\\s+");
			row=Integer.valueOf(words[0]);
			col=Integer.valueOf(words[1]);
			cost=Integer.valueOf(words[2]);
			costMatrix[row][col]=cost;
		}
	}
	
	void setBest() {
		for(int i=1; i<=numNodes; i++) {
			best[i]=costMatrix[sourceNode][i];
		}
	}
	
	void setFather() {
		for(int i=1; i<=numNodes; i++) {
			father[i]=sourceNode;
		}
	}
	
	void setToDo() {
		for(int i=1; i<=numNodes; i++) {
			toDo[i]=1;
		}toDo[sourceNode]=0;
	}
	
	int findMinNode() {
		int minCost=99999;
		int minNode=0;
		for(int i=1;i<=numNodes;i++) {
			if(toDo[i]>0) {
				if(best[i]<minCost) {
					minCost=best[i];
					minNode=i;
				}
			}
		}return minNode;
	}
	
	int computeCost(int minNode, int currentNode) {
		return best[minNode]+costMatrix[minNode][currentNode];
	}
	
	void debugPrint(BufferedWriter output) throws IOException {
		output.write("sourceNode is: "+sourceNode+"\n");
		output.write("currentNode is: "+currentNode+"\n");
		output.write("father array is: \n");
		for(int i=1; i<=numNodes;i++) {
			output.write(father[i]+", ");
		}output.write("\n");
		output.write("best array is: \n");
		for(int i=1; i<=numNodes;i++) {
			output.write(best[i]+", ");
		}output.write("\n");
		output.write("toDo array is: \n");
		for(int i=1; i<=numNodes;i++) {
			output.write(toDo[i]+", ");
		}output.write("\n");
	}
	
	boolean doneToDo() {
		for(int i=1; i<=numNodes; i++) {
			if(toDo[i]==1) {
				return false;
			}
		}return true;
	}
	
	void printShortestPath(int curr, int source, BufferedWriter out) throws IOException {
		out.write("SoruceNode: "+source+"\n");
		out.write("The path from "+source+"to "+curr+": ");
		int check=curr;
		while(check!=source) {
			out.write(check+" <- ");
			check=father[check];
		}out.write(source+": cost= "+best[curr]+"\n");
	}
}
