package services;

import java.io.FileNotFoundException;

import com.google.inject.Inject;

import play.Logger;
import play.api.Environment;
import services.exceptions.InvalidInputException;
/**
 * This service starts running when application gets initialized.
 * The service was declared as Singleton and single instance will be maintained for the entire application. 
 * @author knvarma
 *
 */
public class EatSatisfactionService {
	
	private static final String INPUT_FILE_PATH = "public/resources/input.txt";
	private final Environment environment;
	private SatisfactionAnalyzer satisfactionAnalyzer;

	@Inject
	public EatSatisfactionService(Environment environment) {
		this.environment = environment;
		setUpRestaurant();
	}

	/**
	 * Invoke Restaurant object to read restaurant menu from file.
	 */
	public void setUpRestaurant() {
		Restaurant restaurantMenu = new Restaurant();
        String filePath = environment.getFile(INPUT_FILE_PATH).getAbsolutePath();
        try {
            restaurantMenu.readMenuFromFile(filePath);
        } catch (FileNotFoundException e) {
        	Logger.error("Unable to import restaurant menu, file not found.");
            e.printStackTrace();
        } catch (InvalidInputException e) {
        	Logger.error("Importing restaurant menu failed. Invalid file contents");
        	e.printStackTrace();
        }
        satisfactionAnalyzer = new SatisfactionAnalyzer(restaurantMenu);
	}

	/**
	 * Makes call to SatisfactionAnalyzer to find the max satisfaction value.
	 * @param timelimit
	 * @return
	 */
	public Integer findMaxSatisfaction(Integer timelimit) {
		return satisfactionAnalyzer.findMaxSatisfaction(timelimit);
	}
}
