package AvgRating;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class bookReducer extends Reducer<Text, IntWritable, Text, Text>{

	
	FloatWritable avgRating = new FloatWritable();
	myBook book = new myBook();
	@Override
	protected void reduce(Text isbn, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException{
		
	float sum = 0;
	int count =0;
	float avg;
	//System.out.print("inside reducer");
	for(IntWritable val:values) {
		sum+=val.get();
		count++;
	}
	avg = sum/count;
	
	avgRating.set(avg);
	book.noBooks.set(count);
	book.rating.set(avg);
	book.isbn.set(isbn);
	
	//Filtering out the books who were reviewed by more than atleast 100 people
	if(count>100)
	context.write(new Text(book.isbn),new Text(book.noBooks.toString() + "\t" + book.rating.toString()));
	
	}
	

}
