package Book;
import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Driver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
		
		Job job = Job.getInstance();
		job.setJarByClass(Driver.class);
		//System.out.print("below jar");
		job.setMapperClass(bookMapper.class);
		job.setReducerClass(bookReducer.class);
		job.setCombinerClass(bookReducer.class);
		//System.out.print("below reducer");
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setNumReduceTasks(1);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		FileSystem fs = FileSystem.get(job.getConfiguration());
		Path outDir = new Path(args[1]);
		if(fs.exists(outDir)) {
			fs.delete(outDir,true);
		}
		
		System.exit(job.waitForCompletion(true)?0:1);
	}

}
