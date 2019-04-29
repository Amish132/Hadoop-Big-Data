package Authors;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class authorMapper extends Mapper<LongWritable, Text, Text, LongWritable>{
	
	final static LongWritable one = new LongWritable(1);
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	try {
		
		String[] tokens = value.toString().split(",");
		Text author = new Text(tokens[2]);
		
		//Considered only those values where Ratings were Available, i.e. greater than 0
		
		context.write(author, one);
	}
	catch(NumberFormatException e) {
		System.out.println("Header");
	}
	}
}
