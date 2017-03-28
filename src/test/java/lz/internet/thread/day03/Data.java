/**
 * 
 */
package lz.internet.thread.day03;

/**
 * @author lizhen_pc
 *123
 */
public class Data {

	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "id="+id+" --- name="+name;
	}
	
}
