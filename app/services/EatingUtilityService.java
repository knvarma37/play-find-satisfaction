package services;

/**
 * EatingUtilityService takes the RestaurantMenu as constructor parameter and find the max satisfaction for a given time limit.
 */
public class EatingUtilityService {

    private final Restaurant restaurantMenu;

    public EatingUtilityService(Restaurant restaurantMenu) {
        this.restaurantMenu = restaurantMenu;
    }

    /**
     * Uses KnapSack algorithm
     * Finds max satisfaction from a given list of menu with satisfaction, required time pair.
     * @param customerTimeLimit
     * @return
     */
    public Integer findMaxSatisfaction(int customerTimeLimit) {
        if (!restaurantMenu.isInitSuccess()) {
            return -1;
        }
        int index, timeLimit;
        int maxSatisfactionArray[][] = new int[restaurantMenu.getMenuCount() + 1][customerTimeLimit + 1];
        for (index = 0; index <= restaurantMenu.getMenuCount(); index++) {
            for (timeLimit = 0; timeLimit <= customerTimeLimit; timeLimit++) {
                if (index == 0 || timeLimit == 0) {
                    maxSatisfactionArray[index][timeLimit] = 0;
                    continue;
                }

                MenuItem menuItem = restaurantMenu.getMenuAtIndex(index - 1);
                if (menuItem.getRequiredTime() > timeLimit) {
                    maxSatisfactionArray[index][timeLimit] = maxSatisfactionArray[index - 1][timeLimit];
                } else {
                    maxSatisfactionArray[index][timeLimit] = Math.max(menuItem.getSatisfaction() +
                                    maxSatisfactionArray[index - 1][timeLimit - menuItem.getRequiredTime()],
                            maxSatisfactionArray[index - 1][timeLimit]);
                }
            }
        }
        return maxSatisfactionArray[restaurantMenu.getMenuCount()][customerTimeLimit];
    }
}
