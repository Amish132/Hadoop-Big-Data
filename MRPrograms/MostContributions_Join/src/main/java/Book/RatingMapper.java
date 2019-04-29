package Book;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class RatingMapper extends Mapper<LongWritable, Text, LongWritable, User>{

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] tokens = value.toString().split("\t");
		String count = tokens[1];
		//System.out.println(count);
		String userID = tokens[0];
		//System.out.println(userID);
		User u = new User();
		u.setCount(new LongWritable(Long.parseLong(count)));
		u.setUserid(userID);
		//System.out.println(u.getUserid());
		context.write(new LongWritable(Long.parseLong(userID)), u);
	}

}
