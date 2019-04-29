package AvgRating;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class BookRatingMapper extends Mapper<Text, Text, LongWritable, myBook>{
	@Override
	protected void map(Text key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String bookInfo = value.toString();
		String[] tokens = bookInfo.split("\t");
		String rating = tokens[1];
		String noOfBooks = tokens[0];
		myBook book = new myBook();
		book.setRating(new FloatWritable(Float.parseFloat(rating)));
		book.setNoBooks(new IntWritable(Integer.parseInt(noOfBooks)));
		//System.out.println(u.getUserid());
		context.write(new LongWritable(Long.parseLong(key.toString())), book);
	}
}
