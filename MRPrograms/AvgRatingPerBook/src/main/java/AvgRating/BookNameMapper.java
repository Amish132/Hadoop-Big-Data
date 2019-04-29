package AvgRating;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class BookNameMapper extends Mapper<LongWritable, Text, LongWritable, Text>{
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String[] tokens = value.toString().split("\t");
		
		String bookName = tokens[1];
		String isbn = tokens[0];
		myBook book = new myBook();
		book.setBookName(new Text(bookName));
		book.setIsbn(new Text(isbn));
		//System.out.println(u.getUserid());
		context.write(new LongWritable(Long.parseLong(isbn)), new Text("B" + isbn));
	}
}
