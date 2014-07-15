package com.monkeyrunner.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeMap;

import com.android.chimpchat.ChimpChat;
import com.android.chimpchat.core.IChimpDevice;
import com.android.chimpchat.core.PhysicalButton;
import com.android.chimpchat.core.TouchPressType;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.Log;
import com.android.monkeyrunner.MonkeyDevice;
import com.android.monkeyrunner.easy.By;
import com.android.monkeyrunner.easy.EasyMonkeyDevice;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;

public class WechatSpider {

	private static final String ADB = "/Applications/android-sdk-macosx/platform-tools/adb";
	private static final String TEST_PACKAGE = "com.tencent.mm";
	private static final String TEST_LAUNCH_ACTIVITY = "com.tencent.mm.ui.LauncherUI";

	private static final long TIMEOUT = 5000;

	private ChimpChat mChimpchat;
	private IChimpDevice mDevice;
	private LDDeviceChangeListener ldDeviceChangeListener;
	private EasyMonkeyDevice easyMonkeyDevice;

	/**
	 * Constructor
	 */
	public WechatSpider() {
		super();
		TreeMap<String, String> options = new TreeMap<String, String>();
		options.put("backend", "adb");
		options.put("adbLocation", ADB);
		mChimpchat = ChimpChat.getInstance(options);
	}

	/**
	 * Initializes the JavaMonkey.
	 */
	public void init() {
		ldDeviceChangeListener = new LDDeviceChangeListener();
		AndroidDebugBridge.addDeviceChangeListener(ldDeviceChangeListener);
		mDevice = mChimpchat.waitForConnection(TIMEOUT, ".*");
		if (mDevice == null) {
			throw new RuntimeException("Couldn't connect.");
		}
		easyMonkeyDevice = new EasyMonkeyDevice(new MonkeyDevice(mDevice));
		mDevice.wake();

	}

	/**
	 * List all properties.
	 */
	public void listProperties() {
		if (mDevice == null) {
			throw new IllegalStateException("init() must be called first.");
		}
		for (String prop : mDevice.getPropertyList()) {
			System.out.println(prop + ": " + mDevice.getProperty(prop));
		}
	}

	/**
	 * Terminates this JavaMonkey.
	 */
	public void shutdown() {
		mDevice.press(PhysicalButton.BACK, TouchPressType.DOWN_AND_UP);
		waitUI(1);
		mDevice.press(PhysicalButton.BACK, TouchPressType.DOWN_AND_UP);
		waitUI(1);
		mDevice.press(PhysicalButton.BACK, TouchPressType.DOWN_AND_UP);
		waitUI(1);
		mDevice.press(PhysicalButton.BACK, TouchPressType.DOWN_AND_UP);
		waitUI(1);
		mDevice.press(PhysicalButton.BACK, TouchPressType.DOWN_AND_UP);
		waitUI(1);
		mDevice.press(PhysicalButton.BACK, TouchPressType.DOWN_AND_UP);
		waitUI(1);
		mDevice.press(PhysicalButton.BACK, TouchPressType.DOWN_AND_UP);
		waitUI(1);
		AndroidDebugBridge.removeDeviceChangeListener(ldDeviceChangeListener);
		AndroidDebugBridge.terminate();
		mChimpchat.shutdown();
		mDevice.shell("am force-stop " + TEST_PACKAGE);
		mDevice = null;
	}

	public void launchWechat() {
		// 添加启动权限
		String action = "android.intent.action.MAIN";
		Collection<String> categories = new ArrayList<String>();
		categories.add("android.intent.category.LAUNCHER");
		// 启动要测试的主界面
		// waitUI(5);
		// am start -n com.tencent.mm/com.tencent.mm.ui.LauncherUI
		// mDevice.shell("am start -n "+TEST_PACKAGE+"/"+TEST_LAUNCH_ACTIVITY);
		// am force-stop com.tencent.mm

		mDevice.startActivity(null, action, null, null, categories,
				new HashMap<String, Object>(), TEST_PACKAGE + "/"
						+ TEST_LAUNCH_ACTIVITY, 0);

		// By loginBtn = By.id("id/select_login_btn");
		//
		// if(loginBtn!=null){
		// //点击登录
		// easyMonkeyDevice.touch(loginBtn, TouchPressType.DOWN_AND_UP);
		// waitUI(2);
		//
		// //点击使用其他方式登录
		// easyMonkeyDevice.touch(By.id("id/login_other_way"),
		// TouchPressType.DOWN_AND_UP);
		// waitUI(2);
		//
		// easyMonkeyDevice.type(By.id("id/edittext"), "\u4e2d\u6587");
		// }

		// easyMonkeyDevice.type(By.id("id/login_password_et"), "你好");

		waitUI(10);

		printCurrentInfo();
		// 点击通讯录
		mDevice.touch(596, 186, TouchPressType.DOWN_AND_UP);
		waitUI(2);
		// 点击订阅号
		mDevice.touch(139, 648, TouchPressType.DOWN_AND_UP);
		waitUI(2);
		printCurrentInfo();
		// 点击插画师
		mDevice.touch(342, 664, TouchPressType.DOWN_AND_UP);
		waitUI(2);
		printCurrentInfo();
		// 点击详情
		mDevice.touch(661, 96, TouchPressType.DOWN_AND_UP);
		waitUI(2);
		printCurrentInfo();
		// 点击历史记录
		mDevice.touch(261, 762, TouchPressType.DOWN_AND_UP);
		waitUI(10);
		printCurrentInfo();

		/*
		 * 点击最上面 然后不断向上拖动 来实现点击下面一条
		 */

		for (int i = 0; i < 10; i++) {
			getUrl(i);
			waitUI(4);
		}

		waitUI(5);

		/*
		 * 
		 * 
		 * waitUI(8); //点击Menu按键 mDevice.press(PhysicalButton.MENU,
		 * TouchPressType.DOWN_AND_UP); waitUI(4); //点击复制链接 mDevice.touch(461,
		 * 674,TouchPressType.DOWN_AND_UP);
		 * 
		 * waitUI(2);
		 * 
		 * saveHtml();
		 */

		// HierarchyViewer hierachyView = mDevice.getHierarchyViewer();
		//
		// ViewNode vn = hierachyView.findViewById("action_bar_stub");

		System.out.println("=====");
	}

	private void printCurrentInfo() {
		/*
		 * 启动 am.current.comp.class: com.tencent.mm.ui.LauncherUI 点击订阅号
		 * am.current.comp.class:
		 * com.tencent.mm.plugin.brandservice.ui.BrandServiceIndexUI 点击某个订阅号
		 * am.current.comp.class: com.tencent.mm.ui.chatting.ChattingUI 点击订阅号详情
		 * am.current.comp.class:
		 * com.tencent.mm.ui.contact.profile.ContactInfoUI 点击查看历史记录
		 * am.current.comp.class:
		 * com.tencent.mm.plugin.webview.ui.tools.WebViewUI
		 */
		System.out.println("-----------------------------------");
		System.out.println("am.current.comp.class: "
				+ mDevice.getProperty("am.current.comp.class"));

		// for(int i=0;i<20;i++){
		// System.out.println("am.current.comp.class: "+mDevice.getProperty("am.current.comp.class"));
		// try {
		// Thread.sleep(3000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
	}

	private void getUrl(int i) {
		printCurrentInfo();
		if (i != 0) {
			mDevice.drag(288, 900, 288, 800, 100, 1);
			waitUI(4);
		}

		// 点击文章
		mDevice.shell("sendevent /dev/input/event3 3 57 1099");
		mDevice.shell("sendevent /dev/input/event3 3 58 50");
		mDevice.shell("sendevent /dev/input/event3 3 52 1");
		mDevice.shell("sendevent /dev/input/event3 3 53 379");
		mDevice.shell("sendevent /dev/input/event3 3 54 272");
		mDevice.shell("sendevent /dev/input/event3 3 57 0");
		mDevice.shell("sendevent /dev/input/event3 0 0 0");
		mDevice.shell("sendevent /dev/input/event3 3 88 53");
		mDevice.shell("sendevent /dev/input/event3 0 0 0");
		mDevice.shell("sendevent /dev/input/event3 3 88 54");
		mDevice.shell("sendevent /dev/input/event3 0 0 0");
		mDevice.shell("sendevent /dev/input/event3 3 88 52");
		mDevice.shell("sendevent /dev/input/event3 0 0 0");
		mDevice.shell("sendevent /dev/input/event3 3 57 4294967295");
		mDevice.shell("sendevent /dev/input/event3 0 0 0");

		waitUI(4);
		// 点击Menu按键
		mDevice.press(PhysicalButton.MENU, TouchPressType.DOWN_AND_UP);
		waitUI(2);
		// 点击复制链接
		mDevice.touch(461, 674, TouchPressType.DOWN_AND_UP);
		waitUI(2);

		// 保存链接
		saveHtml();
	}

	private void saveHtml() {

		// com.louding.monkeyrunnerdemo.MainActivity
		mDevice.shell("am start -n "
				+ "com.louding.monkeyrunnerdemo/com.louding.monkeyrunnerdemo.MainActivity");
		waitUI(5);
		printCurrentInfo();
		// 长按
		mDevice.touch(342, 458, TouchPressType.DOWN);
		waitUI(1);
		mDevice.touch(342, 458, TouchPressType.UP);
		waitUI(1);
		// 粘贴
		mDevice.touch(67, 285, TouchPressType.DOWN_AND_UP);
		waitUI(1);
		// 点击下载按钮
		mDevice.touch(371, 618, TouchPressType.DOWN_AND_UP);
		waitUI(3);
		mDevice.shell("am force-stop com.louding.monkeyrunnerdemo");
		// 按两次返回 回到历史消息界面
		// mDevice.press(PhysicalButton.BACK, TouchPressType.DOWN_AND_UP);
		waitUI(1);
		mDevice.press(PhysicalButton.BACK, TouchPressType.DOWN_AND_UP);
	}

	private void waitUI(int s) {
		try {
			System.out.println("休眠"+s+"秒");
			Thread.sleep(1000 * s);
		} catch (InterruptedException e) {
			System.out.println("<===>");
			e.printStackTrace();
		}
	}


	public void excuteAction(String actionFile) {
		// try {
		// //创建一个解析XML的工厂对象
		// SAXParserFactory parserFactory=SAXParserFactory.newInstance();
		// //创建一个解析XML的对象
		// SAXParser parser = parserFactory.newSAXParser();
		// //创建一个解析助手类
		// myhandler=new ActionHandler(this);
		// parser.parse(actionFile, myhandler);
		// } catch (ParserConfigurationException e) {
		// e.printStackTrace();
		// } catch (SAXException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}



	private void doActionGroup(ActionGroup ag) {
		for (int i = 0; i < ag.getCount(); i++) {
			doActions(ag.getActions());
			waitUI(ag.getWaitTime());
		}
	}

	private void doActions(Actions actions) {
		for (int i = 0; i < actions.getActionWarps().size(); i++) {
			doActionWarp(actions.getActionWarps().get(i));
		}
	}

	private void doActionWarp(ActionWarp actionWarp){
		switch (actionWarp.getActionType()) {
		case 0:
			doAction(actionWarp.getAction());
			break;
		case 1:
			doActionGroup(actionWarp.getActionGroup());
			break;

		default:
			break;
		}
	}
	
	private void doAction(Action action) {

		System.out.println(action.getDescribtion());

		if (Operation.Click.getIndex() == action.getOperation()) {
			processClick(action);
		} else if (Operation.LongPress.getIndex() == action.getOperation()) {
			processLongPress(action);
		} else if (Operation.Drag.getIndex() == action.getOperation()) {
			processDrag(action);
		} else if (Operation.Input.getIndex() == action.getOperation()) {
			processInput(action);
		} else if (Operation.StartApp.getIndex() == action.getOperation()) {
			processStartApp(action);
		} else if (Operation.StopApp.getIndex() == action.getOperation()) {
			processStopApp(action);
		} else if (Operation.ShellCMD.getIndex() == action.getOperation()) {
			processShellCMD(action);
		} else if (Operation.Press.getIndex() == action.getOperation()) {
			processPress(action);
		}
	}

	private void processClick(Action action) {
		String viewId = action.getViewId();
		if (viewId != null && (!viewId.trim().equals(""))) {
			easyMonkeyDevice.touch(By.id("id/login_other_way"),
					TouchPressType.DOWN_AND_UP);
		} else {
			mDevice.touch(action.getPointX(), action.getPointY(),
					TouchPressType.DOWN_AND_UP);
		}
		waitUI(action.getWaitTime());
	}

	private void processLongPress(Action action) {
		// 长按
		mDevice.touch(action.getPointX(), action.getPointY(), TouchPressType.DOWN);
		waitUI(1);
		mDevice.touch(action.getPointX(), action.getPointY(), TouchPressType.UP);
		waitUI(action.getWaitTime());
	}

	private void processDrag(Action action) {
		mDevice.drag(action.getStartPointX(),action.getStartPointY(),action.getEndPointX(),action.getEndPointY(), 100, 1);
		waitUI(action.getWaitTime());
	}

	private void processInput(Action action) {

	}

	private void processStartApp(Action action) {
		mDevice.shell("am start -n "
				+ action.getAppPackage()+"/"+action.getMainActivity());
		waitUI(action.getWaitTime());
	}

	private void processStopApp(Action action) {
		mDevice.shell("am force-stop "+action.getAppPackage());
		waitUI(action.getWaitTime());
	}

	private void processShellCMD(Action action) {
		for(int i=0;i<action.getShellCMDs().getCmds().size();i++){
			mDevice.shell(action.getShellCMDs().getCmds().get(i));
		}
		waitUI(action.getWaitTime());
	}

	private void processPress(Action action) {

		if("KEYCODE_HOME".equals(action.getPressKeyCode())){
			mDevice.press(PhysicalButton.HOME, TouchPressType.DOWN_AND_UP);
		}else if("KEYCODE_SEARCH".equals(action.getPressKeyCode())){
			mDevice.press(PhysicalButton.SEARCH, TouchPressType.DOWN_AND_UP);
		}else if("KEYCODE_MENU".equals(action.getPressKeyCode())){
			mDevice.press(PhysicalButton.MENU, TouchPressType.DOWN_AND_UP);
		}else if("KEYCODE_BACK".equals(action.getPressKeyCode())){
			mDevice.press(PhysicalButton.BACK, TouchPressType.DOWN_AND_UP);
		}else if("DPAD_UP".equals(action.getPressKeyCode())){
			mDevice.press(PhysicalButton.DPAD_UP, TouchPressType.DOWN_AND_UP);
		}else if("DPAD_DOWN".equals(action.getPressKeyCode())){
			mDevice.press(PhysicalButton.DPAD_DOWN, TouchPressType.DOWN_AND_UP);
		}else if("DPAD_LEFT".equals(action.getPressKeyCode())){
			mDevice.press(PhysicalButton.DPAD_LEFT, TouchPressType.DOWN_AND_UP);
		}else if("DPAD_RIGHT".equals(action.getPressKeyCode())){
			mDevice.press(PhysicalButton.DPAD_RIGHT, TouchPressType.DOWN_AND_UP);
		}else if("DPAD_CENTER".equals(action.getPressKeyCode())){
			mDevice.press(PhysicalButton.DPAD_CENTER, TouchPressType.DOWN_AND_UP);
		}else if("enter".equals(action.getPressKeyCode())){
			mDevice.press(PhysicalButton.ENTER, TouchPressType.DOWN_AND_UP);
		}
		waitUI(action.getWaitTime());
	}
	
	public static void main(String[] args) {
		
		/*
	 	 //以代码方式运行
		 final WechatSpider wechatSpider = new WechatSpider();
		  wechatSpider.init();
		  //wechatSpider.listProperties();
		 wechatSpider.launchWechat();
		 //wechatSpider.printCurrentInfo();
		 wechatSpider.shutdown();
		*/
		XStream xstream = new XStream();
		xstream.processAnnotations(ShellCMDs.class);
		xstream.processAnnotations(Action.class);
		xstream.processAnnotations(Actions.class);
		xstream.processAnnotations(ActionWarp.class);
		xstream.processAnnotations(ActionGroup.class);
		xstream.autodetectAnnotations(true);
		ActionGroup actionGroup = null;
		try {
			actionGroup = (ActionGroup) xstream.fromXML(new File("/Users/Apple5/Documents/Development/workspace/monkeyrunnerTest/src/com/spider/wechat/doaction.xml"));
			final WechatSpider wechatSpider = new WechatSpider();
			wechatSpider.init();
			wechatSpider.doActionGroup(actionGroup);
			wechatSpider.shutdown();
		} catch (CannotResolveClassException e) {
			Log.e("CannotResolveClassException", e.toString());
		}
	}

}
