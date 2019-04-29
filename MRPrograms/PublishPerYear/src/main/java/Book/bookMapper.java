package Book;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

public class bookMapper extends Mapper<LongWritable, Text, Text, LongWritable>{
	
	private static final LongWritable one = new LongWritable(1);
	static int i=0;
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	//try {
		String line = value.toString();
		String[] tokens = line.split(",");
		Text publishYear = new Text(tokens[3]);
		//System.out.println(tokens[2]);
		if(!(publishYear.equals(new Text(""))) && !(publishYear.equals(new Text("Year-Of-Publication")))  && !(publishYear.equals(new Text("0"))))
		context.write(publishYear,one);
	/*}
	catch(Exception e) {
		//System.out.print(i++);
	}*/
	}
}
