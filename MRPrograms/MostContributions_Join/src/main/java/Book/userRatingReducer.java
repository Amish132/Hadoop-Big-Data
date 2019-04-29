package Book;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class userRatingReducer extends Reducer<Text, LongWritable, Text, LongWritable>{

	static TreeMap<Text, LongWritable> userMap = new TreeMap<Text, LongWritable>();

	protected void reduce(Text key, Iterable<LongWritable> value,
			Reducer<Text, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {


		long count=0;

		for(LongWritable c: value){
			count += c.get();
		}

		userMap.put(key, new LongWritable(count));
		context.write(key, new LongWritable(count));
	}

	/*
	public static <Text, LongWritable extends Comparable<LongWritable>> TreeMap<Text, LongWritable> sortByValues(final TreeMap<Text, LongWritable> map) {

		Comparator<Text> valueComparator = new Comparator<Text>() {
			public int compare(Text k1, Text k2) {
				int compare = 
						map.get(k1).compareTo(map.get(k2));
				if (compare == 0) 
					return 0;
				else 
					return compare;
			}
		};

		TreeMap<Text, LongWritable> sortedByValues = new TreeMap<Text, LongWritable>(valueComparator);
		sortedByValues.putAll(map);
		return sortedByValues;
	}

	public static TreeMap<Text, LongWritable> sortByValue(TreeMap<Text, LongWritable> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<Text, LongWritable> > list = 
               new LinkedList<Map.Entry<Text, LongWritable> >(hm.entrySet()); 

        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<Text, LongWritable> >() { 
            public int compare(Map.Entry<Text, LongWritable> o1,  
                               Map.Entry<Text, LongWritable> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 

        // put data from sorted list to hashmap  
        TreeMap<Text, LongWritable> sortedByValues = new TreeMap<Text, LongWritable>(); 
        for (Map.Entry<Text, LongWritable> aa : list) { 
        	sortedByValues.put(aa.getKey(), aa.getValue()); 
        } 
        return sortedByValues; 
    } 

	private static LongWritable one  = new LongWritable();




	@Override
	protected void cleanup(Reducer<Text, LongWritable, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		TreeMap newUserMap = sortByValue(userMap);
		int count=0;

		Set set = newUserMap.entrySet();

	    // Obtain an Iterator for the entries Set
		Iterator it = set.iterator();

	    while(it.hasNext() ) {
	        Map.Entry me = (Map.Entry)it.next();
	        //context.write((Text)me.getKey(),(LongWritable)me.getValue());
	        count++;
	      }

	}
	 */

}
