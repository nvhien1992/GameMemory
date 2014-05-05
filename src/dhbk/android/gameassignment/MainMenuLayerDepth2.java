package dhbk.android.gameassignment;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import android.view.KeyEvent;

public class MainMenuLayerDepth2 extends CCLayer{
	private static CGSize screenSize;
	private static float scaleFactor;
	private static float radioHeart;
	private static CCSprite radioSet;
	private static CCScene diffScene;
	
	protected MainMenuLayerDepth2() {
		this.setIsKeyEnabled(true);
		this.setIsTouchEnabled(true);
		
		screenSize = CCDirector.sharedDirector().winSize();
		scaleFactor  = CCDirector.sharedDirector().winSize().height / 500f;
		
		radioSet = CCSprite.sprite("radioSet.png");
		radioSet.setScale(1f *scaleFactor); 
		radioSet.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		
		/*< Background >*/
		CCSprite backgroundMainMenu = CCSprite.sprite("background/backgroundMainMenu.jpg");
		backgroundMainMenu.setScaleX(screenSize.width / backgroundMainMenu.getContentSize().width);
		backgroundMainMenu.setScaleY(screenSize.height / backgroundMainMenu.getContentSize().height);
		backgroundMainMenu.setAnchorPoint(CGPoint.ccp(0.5f,1f));
		backgroundMainMenu.setPosition(CGPoint.ccp(screenSize.width/2f, screenSize.height));
		addChild(backgroundMainMenu);
	}
	
	@Override
	public boolean ccKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			CCDirector.sharedDirector().replaceScene(MainMenuLayerDepth1.optionsScene());
		}
	    return super.ccKeyDown(keyCode, event);
	}
	
	/*< LevelScene >*/
	public static CCScene difficultScene() {
		diffScene = CCScene.node();
		CCLayer layer = new MainMenuLayerDepth2();
		diffScene.addChild(layer);
		
		/*< Easy >*/
		CCMenuItemImage buttonEasy = CCMenuItemImage.item("button/buttonEasy.png", "button/buttonEasy.png", layer, "buttonEasyCallBack");
		buttonEasy.setScale(1f *scaleFactor); 
		buttonEasy.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		
		CCMenu buttonEasyMenu = CCMenu.menu(buttonEasy);
		buttonEasyMenu.setContentSize(buttonEasy.getContentSize());
		buttonEasyMenu.setPosition(CGPoint.ccp(screenSize.width/2f, screenSize.height/2 + 60*scaleFactor));
		diffScene.addChild(buttonEasyMenu);
		
		/*< Medium >*/
		CCMenuItemImage buttonMedium = CCMenuItemImage.item("button/buttonMedium.png", "button/buttonMedium.png", layer, "buttonMediumCallBack");
		buttonMedium.setScale(1f *scaleFactor); 
		buttonMedium.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		
		CCMenu buttonMediumMenu = CCMenu.menu(buttonMedium);
		buttonMediumMenu.setContentSize(buttonMedium.getContentSize());
		buttonMediumMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height/2));
		diffScene.addChild(buttonMediumMenu);
		
		/*< Hard >*/
		CCMenuItemImage buttonHard = CCMenuItemImage.item("button/buttonHard.png", "button/buttonHard.png", layer, "buttonHardCallBack");
		buttonHard.setScale(1f *scaleFactor); 
		buttonHard.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		
		CCMenu buttonHardMenu = CCMenu.menu(buttonHard);
		buttonHardMenu.setContentSize(buttonHard.getContentSize());
		buttonHardMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height/2f - 60*scaleFactor));
		diffScene.addChild(buttonHardMenu);
		
		radioHeart = screenSize.width/2 - buttonHard.getContentSize().width/2f*scaleFactor;		
		
		/*< radioEasy >*/
		CCSprite radioEasyBG = CCSprite.sprite("radioBG.png");
		CCSprite radioEasyEmpty = CCSprite.sprite("radioEmpty.png");
		radioEasyBG.setScale(1f *scaleFactor); 
		radioEasyBG.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		radioEasyBG.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2f + 60*scaleFactor));
		diffScene.addChild(radioEasyBG);
		
		radioEasyEmpty.setScale(1f *scaleFactor); 
		radioEasyEmpty.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		radioEasyEmpty.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2f + 60*scaleFactor));
		diffScene.addChild(radioEasyEmpty);
			
		/*< radioMedium >*/
		CCSprite radioMediumBG = CCSprite.sprite("radioBG.png");
		CCSprite radioMediumEmpty = CCSprite.sprite("radioEmpty.png");
		radioMediumBG.setScale(1f *scaleFactor); 
		radioMediumBG.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		radioMediumBG.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2f));
		diffScene.addChild(radioMediumBG);
		
		radioMediumEmpty.setScale(1f *scaleFactor); 
		radioMediumEmpty.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		radioMediumEmpty.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2f));
		diffScene.addChild(radioMediumEmpty);
		
		/*< radioHard >*/
		CCSprite radioHardBG = CCSprite.sprite("radioBG.png");
		CCSprite radioHardEmpty = CCSprite.sprite("radioEmpty.png");
		radioHardBG.setScale(1f *scaleFactor); 
		radioHardBG.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		radioHardBG.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2 - 60*scaleFactor));
		diffScene.addChild(radioHardBG);
		
		radioHardEmpty.setScale(1f *scaleFactor); 
		radioHardEmpty.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		radioHardEmpty.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2f - 60*scaleFactor));
		diffScene.addChild(radioHardEmpty);
		
		if(MainActivity.DIFFICULT == "easy")
			radioSet.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2f + 60*scaleFactor));
		else if(MainActivity.DIFFICULT == "medium")
			radioSet.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2f));
		else radioSet.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2f - 60*scaleFactor)); 
		diffScene.addChild(radioSet);
		
		/*< Back >*/
		diffScene.addChild(buttonBack(layer, 50*scaleFactor));
		
		return diffScene;		
	}
	
	public void buttonEasyCallBack(Object sender) {
		if(MainActivity.AUDIO)
			SoundEngine.sharedEngine().playEffect(MainMenuLayer.context, R.raw.click); 
		
		radioSet.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2f + 60*scaleFactor));
		diffScene.removeChild(radioSet, true);
		diffScene.addChild(radioSet);
		MainActivity.DIFFICULT = "easy";
	}
	
	public void buttonMediumCallBack(Object sender) {
		if(MainActivity.AUDIO)
			SoundEngine.sharedEngine().playEffect(MainMenuLayer.context, R.raw.click);
		
		radioSet.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2f));
		diffScene.removeChild(radioSet, true);
		diffScene.addChild(radioSet);	
		MainActivity.DIFFICULT = "medium";
	}

	public void buttonHardCallBack(Object sender) {
		if(MainActivity.AUDIO)
			SoundEngine.sharedEngine().playEffect(MainMenuLayer.context, R.raw.click);
		
		radioSet.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2 - 60*scaleFactor));
		diffScene.removeChild(radioSet, true);
		diffScene.addChild(radioSet);
		MainActivity.DIFFICULT = "hard";
	}
	/*< end LevelScene >*/
	
	/*< Back Button >*/
	public static CCMenu buttonBack(Object sender, float posY) {
		CCMenuItemImage buttonBack = CCMenuItemImage.item("button/buttonBack.png", "button/buttonBack.png", (CCLayer)sender, "buttonBackCallBack");
		buttonBack.setScale(1f *scaleFactor); 
		buttonBack.setAnchorPoint(CGPoint.ccp(0.5f, 0f));
		
		CCMenu buttonBackMenu = CCMenu.menu(buttonBack);
		buttonBackMenu.setContentSize(buttonBack.getContentSize());
		buttonBackMenu.setPosition(CGPoint.ccp(screenSize.width/2f, posY));
		
		return buttonBackMenu;
	}
	
	public void buttonBackCallBack(Object sender) {
		if(MainActivity.AUDIO)
			SoundEngine.sharedEngine().playEffect(MainMenuLayer.context, R.raw.click);
		
		CCDirector.sharedDirector().replaceScene(MainMenuLayerDepth1.optionsScene());
	}
	/*< end Back Button >*/
	
}
