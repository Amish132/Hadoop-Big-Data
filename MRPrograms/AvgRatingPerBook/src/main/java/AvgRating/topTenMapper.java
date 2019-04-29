package AvgRating;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.lib.IdentityMapper;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class topTenMapper extends Mapper<LongWritable, Text, NullWritable, myBook> {

	TreeMap<FloatWritable,myBook> bookMap = new TreeMap<FloatWritable,myBook>();
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, NullWritable, myBook>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String tokens[] = line.split("\t");
		String isbn = tokens[0];
		int ratingCount = Integer.parseInt(tokens[1]);
		String Rating =tokens[2];
		
		myBook book = new myBook();
		book.isbn.set(isbn);
		book.noBooks.set(ratingCount);
		book.rating.set(Float.parseFloat(Rating));
		
		bookMap.put(new FloatWritable(Float.parseFloat(Rating)), book);
		
		Iterator<Entry<FloatWritable , myBook>> iter = bookMap.entrySet().iterator();
		Entry<FloatWritable , myBook> entry = null;

		while(bookMap.size()>25){
			entry = iter.next();      
			iter.remove();         
		}
		
		//context.write(new IntWritable(ratingCount), book);
	}
	
	protected void cleanup(Context context) throws IOException, InterruptedException {

		// Output our ten records to the reducers with a null key
		
		for(Entry<FloatWritable,myBook> e :bookMap.entrySet()) {
			context.write(NullWritable.get(), e.getValue());
			//System.out.println(e.getKey().toString()+ "\t"+ e.getValue().toString());
		}
	}
}
