package Authors;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class authorReducer extends Reducer<Text, LongWritable, Text, LongWritable>{

	static TreeMap<Integer, String> authorMap = new TreeMap<Integer, String>();
	
	@Override
	protected void reduce(Text author, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException{
		
	int sum =0;

	for(LongWritable val:values) {
		
		sum += val.get();
	}
	authorMap.put(sum, author.toString());
	if(authorMap.size()>20)
	authorMap.remove(authorMap.firstKey());
	
	}
	
	@Override
	protected void cleanup(Reducer<Text, LongWritable, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
	
		for(Integer k : authorMap.descendingKeySet()) {
				context.write(new Text(authorMap.get(k) + "\t"), new LongWritable(k));
			
		}
	}

}