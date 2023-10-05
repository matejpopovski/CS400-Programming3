//import RedBlackTree.Node;

public class ClothBackend implements IClothBackend{

    public RedBlackTree<ICloth> rbt = new RedBlackTree<ICloth>();


//    @Override
//    public void addCloth(ICloth cloth) {
//        this.rbt.insert(cloth);
//    }

//    @Override
//    public void removeCloth(ICloth cloth){
//        this.rbt.delete(cloth.barcode);
//    }

//    @Override
//    public boolean checkCloth(String barcode){
//        return this.rbt.findNodeToRemove(barcode);   //preimenuvaniot method
//    }
//    @Override
//    public int getNumberOfCloths(){
//        return this.rbt.size();
//    }

//    @Override
//    public String GenerateNewDigitNumber(int numberOfCloth){
//        //ova neznam
//    }

	@Override
	public void addCloth(ICloth cloth) {
		// TODO Auto-generated method stub
		this.rbt.insert(cloth);
		
	}

	@Override
	public void removeCloth(ICloth cloth) {
		// TODO Auto-generated method stub
		 this.rbt.remove(cloth);
		
	}

	@Override
	public int getNumberOfCloth() {
		// TODO Auto-generated method stub
		return this.rbt.size();
	}

	
	@Override
	public boolean checkCloth(Integer barcode) {
		return this.rbt.contains();

	}

	@Override
	public String GenerateNewDigitNumber(int numberOfCloth) {
		// TODO Auto-generated method stub
		return null;
	}


}




