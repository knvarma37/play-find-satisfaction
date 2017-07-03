package controllers;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import play.mvc.*;
import services.Restaurant;
import services.EatingUtilityService;
import javax.inject.*;
import java.io.FileNotFoundException;
import services.exceptions.InvalidInputException;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
@Singleton
public class HomeController extends Controller {
    
    private EatingUtilityService eatingUtilityService;
    
    @Inject
    public HomeController() {
        Restaurant restaurantMenu = new Restaurant();
        String filePath = Play.application().getFile("resources/input.txt").getAbsolutePath();
        try {
            restaurantMenu.readMenuFromFile(filePath);
        } catch(FileNotFoundException | InvalidInputException e) {
            e.printStackTrace();
        }
        eatingUtilityService = new EatingUtilityService(restaurantMenu);
    }

    public Result index() {
        return ok(views.html.index.render());
    }
    
    public Result findSatisfaction(Integer timelimit) {
        Integer value = eatingUtilityService.findMaxSatisfaction(timelimit);
        return ok(String.valueOf(value));
    }

}
