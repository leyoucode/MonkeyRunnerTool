package com.monkeyrunner.test;

import com.android.ddmlib.AndroidDebugBridge.IDeviceChangeListener;
import com.android.ddmlib.IDevice;

public class LDDeviceChangeListener implements IDeviceChangeListener {

	public LDDeviceChangeListener() {
		System.out.println("==> LDDeviceChangeListener 初始化");
	}

	@Override
	public void deviceChanged(IDevice device, int arg1) {
		System.out.println(String.format("==> LDDeviceChangeListener deviceChanged %s changed %d",device.getSerialNumber(),arg1));
		
//		if(arg1==4){
//
//			IChimpDevice device = adbBackend.waitForConnection(30000,device.getSerialNumber());
//
//			//_chimpDevices.put（ device.getSerialNumber（）， device）;
//
//			device.drag（0， 0， 100， 100， 10， 9000L）;//下拉状况条的
//
//		}
	}

	@Override
	public void deviceConnected(IDevice device) {
		System.out.println(String.format("==> LDDeviceChangeListener %s 连接了",device.getSerialNumber()));
		
	}

	@Override
	public void deviceDisconnected(IDevice device) {
		System.out.println(String.format("==> LDDeviceChangeListener %s 断开了",device.getSerialNumber()));
	}

}
