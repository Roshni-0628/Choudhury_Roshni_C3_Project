import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class Restaurant {
    private  final String name;
    private  final String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private final List<Item> menu = new ArrayList<Item>();

    public LocalTime currentTime= LocalTime.now();
    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {

        LocalTime currentTime = getCurrentTime();
        return currentTime.isAfter(this.openingTime) && currentTime.isBefore(this.closingTime);


    }

    public LocalTime getCurrentTime(){ return  this.currentTime; }

    public List<Item> getMenu() {
        return this.menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public int getTotalOrderCost(List<String> itemsList){
        int addedCost = 0;
        for(String item : itemsList){
            Item foundItem = findItemByName(item);
            if(nonNull(foundItem) && (foundItem.getPrice() > 0)){
                addedCost += foundItem.getPrice();
            }
        }
        return addedCost;
    }

    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

}
