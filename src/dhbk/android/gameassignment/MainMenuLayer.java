package dhbk.android.gameassignment;

import java.io.IOException;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import android.content.Context;
import android.view.KeyEvent;

public class MainMenuLayer extends CCLayer {
	public static Context context = CCDirector.sharedDirector().getActivity();
	private static CGSize screenSize;
	private static float scaleFactor;
	private static float bottomObj;
	private static CCSprite buttonPlay2;
	private static CCMenuItemImage buttonOptions;
	
	protected MainMenuLayer() {
		this.setIsKeyEnabled(true);
		this.setIsTouchEnabled(true);
		
		screenSize = CCDirector.sharedDirector().winSize();
		scaleFactor  = CCDirector.sharedDirector().winSize().height / 500;
		
		/*< Background >*/
		CCSprite backgroundMainMenu = CCSprite.sprite("background/backgroundMainMenu.jpg");
		backgroundMainMenu.setScaleX(screenSize.width / backgroundMainMenu.getContentSize().width);
		backgroundMainMenu.setScaleY(screenSize.height / backgroundMainMenu.getContentSize().height);
		backgroundMainMenu.setAnchorPoint(CGPoint.ccp(0.5f,1f));
		backgroundMainMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height));
		addChild(backgroundMainMenu);
		
		/*< Game Title >*/ 
		CCSprite gameName = CCSprite.sprite("gameName.png");
		gameName.setAnchorPoint(CGPoint.ccp(0.5f, 1f));
		gameName.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height - 50*scaleFactor));
		gameName.setScale(1f*scaleFactor);
		addChild(gameName);
		
		bottomObj = gameName.getContentSize().height + 50*scaleFactor;
		
		/*< Buttons >*/
		/* Play */
		CCMenuItemImage buttonPlay = CCMenuItemImage.item("button/buttonPlay.png", "button/buttonPlay.png", this, "buttonPlayCallBack");
		buttonPlay.setScale(1f *scaleFactor); 
		buttonPlay.setAnchorPoint(CGPoint.ccp(0.5f, 1f));
		 
		CCMenu buttonPlayMenu = CCMenu.menu(buttonPlay);
		buttonPlayMenu.setContentSize(buttonPlay.getContentSize());
		buttonPlayMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height - bottomObj - 60*scaleFactor));
		addChild(buttonPlayMenu);
		
		buttonPlay2 = CCSprite.sprite("button/buttonPlay2.png");
		buttonPlay2.setScale(1f *scaleFactor); 
		buttonPlay2.setAnchorPoint(CGPoint.ccp(0.5f, 1f));
		buttonPlay2.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height - bottomObj - 60*scaleFactor));
		
		float buttonHeight = buttonPlay.getContentSize().height*scaleFactor;
		
		bottomObj += buttonHeight + 60*scaleFactor;
		
		/* Options */
		buttonOptions = CCMenuItemImage.item("button/buttonOptions.png", "button/buttonOptions.png", this, "buttonOptionsCallBack");
		buttonOptions.setScale(1f *scaleFactor); 
		buttonOptions.setAnchorPoint(CGPoint.ccp(0.5f, 1f));
		
		CCMenu buttonOptionsMenu = CCMenu.menu(buttonOptions);
		buttonOptionsMenu.setContentSize(buttonOptions.getContentSize());
		buttonOptionsMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height - bottomObj - 20*scaleFactor));
		addChild(buttonOptionsMenu);
		
		bottomObj += buttonHeight + 20*scaleFactor;
		
		/* High Scores */
		CCMenuItemImage buttonHighScores = CCMenuItemImage.item("button/buttonHighScores.png", "button/buttonHighScores.png",this, "buttonHighScoresCallBack");
		buttonHighScores.setScale(1f *scaleFactor); 
		buttonHighScores.setAnchorPoint(CGPoint.ccp(0.5f, 1f));
		
		CCMenu buttonHighScoresMenu = CCMenu.menu(buttonHighScores);
		buttonHighScoresMenu.setContentSize(buttonHighScores.getContentSize());
		buttonHighScoresMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height - bottomObj - 20*scaleFactor));
		addChild(buttonHighScoresMenu);
		
		bottomObj += buttonHeight + 20*scaleFactor;
		
		/* About */
		CCMenuItemImage buttonAbout = CCMenuItemImage.item("button/buttonAbout.png", "button/buttonAbout.png", this, "buttonAboutCallBack");
		buttonAbout.setScale(1f *scaleFactor); 
		buttonAbout.setAnchorPoint(CGPoint.ccp(0.5f, 1f));
		
		CCMenu buttonAboutMenu = CCMenu.menu(buttonAbout);
		buttonAboutMenu.setContentSize(buttonAbout.getContentSize());
		buttonAboutMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height - bottomObj - 20*scaleFactor));
		addChild(buttonAboutMenu);
		
		if(MainActivity.MUSIC)
			SoundEngine.sharedEngine().playSound(context, R.raw.background_music_aac, true);
		else SoundEngine.sharedEngine().pauseSound();
		SoundEngine.sharedEngine().preloadEffect(context, R.raw.click);
	}
	
	@Override
	public boolean ccKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			SoundEngine.sharedEngine().pauseSound();
			CCDirector.sharedDirector().end();			
		}
		if(keyCode == KeyEvent.KEYCODE_HOME)
			SoundEngine.sharedEngine().pauseSound();
	    return super.ccKeyDown(keyCode, event);
	}
	
	public void buttonPlayCallBack(Object sender) {
		if(MainActivity.AUDIO)
			SoundEngine.sharedEngine().playEffect(context, R.raw.click);
//		addChild(buttonPlay2);
		CCScene scene = GamePlayLayer.scene();
		CCDirector.sharedDirector().runWithScene(scene);
	}
	
	public void buttonOptionsCallBack(Object sender) {
		if(MainActivity.AUDIO)
			SoundEngine.sharedEngine().playEffect(context, R.raw.click);
		CCScene scene = MainMenuLayerDepth1.optionsScene();
		CCDirector.sharedDirector().runWithScene(scene);
	}
	
	public void buttonHighScoresCallBack(Object sender) throws IOException {
		if(MainActivity.AUDIO)
			SoundEngine.sharedEngine().playEffect(context, R.raw.click);
		CCScene scene = MainMenuLayerDepth1.highScoresScene();
		CCDirector.sharedDirector().runWithScene(scene);
	}
	
	public void buttonAboutCallBack(Object sender) {
		if(MainActivity.AUDIO)
			SoundEngine.sharedEngine().playEffect(context, R.raw.click);
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
