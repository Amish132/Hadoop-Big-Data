package Authors;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Driver extends Configured implements Tool{

	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = new Job(conf);

		job.setJarByClass(Driver.class);

		job.setMapperClass(authorMapper.class);
		
		job.setReducerClass(authorReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		TextInputFormat.addInputPath(job, new Path(args[0]));
		TextOutputFormat.setOutputPath(job, new Path(args[1]));
		//TextOutputFormat.setOutputPath(job, new Path(args[1]+"/temp"));
		
		return job.waitForCompletion(true)?0:1;
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
