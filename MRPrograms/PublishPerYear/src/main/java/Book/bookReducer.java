package Book;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class bookReducer extends Reducer<Text, LongWritable, Text, LongWritable>{

	LongWritable l = new LongWritable();
	
	@Override
	protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException{
	long sum = 0;
	//System.out.print("inside reducer");
	for(LongWritable val:values) {
		sum+=val.get();
		
	}
		l.set(sum);
		context.write(key,l);
	}
}
