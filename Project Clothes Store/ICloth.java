    /**
     * This interface defines getter methods for each cloth's data attributes
     * and is implemented by classes that represent a book and its associated
     * data.
     */
//    public interface ICloth {
//
//        /**
//         * Returns the digit number of the cloth.
//         * @return digit number of the cloth
//         */
//        String getDigitNumber();
//
//
//    }
    public interface ICloth extends Comparable<ICloth> {
        public String getName();
        public int getBarcode();
        public void setName(String name);
        public void setBarcode(int barcode);
   }