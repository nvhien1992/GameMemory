package dhbk.android.gameassignment;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGSize;

import android.view.KeyEvent;

public class GameOverLayer extends CCLayer{
	private static CGSize screenSize;
	private static float scaleFactor;
	
	protected GameOverLayer() {
		this.setIsKeyEnabled(true);
		this.setIsTouchEnabled(true);
		
		screenSize = CCDirector.sharedDirector().winSize();
		scaleFactor  = CCDirector.sharedDirector().winSize().height / 500;
		
		if(MainActivity.WIN) {
			CCSprite backgroundGameWin = CCSprite.sprite("background/backgroundGameWin.jpg");
			backgroundGameWin.setAnchorPoint(0.5f, 1f);
			backgroundGameWin.setScaleX(screenSize.width/backgroundGameWin.getContentSize().width);
			backgroundGameWin.setScaleY(screenSize.height/backgroundGameWin.getContentSize().height);
			backgroundGameWin.setPosition(screenSize.width/2, screenSize.height);
			addChild(backgroundGameWin);
		}else {
			CCSprite backgroundGameOver = CCSprite.sprite("background/backgroundGameOver.jpg");
			backgroundGameOver.setAnchorPoint(0.5f, 1f);
			backgroundGameOver.setScaleX(screenSize.width/backgroundGameOver.getContentSize().width);
			backgroundGameOver.setScaleY(screenSize.height/backgroundGameOver.getContentSize().height);
			backgroundGameOver.setPosition(screenSize.width/2, screenSize.height);
			addChild(backgroundGameOver);
		}
	}
	
	@Override
	public boolean ccKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) 
			CCDirector.sharedDirector().replaceScene(GamePlayLayer.scene());
	    return super.ccKeyDown(keyCode, event);
	}
	
	public static CCScene scene() {
		CCScene scene = CCScene.node();
		CCLayer layer = new GameOverLayer();
		scene.addChild(layer);
		
		return scene;
	}
}
