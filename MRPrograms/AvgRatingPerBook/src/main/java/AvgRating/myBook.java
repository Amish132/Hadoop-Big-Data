package AvgRating;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;



public class myBook implements Writable {

	Text isbn;
	FloatWritable rating;
	IntWritable noBooks;
	Text bookName;
	
	
	public myBook() {
		isbn = new Text();
		rating = new FloatWritable();
		noBooks = new IntWritable();
		bookName = new Text();
	}

 


	public Text getBookName() {
		return bookName;
	}




	public void setBookName(Text bookName) {
		this.bookName = bookName;
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




	public IntWritable getNoBooks() {
		return noBooks;
	}




	public void setNoBooks(IntWritable noBooks) {
		this.noBooks = noBooks;
	}




	public void readFields(DataInput in) throws IOException {

		this.isbn.readFields(in);
		this.rating.readFields(in);
		this.noBooks.readFields(in);
	}

	public void write(DataOutput out) throws IOException {
		this.isbn.write(out);
		this.rating.write(out);
		this.noBooks.write(out);
	}
/*
	@Override
	public int compare(WritableComparable<T> o) {
		rating
	}*/
}
