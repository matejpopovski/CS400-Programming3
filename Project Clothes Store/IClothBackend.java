import java.util.List;

/**
 * Instances of this interface implement the check, add and remove functionality
 * of the Cloth Store Inventory app using red-black tree.
 */
public interface IClothBackend {

    /**
     * Adds a new cloth to the backend's database and is stored in
     * a red-black tree internally.
     * @param cloth the cloth to add
     */
    public void addCloth(ICloth cloth);

    /**
     * Remove a cloth from the backend's database and remove it from
     * a red-black tree internally.
     * @param cloth the cloth to add
     */
    public void removeCloth(ICloth cloth);

    /**
     * Checkout a cloth if exist in the system.
     * @param digitNumber the specific number for every cloth.
     * @return true if cloth exist in the system.
     */
    public boolean checkCloth(Integer barcode);


    /**
     * Returns the number of cloth stored in the backend's database.
     * @return the number of books
     */
    public int getNumberOfCloth();


    /**
     * Generate a new digit Number for the cloth which need to add.
     * @param numberOfCloth the number of clothes stored in the database backene.
     * @return new digit number
     */
    public String GenerateNewDigitNumber(int numberOfCloth);

}