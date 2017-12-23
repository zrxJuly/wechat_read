package com.zrxjuly.wechat_read.wxmenu.menu;

/**
 * 复合类型的按钮.
 * 描述：复合类型的按钮指的是含有子按钮的Button，也就是含有子菜单的一级菜单.
 * @author zhangrongxiang
 * @createDate 2017-12-11
 *
 */
public class ComplexButton extends Button {
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
	
}
