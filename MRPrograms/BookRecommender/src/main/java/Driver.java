import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;


import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.example.bookcrossing.BookCrossingDataModel;
import org.apache.mahout.cf.taste.example.bookcrossing.BookCrossingRecommender;

public class Driver {


	public static void main(String args[]) throws Exception{
		try{
			//Creating data model
			BookCrossingDataModel datamodel = new BookCrossingDataModel(new File(args[0]),false); //data

			BookCrossingRecommender recommender = new BookCrossingRecommender(datamodel);

			List<RecommendedItem> recommendations = recommender.recommend((long)276747, 10);

			for (RecommendedItem recommendation : recommendations) {
				System.out.println(recommendation);
			}

		}catch(NumberFormatException ex) {
			ex.printStackTrace();
		}
		catch(TasteException ex){
				System.out.println("Exception: "+ ex.getMessage());
		}
	}

}
