

public class ContactBook {
	static final int DEFAULT_SIZE = 100;

	private int counter;
	private Contact[] contacts;
	private int currentContact;

	public ContactBook() {
		counter = 0;
		contacts = new Contact[DEFAULT_SIZE];
		currentContact = -1;
	}

	// Pre: name != null
	public boolean hasContact(String name) {
		return searchIndex(name) >= 0;
	}

	public int getNumberOfContacts() {
		return counter;
	}

	// Pre: name!= null && !hasContact(name)
	public void addContact(String name, int phone, String email) {
		if (counter == contacts.length)
			resize();
		contacts[counter] = new Contact(name, phone, email);
		counter++;
	}

	// Pre: name != null && hasContact(name)
	public void deleteContact(String name) {
		contacts[searchIndex(name)] = contacts[counter - 1];
		counter--;
	}

	// Pre: name != null && hasContact(name)
	public int getPhone(String name) {
		return contacts[searchIndex(name)].getPhone();
	}

	// Pre: name != null && hasContact(name)
	public String getEmail(String name) {
		return contacts[searchIndex(name)].getEmail();
	}

	// Pre: name != null && hasContact(name)
	public void setPhone(String name, int phone) {
		contacts[searchIndex(name)].setPhone(phone);
	}

	// Pre: name != null && hasContact(name)
	public void setEmail(String name, String email) {
		contacts[searchIndex(name)].setEmail(email);
	}

	public int searchIndex(String name) {
		int i = 0;
		int result = -1;
		boolean found = false;
		while (i < counter && !found)
			if (contacts[i].getName().equals(name))
				found = true;
			else
				i++;
		if (found)
			result = i;
		return result;
	}
	
	public int indexByNumber(int number) {
		int i = 0;
		int result = -1;
		boolean found = false;
		while (i < counter && !found)
			if (contacts[i].getPhone()== number)
				found = true;
			else
				i++;
		if (found)
			result = i;
		return result;
		
	}
	
	public Contact getContactByNumber(int number) {
		return contacts[indexByNumber(number)];
	}

	public boolean isPhoneRepeated(){
		boolean repeated = false;
		for (int i = 0; i < counter && !repeated; i++) 
			for(int j = i + 1; j < counter;j++) {
				if(contacts[i].getPhone() == contacts [j].getPhone())
					repeated = true;
			}
		
		return repeated;
	}
	
	private void resize() {
		Contact tmp[] = new Contact[2 * contacts.length];
		for (int i = 0; i < counter; i++)
			tmp[i] = contacts[i];
		contacts = tmp;
	}

	public void initializeIterator() {
		currentContact = 0;
	}

	public boolean hasNext() {
		return (currentContact >= 0) && (currentContact < counter);
	}

	// Pre: hasNext()
	public Contact next() {
		return contacts[currentContact++];
	}

}
