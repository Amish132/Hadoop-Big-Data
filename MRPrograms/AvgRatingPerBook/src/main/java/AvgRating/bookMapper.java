package AvgRating;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

public class bookMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	static int i=0;
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	try {
		
		String[] tokens = value.toString().split(",");
		//System.out.print(tokens[0]);
		//System.out.print(tokens[1]);
		//System.out.println(tokens[5]);
		Text bookIsbn = new Text(tokens[1]);
		IntWritable rating = new IntWritable(Integer.parseInt(tokens[2]));
		
		//Considered only those values where Ratings were Available, i.e. greater than 0
		if(rating.compareTo(new IntWritable(0))==1)
		context.write(bookIsbn,rating);
	}
	catch(NumberFormatException e) {
		System.out.println("Header");
	}
	}
	
	
	
}
