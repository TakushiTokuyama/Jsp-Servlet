package schedule;

public class Plan {

		private int id;
		private int number;
		private String name;
		private String planTime;
		private String firstTime;
		private String lastTime;
		private String planCategory;
		private String planText;

		Plan(){}

	public Plan(int id , String planTime , String planCategory , String firstTime , String lastTime , String planText){

		this.id = id;
		this.planTime = planTime;
		this.planCategory = planCategory;
		this.firstTime = firstTime;
		this.lastTime = lastTime;
		this.planText = planText;
	}

	public Plan(int number , String name , String planTime , String planCategory , String firstTime , String lastTime , String planText){

		this.number = number;
		this.name = name;
		this.planTime = planTime;
		this.planCategory = planCategory;
		this.firstTime = firstTime;
		this.lastTime = lastTime;
		this.planText = planText;
	}

	public Plan(int id , int number , String name ,  String planTime , String planCategory , String firstTime , String lastTime , String planText){

		this(number , name , planTime , planCategory , firstTime , lastTime  ,planText);
		this.id = id;

	}

	public int getId() {return this.id;}
	public int getNumber() {return this.number;}
	public String getName() {return this.name;}
	public String getPlanTime() {return this.planTime;}
	public String getPlanCategory() {return this.planCategory;}
	public String getFirstTime() {return this.firstTime;}
	public String getLastTime() {return this.lastTime;}
	public String getPlanText() {return this.planText;}

}
