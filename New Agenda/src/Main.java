import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

//Alexandre Nascimento 52515

public class Main {

	public static final String FILE = "Contacts.txt";

	// Constantes que definem os comandos
	public static final String ADD_CONTACT = "AC";
	public static final String REMOVE_CONTACT = "RC";
	public static final String GET_PHONE = "GP";
	public static final String GET_EMAIL = "GE";
	public static final String SET_PHONE = "SP";
	public static final String SET_EMAIL = "SE";
	public static final String LIST_CONTACTS = "LC";
	public static final String GET_BY_NUMBER = "GN";
	public static final String EXISTS_PHONE = "EP";
	public static final String QUIT = "Q";

	// Constantes que definem as mensagens para o utilizador
	public static final String CONTACT_EXISTS = "Contact already exists.";
	public static final String NAME_NOT_EXIST = "Contact does not exist.";
	public static final String CONTACT_ADDED = "Contact added.";
	public static final String CONTACT_REMOVED = "Contact removed.";
	public static final String CONTACT_UPDATED = "Contact updated.";
	public static final String BOOK_EMPTY = "Contact book empty.";
	public static final String PHONE_NOT_EXISTS = "Phone number does not exist.";
	public static final String SHARE_NUMBER = "There are contacts that share phone numbers.";
	public static final String DIFERENT_NUMBER = "All contacts have different phone numbers.";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ContactBook cBook = new ContactBook();
		String comm = getCommand(in);

		while (!comm.equals(QUIT)) {
			switch (comm) {
			case ADD_CONTACT:
				addContact(in, cBook);
				break;
			case REMOVE_CONTACT:
				deleteContact(in, cBook);
				break;
			case GET_PHONE:
				getPhone(in, cBook);
				break;
			case GET_EMAIL:
				getEmail(in, cBook);
				break;
			case SET_PHONE:
				setPhone(in, cBook);
				break;
			case SET_EMAIL:
				setEmail(in, cBook);
				break;
			case LIST_CONTACTS:
				listAllContacts(cBook);
				break;
			case GET_BY_NUMBER:
				getNameByNumber(in, cBook);
				break;
			case EXISTS_PHONE:
				existsContact(cBook);
				break;
			case QUIT:

				break;

			default:

			}
			System.out.println();
			comm = getCommand(in);
		}

		System.out.println("Goodbye!");
		System.out.println();
		in.close();

	}

	

	private static String getCommand(Scanner in) {
		String input;

		input = in.nextLine().toUpperCase();
		return input;
	}

	private static void addContact(Scanner in, ContactBook cBook) {
		String name, email;
		int phone;

		name = in.nextLine().trim();
		phone = in.nextInt();
		in.nextLine();
		email = in.nextLine().trim();
		if (!cBook.hasContact(name)) {
			cBook.addContact(name, phone, email);

			System.out.println(CONTACT_ADDED);
		} else
			System.out.println(CONTACT_EXISTS);
	}

	private static void deleteContact(Scanner in, ContactBook cBook) {
		String name;
		name = in.nextLine().trim();
		if (cBook.hasContact(name)) {
			cBook.deleteContact(name);
			System.out.println(CONTACT_REMOVED);
		} else
			System.out.println(NAME_NOT_EXIST);
	}

	private static void getPhone(Scanner in, ContactBook cBook) {
		String name;
		name = in.nextLine();
		if (cBook.hasContact(name)) {
			System.out.println(cBook.getPhone(name));
		} else
			System.out.println(NAME_NOT_EXIST);
	}

	private static void getEmail(Scanner in, ContactBook cBook) {
		String name;
		name = in.nextLine();
		if (cBook.hasContact(name)) {
			System.out.println(cBook.getEmail(name));
		} else
			System.out.println(NAME_NOT_EXIST);
	}

	private static void setPhone(Scanner in, ContactBook cBook) {
		String name;
		int phone;
		name = in.nextLine();
		phone = in.nextInt();
		in.nextLine();
		if (cBook.hasContact(name)) {
			cBook.setPhone(name, phone);
			System.out.println(CONTACT_UPDATED);
		} else
			System.out.println(NAME_NOT_EXIST);
	}

	private static void setEmail(Scanner in, ContactBook cBook) {
		String name;
		String email;
		name = in.nextLine();
		email = in.nextLine();
		if (cBook.hasContact(name)) {
			cBook.setEmail(name, email);
			System.out.println(CONTACT_UPDATED);
		} else
			System.out.println(NAME_NOT_EXIST);
	}

	private static void listAllContacts(ContactBook cBook) {
		if (cBook.getNumberOfContacts() != 0) {
			cBook.initializeIterator();
			while (cBook.hasNext()) {
				Contact c = cBook.next();
				System.out.println(c.getName() + "; " + c.getEmail() + "; " + c.getPhone());
			}
		} else
			System.out.println(BOOK_EMPTY);
	}

	private static void getNameByNumber(Scanner in, ContactBook cBook) {
		int number;
		number = in.nextInt();
		if (cBook.indexByNumber(number) != -1) {
			System.out.print(cBook.getContactByNumber(number).getName());

		} else
			System.out.print(PHONE_NOT_EXISTS);
	}

	private static void existsContact(ContactBook cBook) {
		if (cBook.isPhoneRepeated())
			System.out.println(SHARE_NUMBER);
		else
			System.out.println(DIFERENT_NUMBER);
	}
}