package services;

/**
 * SatisfactionAnalyzer takes the Restaurant as constructor parameter and find the max satisfaction for a given time limit.
 */
public class SatisfactionAnalyzer {

    private final Restaurant restaurant;

    public SatisfactionAnalyzer(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * Uses KnapSack algorithm
     * Finds max satisfaction from a given list of menu with satisfaction, required time pair.
     * @param customerTimeLimit
     * @return Returns max satisfaction value
     */
    public Integer findMaxSatisfaction(int customerTimeLimit) {
        if (!restaurant.isInitSuccess()) {
            return -1;
        }
        int index, timeLimit;
        int maxSatisfactionArray[][] = new int[restaurant.getMenuCount() + 1][customerTimeLimit + 1];
        for (index = 0; index <= restaurant.getMenuCount(); index++) {
            for (timeLimit = 0; timeLimit <= customerTimeLimit; timeLimit++) {
                if (index == 0 || timeLimit == 0) {
                    maxSatisfactionArray[index][timeLimit] = 0;
                    continue;
                }

                MenuItem menuItem = restaurant.getMenuAtIndex(index - 1);
                if (menuItem.getRequiredTime() > timeLimit) {
                    maxSatisfactionArray[index][timeLimit] = maxSatisfactionArray[index - 1][timeLimit];
                } else {
                    maxSatisfactionArray[index][timeLimit] = Math.max(menuItem.getSatisfaction() +
                                    maxSatisfactionArray[index - 1][timeLimit - menuItem.getRequiredTime()],
                            maxSatisfactionArray[index - 1][timeLimit]);
                }
            }
        }
        return maxSatisfactionArray[restaurant.getMenuCount()][customerTimeLimit];
    }
}
