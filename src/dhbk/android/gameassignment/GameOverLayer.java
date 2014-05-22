package dhbk.android.gameassignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

import android.os.Environment;
import android.view.KeyEvent;

public class GameOverLayer extends CCLayer{
	private static CGSize screenSize;
	private static float scaleFactor;
	
	protected GameOverLayer() {
		this.setIsKeyEnabled(true);
		this.setIsTouchEnabled(true);
		
		screenSize = CCDirector.sharedDirector().winSize();
		scaleFactor  = CCDirector.sharedDirector().winSize().height / 500f;
		
		if(MainActivity.WIN) {
			int tmp = Integer.parseInt(MainActivity.HIGH_SCORES[2]);
			if(MainActivity.SCORES > Integer.parseInt(MainActivity.HIGH_SCORES[0])) {
				MainActivity.HIGH_SCORES[2] = MainActivity.HIGH_SCORES[1];
				MainActivity.HIGH_SCORES[1] = MainActivity.HIGH_SCORES[0];
				MainActivity.HIGH_SCORES[0] = String.valueOf(MainActivity.SCORES);
			} else if(MainActivity.SCORES > Integer.parseInt(MainActivity.HIGH_SCORES[1])) {
				MainActivity.HIGH_SCORES[2] = MainActivity.HIGH_SCORES[1];
				MainActivity.HIGH_SCORES[1] = String.valueOf(MainActivity.SCORES);
			} else if(MainActivity.SCORES > Integer.parseInt(MainActivity.HIGH_SCORES[2])) {
				MainActivity.HIGH_SCORES[2] = String.valueOf(MainActivity.SCORES);
			}
			File f = new File(Environment.getExternalStorageDirectory().getPath() + "/memory/" + "highScores.txt");
			f.delete();
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writeScoresFile(MainActivity.HIGH_SCORES[0]);
			writeScoresFile(MainActivity.HIGH_SCORES[1]);
			writeScoresFile(MainActivity.HIGH_SCORES[2]);
			
			CCSprite backgroundGameWin = CCSprite.sprite("background/backgroundGameWin.jpg");
			backgroundGameWin.setAnchorPoint(0.5f, 1f);
			backgroundGameWin.setScaleX(screenSize.width/backgroundGameWin.getContentSize().width);
			backgroundGameWin.setScaleY(screenSize.height/backgroundGameWin.getContentSize().height);
			backgroundGameWin.setPosition(screenSize.width/2f, screenSize.height);
			addChild(backgroundGameWin);
			
			/*< gamewin >*/
			CCLabel gameWin = CCLabel.makeLabel("New Highscore", "HeadingFont.ttf", 24);
			ccColor3B color = ccColor3B.ccc3(225, 0, 0);
			gameWin.setColor(color);
			gameWin.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
			gameWin.setPosition(screenSize.width/2f, screenSize.height/2f + 40*scaleFactor);
			if(MainActivity.SCORES > tmp) {
				addChild(gameWin);
				CCLabel newScore = CCLabel.makeLabel(String.valueOf(MainActivity.SCORES), "HeadingFont.ttf", 24);
				newScore.setColor(color);
				newScore.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
				newScore.setPosition(screenSize.width/2f, screenSize.height/2f);
				addChild(newScore);
			}
			
			/*< new highscore >*/
			CCLabel newHighScore = CCLabel.makeLabel("You Win", "HeadingFont.ttf", 24);
			ccColor3B colorNHS = ccColor3B.ccc3(225, 0, 0);
			newHighScore.setColor(colorNHS);
			newHighScore.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
			newHighScore.setPosition(screenSize.width/2f, screenSize.height/2f - 40f*scaleFactor);
			if(MainActivity.SCORES > tmp)
				addChild(newHighScore);
			
			/*< Play again >*/
			CCMenuItemImage buttonPlayAgain = CCMenuItemImage.item("button/buttonPlayAgain.png", "button/buttonPlayAgain.png", this, "buttonPlayAgainCallBack");
			buttonPlayAgain.setScale(1f*scaleFactor);
			buttonPlayAgain.setAnchorPoint(CGPoint.ccp(0.5f, 0f));
			
			CCMenu buttonPlayAgainMenu = CCMenu.menu(buttonPlayAgain);
			buttonPlayAgainMenu.setContentSize(buttonPlayAgain.getContentSize());
			buttonPlayAgainMenu.setPosition(CGPoint.ccp(screenSize.width/2f, 50*scaleFactor));
			addChild(buttonPlayAgainMenu);
		}else {
			CCSprite backgroundGameOver = CCSprite.sprite("background/backgroundGameOver.jpg");
			backgroundGameOver.setAnchorPoint(0.5f, 1f);
			backgroundGameOver.setScaleX(screenSize.width/backgroundGameOver.getContentSize().width);
			backgroundGameOver.setScaleY(screenSize.height/backgroundGameOver.getContentSize().height);
			backgroundGameOver.setPosition(screenSize.width/2f, screenSize.height);
			addChild(backgroundGameOver);
			
			/*< gameover >*/
			CCLabel gameOver = CCLabel.makeLabel("Game Over", "HeadingFont.ttf", 24);
			ccColor3B color = ccColor3B.ccc3(225, 255, 0);
			gameOver.setColor(color);
			gameOver.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
			gameOver.setPosition(screenSize.width/2f, screenSize.height/2f);
			addChild(gameOver);
			
			/*< Play again >*/
			CCMenuItemImage buttonPlayAgain = CCMenuItemImage.item("button/buttonPlayAgain.png", "button/buttonPlayAgain.png", this, "buttonPlayAgainCallBack");
			buttonPlayAgain.setScale(1f*scaleFactor);
			buttonPlayAgain.setAnchorPoint(CGPoint.ccp(0.5f, 0f));
			
			CCMenu buttonPlayAgainMenu = CCMenu.menu(buttonPlayAgain);
			buttonPlayAgainMenu.setContentSize(buttonPlayAgain.getContentSize());
			buttonPlayAgainMenu.setPosition(CGPoint.ccp(screenSize.width/2f, 50*scaleFactor));
			addChild(buttonPlayAgainMenu);
		}
	}
	
	@Override
	public boolean ccKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			MainActivity.SCORES = 0;
			MainActivity.LEVEL = 1;
			CCDirector.sharedDirector().replaceScene(GamePlayLayer.scene());
		}
	    return super.ccKeyDown(keyCode, event);
	}
	
	public void buttonPlayAgainCallBack(Object sender) {
		if(MainActivity.AUDIO) {
			MainActivity.SCORES = 0;
			SoundEngine.sharedEngine().playEffect(MainMenuLayer.context, R.raw.click);
		}
		CCScene scene = GamePlayLayer.scene();
		CCDirector.sharedDirector().replaceScene(scene);
	}
	
	public static CCScene scene() {
		CCScene scene = CCScene.node();
		CCLayer layer = new GameOverLayer();
		scene.addChild(layer);
		
		return scene;
	}
	
	public static void writeScoresFile(String score) {
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state)) {
			File gameDir = new File(Environment.getExternalStorageDirectory().getPath() + "/memory");
			gameDir.mkdirs();
			FileWriter out;
			try {
				out = new FileWriter(new File(gameDir, "highScores.txt"), true);

				out.write(score + "\n");
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
