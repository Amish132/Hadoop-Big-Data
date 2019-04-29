package AvgRating;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class BookNameReducer extends Reducer<LongWritable, Text, Text, Text>{

	static TreeMap<LongWritable, myBook> bookMap = new TreeMap<LongWritable, myBook>();
	static myBook book = new myBook();
	protected void reduce(LongWritable key, Iterable<Text> value,
			Reducer<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
		//System.out.println(u.age + " " + u.count + " " + u.location + " " + u.rating);

		int count=0;
		

	}


}
