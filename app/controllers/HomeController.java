package controllers;

import javax.inject.Inject;
import javax.inject.Singleton;

import play.mvc.Controller;
import play.mvc.Result;
import services.EatSatisfactionService;


/**
 * This controller serves Home page and finds the max satisfaction value.
 */
@Singleton
public class HomeController extends Controller {
    
    private EatSatisfactionService eatSatisfactionService;
    
    @Inject
    public HomeController(EatSatisfactionService eatSatisfactionService) {
		this.eatSatisfactionService = eatSatisfactionService;
    }
    
    /**
     * This API returns home page for "/" route.
     * @return
     */
    public Result index() {
        return ok(views.html.index.render());
    }
    
    /**
     * This API returns the max satisfaction value within the time limit.
     * @param timelimit
     * @return
     */
    public Result findSatisfaction(Integer timelimit) {
        Integer value = eatSatisfactionService.findMaxSatisfaction(timelimit);
        return ok(String.valueOf(value));
    }

}
