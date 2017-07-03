package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import services.exceptions.InvalidInputException;


/**
 * Created by knvarma
 * 
 */
public class Restaurant {

    private final Pattern menuPattern = Pattern.compile("(\\d+)\\s+(\\d+)");
    private final List<MenuItem> menuItems = new ArrayList<MenuItem>();

    private int menuCount = -1;
    private boolean initSuccess = true;

    /**
     * Reads the file and loads into menu items list. If failed to load, initSuccess is set to false.
     * Later this variable will be verified before calculating max satisfaction value.
     * @param filePath
     * @throws FileNotFoundException
     * @throws InvalidInputException
     */
    public void readMenuFromFile(String filePath) throws FileNotFoundException, InvalidInputException{
        if (isNotValidFile(filePath)) {
            initSuccess = false;
            return;
        }
        
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(filePath)));
            String line = br.readLine();

            parseMenuCount(line);

            while ((line = br.readLine()) != null) {
                Matcher matcher = menuPattern.matcher(line);
                if (matcher.find()) {
                    try {
                        int satisfaction = Integer.parseInt(matcher.group(1));
                        int timeTake = Integer.parseInt(matcher.group(2));
                        menuItems.add(new MenuItem(satisfaction, timeTake));
                    } catch (NumberFormatException e) {
                        initSuccess = false;
                        throw new InvalidInputException("Input file has invalid content");
                    }
                }
            }
        } catch (Exception e) {
            initSuccess = false;
            e.printStackTrace();
        } finally {
        	if (br != null) {
        		try {
        			br.close();	
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
        	}
        }
    }

    private void parseMenuCount(String line) throws InvalidInputException {
        if (line == null) {
            throw new InvalidInputException();
        } else {
            try {
                menuCount = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw new InvalidInputException();
            }
        }
    }

    private boolean isNotValidFile(String filePath) throws FileNotFoundException {
        if (filePath == null) {
            throw new IllegalArgumentException("File path should not be null");
        }

        if (filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path should not be empty");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath + " file is not found.");
        }
        return false;
    }

    public boolean isInitSuccess() {
        return initSuccess;
    }

    public int getMenuCount() {
        return menuCount;
    }

    public List<MenuItem> getMenu() {
        return menuItems;
    }

    public MenuItem getMenuAtIndex(int index) {
    	if (menuItems.size() > index) {
    		return menuItems.get(index);
    	}
        
    	return null;
    }
}
