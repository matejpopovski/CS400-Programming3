public class Cloth implements ICloth {

    String name;
    Integer barcode;

    public Cloth(String name, Integer barcode){
        this.name = name;
        this.barcode = barcode;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public int getBarcode(){
        return this.barcode;
    }
    @Override
    public void setName(String name){
        this.name = name;
    }
    @Override
    public void setBarcode(int barcode){
        this.barcode = barcode;
    }
	@Override
	public int compareTo(ICloth cloth) {
			return this.barcode.compareTo(cloth.getBarcode());
	}
}