import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Huffman Encoding.
 */
public class Huffman {
	
	/**
	 * Builds the frequency table for each charactr in the given string. 
	 * Map<Character, Integer> should be a mapping of characters to their
	 * respective frequencies.
	 *
	 * @param str The String to be encoded.
	 */
	public static Map<Character, Integer> buildFrequencyTable(String str) {
		Map map = new HashMap<Character, Integer>();
		Set set= new HashSet();
		for(int i=0; i<str.length(); i++)
		{
			set.add(str.charAt(i));
		}
		int ctr=0;
		Object[] c = set.toArray();
		for(int i=0; i< c.length; i++)
		{
			char a = (Character) c[i];
			ctr=0;
			for(int j=0;j<str.length(); j++)
			{
				if(a==str.charAt(j))
					ctr++;
			}
			map.put(a, ctr);
		}
		return map;
	}

	/**
	 * Builds the encoder map that pairs a Character with it's corresponding 
	 * Huffman encoding. Map<Character, String> should be a map of characters
	 * to their Huffman encoding.
	 *
	 * @param frequencyTable The frequency table of characters
	 */
	public static Map<Character, String> buildEncoder(Map<Character, Integer> frequencyTable) {
		HuffmanTree tree = buildHuffmanTree(frequencyTable);
		Map<Character, String> map = tree.encode();
		return map;
		
	}

	/**
	 * Builds a huffman tree that can be used for decoding from the frequency table
	 * 
	 * @param frequencyTable The frequency table of characters
	 */
	public static HuffmanTree buildHuffmanTree(Map<Character, Integer> frequencyTable) {
		HuffmanTree tree = new HuffmanTree(frequencyTable);
		return tree;
	}
	
	/**
	 * Encodes a String
	 *
	 * @param String input The String to be encoded.
	 * @param Map encoder an encoder that can be used to encode the input
	 * @return String The resulting encoded String.
	 */
	public static String encodeString(String input, Map<Character, String> encoder) {
		if(input.length()==0)
			return "";
		if(input.length()==1)
			return "0";
		if(input == null)
			return null;
		String s = "";
		for(int i =0; i<input.length(); i++)
		{
			s= s+ encoder.get(input.charAt(i));
		}
		return s;
	}
		
	/**
	 * Decodes a String
	 *
	 * @param String input The String to be decoded.
	 * @param HuffmanTree tree A huffman tree generated from the input
	 * @return String The decoded String.
	 */
	public static String decodeString(String input, HuffmanTree tree) {
		if(input.length()==0)
			return "";
		if(input==null)
			return null;
		String s ="";
		char temp = 0;
		Map<Character, String> map = tree.encode();
		Set<Character> set  = map.keySet();
		Object[] c = set.toArray();
		int i=0;
		int j=1;
		while(i<input.length()||j<input.length())
		{
				if(map.containsValue(input.substring(i,j)))
				{
					String a = input.substring(i,j);
					for(int k=0;k<c.length;k++)
					{
						if(map.get(c[k]).equals(a))
						{
							temp = (Character)c[k];
						}
					}
					
					s= s+temp;
					i=j;
					j=j+1;
				}
				else
				{
					j++;
				}
		}
		return s;
	}
}