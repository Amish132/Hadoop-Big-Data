import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class BinningMapper extends Mapper<Object, Text, Text, NullWritable> {

	private MultipleOutputs<Text, NullWritable> multipleOutputs = null;
	SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		multipleOutputs = new MultipleOutputs<Text, NullWritable>(context);
	}

	@Override
	protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		try {
			String[] field = value.toString().split("\"");
			//System.out.println(field[0]);
			//System.out.println(field[1]);
			//System.out.println(field[2]);
			//System.out.println(field[1].split(",")[2]);
			String[] location= field[1].split(",");
			String country = location[2];
			
			if (null != country) {


				if (country.equalsIgnoreCase(" usa") || country.equalsIgnoreCase("usa")) {

					multipleOutputs.write("Countries", value, NullWritable.get(), "USA");

				} else{
					multipleOutputs.write("Countries", value, NullWritable.get(), "Others");
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			//System.out.println("Header");
		}


	}

	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException {
		multipleOutputs.close();
	}


}