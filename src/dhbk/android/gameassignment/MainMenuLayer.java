package dhbk.android.gameassignment;

import java.io.IOException;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

public class MainMenuLayer extends CCLayer {
	private static CGSize screenSize;
	private static float scaleFactor;
	
	protected MainMenuLayer() {
		this.setIsTouchEnabled(true);
		
		screenSize = CCDirector.sharedDirector().winSize();
		scaleFactor  = CCDirector.sharedDirector().winSize().height / 500 ;
		
		/*< Background >*/
		CCSprite backgroundMainMenu = CCSprite.sprite("backgroundMainMenu.jpg");
		backgroundMainMenu.setScale(screenSize.width / backgroundMainMenu.getContentSize().width);
		backgroundMainMenu.setAnchorPoint(CGPoint.ccp(0.5f,1f));
		backgroundMainMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height));
		addChild(backgroundMainMenu);
		
		/*< Game Title >*/ 
		CCSprite gameName = CCSprite.sprite("gameName.png");
		gameName.setAnchorPoint(CGPoint.ccp(0.5f, 1f));
		gameName.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height - 50*scaleFactor));
		gameName.setScale(1f*scaleFactor);
		addChild(gameName);
		
		float bottomObj = gameName.getContentSize().height + 50*scaleFactor;
		
		/*< Buttons >*/
		/* Play */
		CCMenuItemImage buttonPlay = CCMenuItemImage.item("buttonPlay.png", "buttonPlay.png", this, "buttonPlayCallBack");
		buttonPlay.setScale(1f *scaleFactor); 
		buttonPlay.setAnchorPoint(CGPoint.ccp(0.5f, 1f));
		 
		CCMenu buttonPlayMenu = CCMenu.menu(buttonPlay);
		buttonPlayMenu.setContentSize(buttonPlay.getContentSize());
		buttonPlayMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height - bottomObj - 40*scaleFactor));
		addChild(buttonPlayMenu);
		
		float buttonHeight = buttonPlay.getContentSize().height*scaleFactor;
		
		bottomObj += buttonHeight + 40*scaleFactor;
		
		/* Options */
		CCMenuItemImage buttonOptions = CCMenuItemImage.item("buttonOptions.png", "buttonOptions.png", this, "buttonOptionsCallBack");
		buttonOptions.setScale(1f *scaleFactor); 
		buttonOptions.setAnchorPoint(CGPoint.ccp(0.5f, 1f));
		
		CCMenu buttonOptionsMenu = CCMenu.menu(buttonOptions);
		buttonOptionsMenu.setContentSize(buttonOptions.getContentSize());
		buttonOptionsMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height - bottomObj - 20*scaleFactor));
		addChild(buttonOptionsMenu);
		
		bottomObj += buttonHeight + 20*scaleFactor;
		
		/* High Scores */
		CCMenuItemImage buttonHighScores = CCMenuItemImage.item("buttonHighScores.png", "buttonHighScores.png",this, "buttonHighScoresCallBack");
		buttonHighScores.setScale(1f *scaleFactor); 
		buttonHighScores.setAnchorPoint(CGPoint.ccp(0.5f, 1f));
		
		CCMenu buttonHighScoresMenu = CCMenu.menu(buttonHighScores);
		buttonHighScoresMenu.setContentSize(buttonHighScores.getContentSize());
		buttonHighScoresMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height - bottomObj - 20*scaleFactor));
		addChild(buttonHighScoresMenu);
		
		bottomObj += buttonHeight + 20*scaleFactor;
		
		/* About */
		CCMenuItemImage buttonAbout = CCMenuItemImage.item("buttonAbout.png", "buttonAbout.png", this, "buttonAboutCallBack");
		buttonAbout.setScale(1f *scaleFactor); 
		buttonAbout.setAnchorPoint(CGPoint.ccp(0.5f, 1f));
		
		CCMenu buttonAboutMenu = CCMenu.menu(buttonAbout);
		buttonAboutMenu.setContentSize(buttonAbout.getContentSize());
		buttonAboutMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height - bottomObj - 20*scaleFactor));
		addChild(buttonAboutMenu);
	}
	
	public void buttonPlayCallBack(Object sender) {
		CCScene scene = GamePlayLayer.scene();
		CCDirector.sharedDirector().runWithScene(scene);
	}
	
	public void buttonOptionsCallBack(Object sender) {
		CCScene scene = MainMenuLayerDepth1.optionsScene();
		CCDirector.sharedDirector().runWithScene(scene);
	}
	
	/*
	 * @ to be changed.
	 */
	public void buttonHighScoresCallBack(Object sender) throws IOException {
		CCScene scene = MainMenuLayerDepth1.highScoresScene();
		CCDirector.sharedDirector().runWithScene(scene);
	}
	
	public void buttonAboutCallBack(Object sender) {
		CCScene scene = MainMenuLayerDepth1.aboutScene();
		CCDirector.sharedDirector().runWithScene(scene);
	}
	
	public static CCScene scene() {
		CCScene scene = CCScene.node();
		CCLayer layer = new MainMenuLayer();
		scene.addChild(layer);
		
		return scene;
	}
}
