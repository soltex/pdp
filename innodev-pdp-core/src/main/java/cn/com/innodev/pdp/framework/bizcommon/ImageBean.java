/**
 * 
 */
package cn.com.innodev.pdp.framework.bizcommon;

import com.vanstone.common.MyAssert;
import com.vanstone.common.util.image.ImagePropertyVO;
import com.vanstone.weedfs.client.WeedFile;

/**
 * 图片对象
 * 
 * @author shipeng
 */
public class ImageBean {

	private WeedFile weedFile;
	private int width;
	private int height;
	
	public ImageBean() {
		//默认无惨构造函数
	}
	
	public ImageBean(WeedFile weedFile, ImagePropertyVO imagePropertyVO) {
		MyAssert.notNull(weedFile);
		MyAssert.notNull(imagePropertyVO);
		this.weedFile = weedFile;
		this.width = imagePropertyVO.getWidth();
		this.height = imagePropertyVO.getHeight();
	}
	
	public WeedFile getWeedFile() {
		return weedFile;
	}
	
	public void setWeedFile(WeedFile weedFile) {
		this.weedFile = weedFile;
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
