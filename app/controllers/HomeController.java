package controllers;

import java.nio.file.Path;
import java.nio.file.Paths;
import play.mvc.*;
import services.Restaurant;
import services.EatingUtilityService;
import javax.inject.*;
import play.Environment;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
@Singleton
public class HomeController extends Controller {
    
    private final EatingUtilityService eatingUtilityService;
    private final Environment env;
    
    @Inject
    public HomeController(final Environment env) {
        this.env = env;
        Restaurant restaurantMenu = new Restaurant();
        String filePath = env.getFile("input.txt").getAbsolutePath();
        try {
            restaurantMenu.readMenuFromFile(filePath);
            eatingUtilityService = new EatingUtilityService(restaurantMenu);
        } catch(Exception e) {
          e.printStackTrace();
        }
    }

    public Result index() {
        return ok(views.html.index.render());
    }
    
    public Result findSatisfaction(Integer timelimit) {
        return ok(eatingUtilityService.findMaxSatisfaction(timelimit));
    }

}
