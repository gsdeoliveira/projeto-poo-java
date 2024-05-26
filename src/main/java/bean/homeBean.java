package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class homeBean {
	private String searchValue;
	
	public void searchChange() {
		//
	}
	
	public String getSearchValue() {
		return searchValue;
	}
	
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
}
