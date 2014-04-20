package dhbk.android.gameassignment;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

public class MainMenuLayerDepth2 extends CCLayer{
	private static CGSize screenSize;
	private static float scaleFactor;
	private static float radioHeart;
	private static CCSprite radioSet;
	private static CCScene lvScene;
	
	protected MainMenuLayerDepth2() {
		this.setIsTouchEnabled(true);
		
		screenSize = CCDirector.sharedDirector().winSize();
		scaleFactor  = CCDirector.sharedDirector().winSize().height / 500 ;
		
		radioSet = CCSprite.sprite("radioSet.png");
		radioSet.setScale(1f *scaleFactor); 
		radioSet.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		
		/*< Background >*/
		CCSprite backgroundMainMenu = CCSprite.sprite("backgroundMainMenu.jpg");
		backgroundMainMenu.setScale(screenSize.width / backgroundMainMenu.getContentSize().width);
		backgroundMainMenu.setAnchorPoint(CGPoint.ccp(0.5f,1f));
		backgroundMainMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height));
		addChild(backgroundMainMenu);
	}
	
	/*< LevelScene >*/
	public static CCScene levelScene() {
		lvScene = CCScene.node();
		CCLayer layer = new MainMenuLayerDepth2();
		lvScene.addChild(layer);
		
		/*< Easy >*/
		CCMenuItemImage buttonEasy = CCMenuItemImage.item("buttonEasy.png", "buttonEasy.png", layer, "buttonEasyCallBack");
		buttonEasy.setScale(1f *scaleFactor); 
		buttonEasy.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		
		CCMenu buttonEasyMenu = CCMenu.menu(buttonEasy);
		buttonEasyMenu.setContentSize(buttonEasy.getContentSize());
		buttonEasyMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height/2 + 60*scaleFactor));
		lvScene.addChild(buttonEasyMenu);
		
		/*< Medium >*/
		CCMenuItemImage buttonMedium = CCMenuItemImage.item("buttonMedium.png", "buttonMedium.png", layer, "buttonMediumCallBack");
		buttonMedium.setScale(1f *scaleFactor); 
		buttonMedium.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		
		CCMenu buttonMediumMenu = CCMenu.menu(buttonMedium);
		buttonMediumMenu.setContentSize(buttonMedium.getContentSize());
		buttonMediumMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height/2));
		lvScene.addChild(buttonMediumMenu);
		
		/*< Hard >*/
		CCMenuItemImage buttonHard = CCMenuItemImage.item("buttonHard.png", "buttonHard.png", layer, "buttonHardCallBack");
		buttonHard.setScale(1f *scaleFactor); 
		buttonHard.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		
		CCMenu buttonHardMenu = CCMenu.menu(buttonHard);
		buttonHardMenu.setContentSize(buttonHard.getContentSize());
		buttonHardMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height/2 - 60*scaleFactor));
		lvScene.addChild(buttonHardMenu);
		
		radioHeart = screenSize.width/2 - buttonHard.getContentSize().width/2*scaleFactor;		
		
		/*< radioEasy >*/
		CCSprite radioEasyBG = CCSprite.sprite("radioBG.png");
		CCSprite radioEasyEmpty = CCSprite.sprite("radioEmpty.png");
		radioEasyBG.setScale(1f *scaleFactor); 
		radioEasyBG.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		radioEasyBG.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2 + 60*scaleFactor));
		lvScene.addChild(radioEasyBG);
		
		radioEasyEmpty.setScale(1f *scaleFactor); 
		radioEasyEmpty.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		radioEasyEmpty.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2 + 60*scaleFactor));
		lvScene.addChild(radioEasyEmpty);
			
		/*< radioMedium >*/
		CCSprite radioMediumBG = CCSprite.sprite("radioBG.png");
		CCSprite radioMediumEmpty = CCSprite.sprite("radioEmpty.png");
		radioMediumBG.setScale(1f *scaleFactor); 
		radioMediumBG.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		radioMediumBG.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2));
		lvScene.addChild(radioMediumBG);
		
		radioMediumEmpty.setScale(1f *scaleFactor); 
		radioMediumEmpty.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		radioMediumEmpty.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2));
		lvScene.addChild(radioMediumEmpty);
		
		/*< radioHard >*/
		CCSprite radioHardBG = CCSprite.sprite("radioBG.png");
		CCSprite radioHardEmpty = CCSprite.sprite("radioEmpty.png");
		radioHardBG.setScale(1f *scaleFactor); 
		radioHardBG.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		radioHardBG.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2 - 60*scaleFactor));
		lvScene.addChild(radioHardBG);
		
		radioHardEmpty.setScale(1f *scaleFactor); 
		radioHardEmpty.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		radioHardEmpty.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2 - 60*scaleFactor));
		lvScene.addChild(radioHardEmpty);
		
		if(MainActivity.LEVEL == "easy")
			radioSet.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2 + 60*scaleFactor));
		else if(MainActivity.LEVEL == "medium")
			radioSet.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2));
		else radioSet.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2 - 60*scaleFactor)); 
		lvScene.addChild(radioSet);
		
		/*< Back >*/
		lvScene.addChild(buttonBack(layer, 50*scaleFactor));
		
		return lvScene;		
	}
	
	public void buttonEasyCallBack(Object sender) {
		radioSet.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2 + 60*scaleFactor));
		lvScene.removeChild(radioSet, true);
		lvScene.addChild(radioSet);
		MainActivity.LEVEL = "easy";
	}
	
	public void buttonMediumCallBack(Object sender) {
		radioSet.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2));
		lvScene.removeChild(radioSet, true);
		lvScene.addChild(radioSet);	
		MainActivity.LEVEL = "medium";
	}

	public void buttonHardCallBack(Object sender) {
		radioSet.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2 - 60*scaleFactor));
		lvScene.removeChild(radioSet, true);
		lvScene.addChild(radioSet);
		MainActivity.LEVEL = "hard";
	}
	/*< end LevelScene >*/
	
	/*< Back Button >*/
	public static CCMenu buttonBack(Object sender, float posY) {
		CCMenuItemImage buttonBack = CCMenuItemImage.item("buttonBack.png", "buttonBack.png", (CCLayer)sender, "buttonBackCallBack");
		buttonBack.setScale(1f *scaleFactor); 
		buttonBack.setAnchorPoint(CGPoint.ccp(0.5f, 0f));
		
		CCMenu buttonBackMenu = CCMenu.menu(buttonBack);
		buttonBackMenu.setContentSize(buttonBack.getContentSize());
		buttonBackMenu.setPosition(CGPoint.ccp(screenSize.width/2, posY));
		
		return buttonBackMenu;
	}
	
	public void buttonBackCallBack(Object sender) {
		CCDirector.sharedDirector().replaceScene(MainMenuLayerDepth1.optionsScene());
	}
	/*< end Back Button >*/
	
}
