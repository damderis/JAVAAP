

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private static ShoppingCart INSTANCE;

    public static ShoppingCart getInstance(){
        if(INSTANCE==null){
            INSTANCE = new ShoppingCart();
        }
        return INSTANCE;
    }

    private Map<String,CartEntry> entries;
    
    private ShoppingCart(){
        this.entries = new HashMap<>();
    }

    public static ShoppingCart nullInstance(){
        INSTANCE = null;
        return INSTANCE;
    }

    public void addProduct(String productName){
        CartEntry productEntry = entries.get(productName.toUpperCase());
        if(productEntry!=null){
            productEntry.increaseQuantity();
        }else{
            Product product = Product.valueOf(productName);
            CartEntry entry = new CartEntry(product, 1);
            entries.put(productName.toUpperCase(),entry);
        }

    }

    public void removeProduct(String productName){
        CartEntry productEntry = entries.get(productName.toUpperCase());
        if(productEntry!=null){
            productEntry.decreaseQuantity();
        }
        
    }

    public int getQuantity(String productName){
        CartEntry entry = entries.get(productName.toUpperCase());
        if(entry!=null){
            return entry.getQuantity();
        }
        return 0;
    }

    public BigDecimal calculateTotal(){
        float total = 0.00f;
        
        for(CartEntry entry:entries.values()){
            float entryCost = entry.getProduct().getPrice()*entry.getQuantity();
            total = total + entryCost;
        }
        
        BigDecimal value1 = new BigDecimal(String.valueOf(total));
        BigDecimal rounded1 = value1.setScale(2, RoundingMode.HALF_UP);
        
        return rounded1;
    }

    public List<CartEntry> getEntries(){
        return new ArrayList<>(entries.values());
    }
}
