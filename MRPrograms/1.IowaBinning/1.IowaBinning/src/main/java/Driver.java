import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Driver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		if (args.length != 2) {
			System.err.println("Usage: MaxSubmittedCharge <input path> <output path>");
			System.exit(-1);
		}
		Path inputPath = new Path(args[0]);
		Path outputDir = new Path(args[1]);

		System.err.println("inside driver");
		Configuration conf = new Configuration(true);
		// Delete output if exists
		FileSystem hdfs = FileSystem.get(conf);
		if (hdfs.exists(outputDir)) {
			hdfs.delete(outputDir, true);
		}
		Job job = Job.getInstance(conf);

		job.setJarByClass(Driver.class);
		job.setJobName("Binning_Data");

		FileInputFormat.addInputPath(job, inputPath);
		FileOutputFormat.setOutputPath(job, outputDir);
		MultipleOutputs.addNamedOutput(job, "Countries", TextOutputFormat.class,Text.class, NullWritable.class);
		job.setMapperClass(BinningMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setNumReduceTasks(0);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		int code = job.waitForCompletion(true) ? 0 : 1;
		System.exit(code);

	}



}
