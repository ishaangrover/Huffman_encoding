import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;



public class HuffmanTree {
	
	
	public Node root;
	private List<Node> list;
	
	
	
	
	
	public HuffmanTree(Map<Character, Integer> frequencyTable)
	{
		root = null;
		list = new ArrayList();
		buildTree(frequencyTable);
		
	}
	
	public void buildTree(Map<Character, Integer> frequencyTable)
	{
		Set<Character> keys = frequencyTable.keySet();
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		for(char c: keys)
		{
			Node temp = new Node(c, frequencyTable.get(c));
			q.add(temp);
			list.add(temp);
		}
		
		root = null;
		
		if(q.size() == 1)
		{
			root = q.poll();
			root.code = "0";
			q.add(root);
			return;
		}
		else
		{
			while(q.size()>1)
			{
				Node a = q.poll();
				Node b = q.poll();
				root = new Node(a,b);
				q.add(root);
			}
		}
		Node a =root;
		code(a);
		
	}
	
		public Map<Character, String> encode() {
			Map<Character, String> map = new HashMap<Character, String>();
			for(int i = 0; i<list.size();i++){
				map.put(list.get(i).character, list.get(i).code);
			}
			return map;
		}
		
		public void code(Node current){
			if (current == null)
				return;
			else
			{
				if(current.left!=null){
					current.left.code =current.code + "0";
				}
				if(current.right!=null){
					current.right.code =current.code + "1";
				}
				code(current.left);
				code(current.right);
			}
		}
	
		
	public class Node implements Comparable<Node>{
		public String code= "";
		public int frequency;
		public char character;
		public Node left;
		public Node right;

		public Node(char a, int b)
		{
			this.character = a;
			this.frequency = b;
			this.left=null;
			this.right=null;
		}

		public Node(Node a, Node b)
		{
			this.character= 0;
			this.frequency = a.frequency +b.frequency;
			this.left = a;
			this.right = b;
		}

		@Override
		public int compareTo(Node n) {
			if(this.frequency == n.frequency)
				return 0;
			if(this.frequency < n.frequency)
				return -1;
			return 1;
		}
	}
}
