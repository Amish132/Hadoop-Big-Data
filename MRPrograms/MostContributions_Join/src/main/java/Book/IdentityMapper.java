package Book;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class IdentityMapper extends Mapper<LongWritable, Text, NullWritable, Text>{
//	public class IdentityMapper extends Mapper<LongWritable, Text, LongWritable, Text>{
	TreeMap<LongWritable, Text> userMap = new TreeMap<LongWritable, Text>();

	//will pass only the topten values to the reducer
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		try {

			String[] tokens = value.toString().split("\t");
			//System.out.println(tokens[0]+"     "+tokens[1]);
			userMap.put(new LongWritable(Long.parseLong(tokens[1])), new Text(tokens[0]));

			Iterator<Entry<LongWritable , Text>> iter = userMap.entrySet().iterator();
			Entry<LongWritable , Text> entry = null;

			while(userMap	.size()>10){
				entry = iter.next();      
				iter.remove();         
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Header");
		}

	}

	protected void cleanup(Context context) throws IOException, InterruptedException {

		// Output our ten records to the reducers with a null key
		
		for(Entry<LongWritable,Text> e :userMap.entrySet()) {
			context.write(NullWritable.get(), new Text(e.getKey().toString()+ "\t"+ e.getValue().toString()));
			//System.out.println(e.getKey().toString()+ "\t"+ e.getValue().toString());
		}
		
		/*for (Text t:userMap.values()) {
			//context.write(NullWritable.get(), t);
			context.write(userMap.entrySet(), t);
			//System.out.println(t.toString());
		}*/
	}                 


}


