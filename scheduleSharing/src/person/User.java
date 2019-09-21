package person;

import java.io.Serializable;

public class User implements Serializable{
	//ログインに関する情報
	private int number;
	private String id;
	private String password;
	private String name;

	public User() {}

	public User(int number, String name) {

		this.number = number;
		this.name = name;

	}

	public User(String id , String password , String name) {

		this.id = id;
		this.password = password;
		this.name = name;

	}

	public User(int number , String id , String password , String name) {

		this.number = number;
		this.id = id;
		this.password = password;
		this.name = name;

	}

	public int getNumber() {return this.number;}
	public String getId() {return this.id;}
	public String getPassword() {return this.password;}
	public String getName() {return this.name;}

}
