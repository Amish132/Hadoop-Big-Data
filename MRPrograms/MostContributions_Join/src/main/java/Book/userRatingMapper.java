package Book;
import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class userRatingMapper extends Mapper<LongWritable, Text, Text, LongWritable>{

	private static LongWritable one = new LongWritable(1);
	TreeMap<Text, LongWritable> userMap = new TreeMap<Text, LongWritable>();

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		try {

			String[] tokens = value.toString().split(",");

			User user = new User();

			user.userid = new Text(tokens[0]);
			user.isbn = new Text(tokens[1]);
			user.rating= new FloatWritable(Float.parseFloat(tokens[2]));

			//Considered only those values where Ratings were Available, i.e. greater than 0
			context.write(new Text(tokens[0]),one);
		}
		catch(NumberFormatException e) {
			System.out.println("Header");
		}
	}



}
