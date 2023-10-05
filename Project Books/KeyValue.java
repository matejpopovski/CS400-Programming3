public class KeyValue<KeyType, ValueType> {

	private KeyType key;
	private ValueType value;

	public KeyValue(KeyType key, ValueType value) {
		this.key = key;
		this.value = value;
	}

	public KeyType getKey() {
		return this.key;
	}


	public ValueType getValue() {
		return this.value;
	}

}