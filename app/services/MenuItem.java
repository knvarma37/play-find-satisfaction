package services;

/**
 * Created by knvarma
 * Holds the information about a Restaurant Menu.
 */
public class MenuItem {

    private int satisfaction;
    private int requiredTime;

    public MenuItem(int satisfaction, int timeTaken) {
        this.satisfaction = satisfaction;
        this.requiredTime = timeTaken;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public int getRequiredTime() {
        return requiredTime;
    }

    public void setRequiredTime(int timeTaken) {
        this.requiredTime = timeTaken;
    }
    
    @Override
    public int hashCode() {
        final int seed = 37;
        int result = 1;
        result = seed * result + satisfaction;
        result = seed * result + requiredTime;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MenuItem)) {
            return false;
        }

        MenuItem item = (MenuItem) obj;
        if (this.satisfaction != item.satisfaction) {
            return false;
        }

        if (this.requiredTime != item.requiredTime) {
            return false;
        }

        return true;
    }
}
