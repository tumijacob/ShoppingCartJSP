package pgdswd.shoppingcart;

/**
 *
 * @author Tumi
 */
public class ShoppingCart {
    
    private String productName;
    private int productId;
    private double productPrice;
    private int quantity;
    private double itemTotal;

    /**
     * Get the value of itemTotal
     *
     * @return the value of itemTotal
     */
    public double getItemTotal() {
        return itemTotal;
    }

    /**
     * Set the value of itemTotal
     *
     * @param itemTotal new value of itemTotal
     */
    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }

    /**
     * Default no-args constructor
     */
    public ShoppingCart() {
        productName = "";
        productId = 0;
        productPrice = 0;
        quantity = 0;
        itemTotal = 0;
    }

    /**
     * Constructs a product with given name, price, id and quantity.
     * @param productName the name of the product
     * @param productPrice price of the product
     * @param stringproductId  the id of the product
     * @param quantity the number of product
     */
    public ShoppingCart(String productName,int productId, double productPrice, int quantity, double itemTotal) {
        setProductName(productName);
        setProductId(productId);
        setProductPrice(productPrice);
        setQuantity(quantity);
        setItemTotal(itemTotal);
    }

    
    /**
     * Get the value of quantity
     *
     * @return the value of quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the value of quantity
     *
     * @param quantity new value of quantity
     */
    public final void setQuantity(int quantity) {
        this.quantity = quantity;
    }
   

    /**
     * Get the value of productPrice
     *
     * @return the value of productPrice
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * Set the value of productPrice
     *
     * @param productPrice new value of productPrice
     */
    public final void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * Get the value of productName
     *
     * @return the value of productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Set the value of productName
     *
     * @param productName new value of productName
     */
    public final void setProductName(String productName) {
        this.productName = productName;
    }

        /**
     * Get the value of productId
     *
     * @return the value of productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Set the value of productId
     *
     * @param productId new value of productId
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

}
