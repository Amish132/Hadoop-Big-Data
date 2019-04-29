package AvgRating;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class topTenReducer extends Reducer<NullWritable, myBook, Text, Text>{

	public static TreeMap<FloatWritable, myBook> top = new TreeMap<FloatWritable, myBook>();
	
	@Override
	protected void reduce(NullWritable key, Iterable<myBook> value,
			Reducer<NullWritable, myBook, Text, Text>.Context context) throws IOException, InterruptedException {

		
		for(myBook b:value) {
			myBook book = new myBook();
			book.isbn.set(b.isbn);
			book.noBooks.set(b.noBooks.get());
			book.rating.set(b.rating.get());
			top.put(new FloatWritable(book.rating.get()),book);
		}

		
		//context.write(new Text(myBook.isbn), myBook);
}
	
	@Override
	protected void cleanup(Reducer<NullWritable, myBook, Text, Text>.Context context)
			throws IOException, InterruptedException {
		for(Entry<FloatWritable, myBook> k: top.descendingMap().entrySet()) {
				context.write(new Text(k.getValue().isbn),new Text(k.getValue().noBooks + "\t" + k.getValue().rating));	
			
		}
	}
	
}
