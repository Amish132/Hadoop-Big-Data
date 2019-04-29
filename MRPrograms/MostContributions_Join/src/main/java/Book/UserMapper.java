package Book;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class UserMapper extends Mapper<LongWritable, Text, LongWritable, User>{
	//	public class IdentityMapper extends Mapper<LongWritable, Text, LongWritable, Text>{

	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		try {
			String[] token1 = value.toString().split(",\"");

			String userid = token1[0];
			//System.out.println(token1[1]);
			String[] token2 = token1[1].split("\",");
			//System.out.println(token1[1]);
			//System.out.println(token2[0]);
			String location = token2[0];
			//System.out.println(token2[1]);
			String age = token2[1];
			User u = new User();
			u.setUserid(userid);
			u.setLocation(new Text(location));
			if(age.equalsIgnoreCase("NULL"))
				u.setAge(new Text("NA"));
			else u.setAge(new Text(age));

			//System.out.println(u.getAge());
			context.write(new LongWritable(Long.parseLong(userid)),u);
		}
		catch(NumberFormatException e) {
			System.out.println("Header");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Header");
		}
	}
}