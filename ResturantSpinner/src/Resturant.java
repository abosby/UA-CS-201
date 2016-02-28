
public class Resturant {
	
	String resturantName;
	String resturantAddress;
	String resturantStyle;
	String resturantPrice;
	String resturantGroup;


	public Resturant(String rName, String rAddress, String rStyle, String rPrice, String rGroup){
		resturantName = rName;
		resturantAddress = rAddress;
		resturantStyle = rStyle;
		resturantPrice = rPrice;
		resturantGroup = rGroup;
	}

	public String getName(){
		return resturantName;
	}
	
	public String getAddress(){
		return resturantAddress;
	}
	
	public String getStyle(){
		return resturantStyle;
	}
	
	public String getPrice(){
		return resturantPrice;
	}
	
	public String getGroup(){
		return resturantGroup;
	}
	

	public void editResturant(String rName, String rAddress, String rStyle, String rPrice, String rGroup) {
		resturantName = rName;
		resturantAddress = rAddress;
		resturantStyle = rStyle;
		resturantPrice = rPrice;
		resturantGroup = rGroup;
	}
	
	public String asCSVRecord(){
		return resturantName + '\t' + resturantAddress + '\t' + resturantStyle + '\t' + resturantPrice + '\t' + resturantGroup;
	}

}


