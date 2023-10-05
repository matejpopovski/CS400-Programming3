public class IISBNValidatorplaceHolder implements IISBNValidator{
    @Override
    public boolean validate(String isbn13) {
        if((isbn13.length() == 13) && (isbn13.equals("1234567890123"))){
            return true;
        }
        return false;
    }
}