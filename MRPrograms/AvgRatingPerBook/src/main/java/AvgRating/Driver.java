package AvgRating;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
 

public class Driver extends Configured implements Tool{
	
	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = new Job(conf);

		job.setJarByClass(Driver.class);

		job.setMapperClass(bookMapper.class);
		
		job.setReducerClass(bookReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		TextInputFormat.addInputPath(job, new Path(args[0]));
		TextOutputFormat.setOutputPath(job, new Path(args[1]+"/temp"));
		
		job.waitForCompletion(true);

		Job job2 = new Job(conf);	

		job2.setJarByClass(Driver.class);

		job2.setMapperClass(topTenMapper.class);
		job2.setReducerClass(topTenReducer.class);

		job2.setMapOutputKeyClass(NullWritable.class);
		job2.setMapOutputValueClass(myBook.class);


		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(Text.class);

		job2.setInputFormatClass(TextInputFormat.class);
		job2.setOutputFormatClass(TextOutputFormat.class);

		TextInputFormat.addInputPath(job2, new Path(args[1]+"/temp"));
		TextOutputFormat.setOutputPath(job2, new Path(args[1]+"/final"));

		job2.waitForCompletion(true);
		
		return job2.waitForCompletion(true)?0:1;
	}

	public static void main(String[] args) throws Exception{


		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);


		FileSystem fs = FileSystem.get(job.getConfiguration());
		Path outDir = new Path(args[1]);
		if(fs.exists(outDir)) {
			fs.delete(outDir,true);
		}

		int exitFlag = ToolRunner.run(new Configuration(), new Driver(), args);

		System.exit(exitFlag);



	}

}
