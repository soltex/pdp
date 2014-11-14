/**
 * 
 */
package cn.com.innodev.example.common;

import com.vanstone.weedfs.client.WeedFile;

/**
 * 图片对象
 * 
 * @author shipeng
 */
public class ImageBean {

	private WeedFile weedFile;
	private String extName;
	private int width;
	private int height;

	public WeedFile getWeedFile() {
		return weedFile;
	}

	public void setWeedFile(WeedFile weedFile) {
		this.weedFile = weedFile;
	}

	public String getExtName() {
		return extName;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
