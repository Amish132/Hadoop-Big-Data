package Book;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Comparator;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;



public class User implements Writable {

	Text userid;
	Text isbn;
	FloatWritable rating;
	LongWritable count;
	Text location;
	Text age;

	public Text getLocation() {
		return location;
	}

	public void setLocation(Text location) {
		this.location = location;
	}


	public User() {
		super();
		this.userid = new Text();
		this.isbn = new Text();
		this.rating = new FloatWritable();
		this.count = new LongWritable();
		this.location = new Text();
		this.age = new Text();
	}

	public Text getAge() {
		return age;
	}

	public void setAge(Text age) {
		this.age = age;
	}

	public LongWritable getCount() {
		return count;
	}

	public void setCount(LongWritable count) {
		this.count = count;
	}


	public Text getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid.set(userid);
	}



	public Text getIsbn() {
		return isbn;
	}



	public void setIsbn(Text isbn) {
		this.isbn = isbn;
	}



	public FloatWritable getRating() {
		return rating;
	}



	public void setRating(FloatWritable rating) {
		this.rating = rating;
	}


	public void readFields(DataInput in) throws IOException {

		this.userid.readFields(in);
		this.isbn.readFields(in);
		this.rating.readFields(in);
		this.count.readFields(in);
		this.location.readFields(in);
		this.age.readFields(in);

	}



	public void write(DataOutput out) throws IOException {
		this.userid.write(out);
		this.isbn.write(out);
		this.rating.write(out);
		this.count.write(out);
		this.location.write(out);
		this.age.write(out);
	}
}
class myUserComp implements Comparator<User>{

	public int compare(User u1, User u2) {
		if(u1.userid.equals(u2.userid)){
			return 1;
		} else {
			return -1;
		}
	}
}



