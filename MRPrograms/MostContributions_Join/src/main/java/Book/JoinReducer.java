package Book;

import java.io.IOException;
import java.util.Collection;
import java.util.TreeMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JoinReducer extends Reducer<LongWritable, User, Text, Text>{

	static TreeMap<Text, LongWritable> userMap = new TreeMap<Text, LongWritable>();
	static User user = new User();
	protected void reduce(LongWritable key, Iterable<User> value,
			Reducer<LongWritable, User, Text, Text>.Context context) throws IOException, InterruptedException {
		//System.out.println(u.age + " " + u.count + " " + u.location + " " + u.rating);

		int count=0;

		for(User u:value) {

			if(count>0) {
				context.write(u.userid, new Text("Total Books Reviewed: "+u.count+"\t ,Location: "+user.getLocation() + "\t\t\t\t ,Age: " + user.age));	
			}
			else {
				user.setLocation(new Text(u.location));
				user.setAge(new Text(u.age));
				count++;
			}

		}

	}


}
