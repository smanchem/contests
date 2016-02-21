import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class KthAncestor {

    public static void main(String[] args) throws java.io.IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		
		while( T > 0 ){
			T--;
			//HashMap<Integer, Integer> tree = new HashMap<Integer, Integer>();
			int[] tree = new int[100001];
			for( int i = 0; i < 100001; i++ )
				tree[i] = -1;
			
			int P = Integer.parseInt(br.readLine());
            int i = 0;
            String[] Edges = new String[P];
			while( P > 0 ){
				P--;
                Edges[i++] =  br.readLine();
            }
            
            P = i;
            i = 0;
            
            while( P > 0 ){
                P--;
				String[] indices = Edges[i++].split("\\s");
				int child = Integer.parseInt(indices[0]);
				int parent = Integer.parseInt(indices[1]);
				
				// Add node and it's parent. Store the parent of each node in the HashMap as <child, parent>
				tree[child] = parent;	
			}
			
			int Q = Integer.parseInt(br.readLine());
		    i = 0;
            
			String[] Queries = new String[Q];
			while( Q > 0 ){
				Q--;
				Queries[i++] = br.readLine();
			}
			
			Q = i;
			i = 0;
			while( Q > 0 ){
				Q--;
				String[] indices = Queries[i++].split("\\s");
				int queryType = Integer.parseInt(indices[0]);
				if( queryType == 0 ){
					int parent = Integer.parseInt(indices[1]);
					int child = Integer.parseInt(indices[2]);
					
					//tree.put(child, parent);
					tree[child] = parent;
				} else if( queryType == 1 ){
					int leafnode = Integer.parseInt(indices[1]);	// Only leaf nodes can be removed. Not being checked.
					
					//tree.remove(leafnode);
					tree[leafnode] = -1;
				} else if( queryType == 2 ){
					int node = Integer.parseInt(indices[1]);
					int K = Integer.parseInt(indices[2]);
					
					result.append(kthAncestor( tree, node, K ) + "\n");
					//System.out.println(kthAncestor);
				}
			}
		}
		System.out.println(result);
		
	}
	
	public static int kthAncestor( int[] tree, int node, int K ){
		if( tree[node] == -1 )
			return 0;
		
		int parent = tree[node];
		while ( K > 1 ){
			K--;
			if( parent == 0 )
				return 0;
			//System.out.println("Parent: " + parent);
			parent = tree[parent];
		}
		return parent;
	}

}
