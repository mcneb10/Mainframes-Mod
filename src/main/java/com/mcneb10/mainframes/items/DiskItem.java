package com.mcneb10.mainframes.items;

public class DiskItem extends BaseItem {

	public DiskItem(String name) {
		super(name);
		this.setMaxStackSize(1);
		this.bFull3D=true;
	}

}
