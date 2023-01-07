

public enum Product {
    EVIAN("evian.jpg",2.00f),HUPSENG("hupseng.jpg",4.30f),
    MAGGI("maggi.jpg",4.00f),OREO("oreo.jpg",5.50f), PEPSI("pepsi.jpg",3.50f),
    PANADOL("panadol.jpg",3.50f), CHIP("chip.jpg",3.50f), BISCUIT("sugarcrack.jpg",3.50f),
    CABLE("typec.jpg",3.50f), EMPTY("",0.0f);

    private String imageFile;
    private float price;

    Product(String imageFile,float price){
        this.imageFile = imageFile;
        this.price = price;
    }

    public String getImageFile() {
        return imageFile;
    }

    public float getPrice() {
        return price;
    }
}