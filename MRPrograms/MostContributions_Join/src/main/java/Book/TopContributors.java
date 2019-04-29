package Book;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TopContributors extends Reducer<NullWritable, Text, Text, LongWritable>{
	//public class TopContributors extends Reducer<LongWritable, Text, LongWritable, Text>{
	public static TreeMap<LongWritable , Text> userMap = new TreeMap<LongWritable , Text>();


	public void reduce(NullWritable key, Iterable<Text> value,
			Context context) throws IOException, InterruptedException {
		//try {

		for(Text line:value) {
			String l=line.toString();
			//System.out.println(l);
			if(l.length()>0){
				String[] tokens=l.split("\t");
				LongWritable count= new LongWritable(Long.parseLong(tokens[0]));	
				//System.out.println(count.toString()+"              " +new Text(tokens[1]));
				userMap.put(count, new Text(tokens[1]));
			}
		}
		/*
			Iterator<Entry<LongWritable , Text>> iter = userMap.entrySet().iterator();
			Entry<LongWritable , Text> entry = null;

			while(userMap.size()>10){
				entry = iter.next();
				iter.remove();            
			}

			for (Text t : userMap.descendingMap().values()) {
				// Output our ten records to the file system with a null key
				System.out.println(key.toString() + "             "+t.toString());
				context.write(key, t);
			}
		 */
		//}
		/*
		catch(NumberFormatException e) {
			System.out.println("");
		}*/

	}

	@Override
	protected void cleanup(Context context)
			throws IOException, InterruptedException {
		
		for (Entry<LongWritable,Text> t : userMap.descendingMap().entrySet()) {
			// Output our ten records to the file system with a null key
			//System.out.println(t.getValue()+ "             "+t.getKey().toString());
			context.write(t.getValue(), t.getKey());
		}
	}



}
