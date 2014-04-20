package dhbk.android.gameassignment;

import org.cocos2d.actions.CCProgressTimer;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

public class GamePlayLayer extends CCLayer {
	private static CGSize screenSize;
	private static float scaleFactor;
	private float buttonWidth;
	private float newRate;
	private CCProgressTimer progressBarBG;
	private CCProgressTimer progressBar;
	
	protected GamePlayLayer() {
		// TODO Auto-generated constructor stub
		this.setIsTouchEnabled(true);
		
		screenSize = CCDirector.sharedDirector().winSize();
		scaleFactor  = CCDirector.sharedDirector().winSize().height / 500;
		
		/*< Pic Background >*/
		CCSprite backgroundGame = CCSprite.sprite("backgroundGame2.jpg");
		backgroundGame.setScale(screenSize.width / backgroundGame.getContentSize().width);
		backgroundGame.setAnchorPoint(CGPoint.ccp(0.5f,1f));
		backgroundGame.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height));
		addChild(backgroundGame);
		
		/*< Progress bar >*/
		progressBarBG = CCProgressTimer.progress("progressBarBG.png");
		progressBarBG.setScale(screenSize.width / progressBarBG.getContentSize().width);
		progressBarBG.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		progressBarBG.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height - progressBarBG.getContentSize().height/2*scaleFactor));
		progressBarBG.setPercentage(100);
		addChild(progressBarBG);
		
		progressBar = CCProgressTimer.progress("progressBar.png");
		progressBar.setType(CCProgressTimer.kCCProgressTimerTypeHorizontalBarLR);
		progressBar.setScale(screenSize.width / progressBarBG.getContentSize().width);
		progressBar.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		progressBar.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height - progressBarBG.getContentSize().height/2*scaleFactor));
		progressBar.setPercentage(75);
		addChild(progressBar);
		/*< end Progress bar >*/
		
		buttonWidth = screenSize.width/3;
		newRate = 1;
		
		/*< Back >*/
		CCMenuItemImage buttonBack = CCMenuItemImage.item("buttonBack.png", "buttonBack.png", this, "buttonBackCallBack");
		if(buttonWidth < buttonBack.getContentSize().width) {
			newRate = buttonWidth/buttonBack.getContentSize().width;
			buttonBack.setScale(newRate);
		}else buttonBack.setScale(1f*scaleFactor);
		buttonBack.setAnchorPoint(CGPoint.ccp(0f, 0f));
		
		CCMenu buttonBackMenu = CCMenu.menu(buttonBack);
		buttonBackMenu.setContentSize(buttonBack.getContentSize());
		buttonBackMenu.setPosition(CGPoint.ccp(0f, 0f));
		addChild(buttonBackMenu);
		
		/*< Play >*/
		CCMenuItemImage buttonStart = CCMenuItemImage.item("buttonStart.png", "buttonStart.png", this, "buttonStartCallBack");
		if(buttonWidth < buttonStart.getContentSize().width) {
			newRate = buttonWidth/buttonStart.getContentSize().width;
			buttonStart.setScale(newRate);
		}else buttonStart.setScale(1f*scaleFactor);
		buttonStart.setAnchorPoint(CGPoint.ccp(1f, 0f));
		
		CCMenu buttonStartMenu = CCMenu.menu(buttonStart);
		buttonStartMenu.setContentSize(buttonStart.getContentSize());
		buttonStartMenu.setPosition(CGPoint.ccp(screenSize.width, 0f));
		addChild(buttonStartMenu);
		
		/*< Scores >*/
		CCLabel scores = CCLabel.makeLabel("Scores:", "HeadingFont.ttf", 24);
		ccColor3B colorScores = ccColor3B.ccc3(225, 0, 0);		
		scores.setColor(colorScores);
		scores.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		scores.setPosition(CGPoint.ccp(screenSize.width/2, buttonStart.getContentSize().height*newRate));
		addChild(scores);
	}

	public static CCScene scene() {
		CCScene scene = CCScene.node();
		CCLayer layer = new GamePlayLayer();
		scene.addChild(layer);
//		Thread a = new Thread("a");
				
		return scene;
	}
	
	public void buttonBackCallBack(Object sender) {
		CCDirector.sharedDirector().replaceScene(MainMenuLayer.scene());
	}
	
}
