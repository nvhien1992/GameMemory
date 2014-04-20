package dhbk.android.gameassignment;

import android.os.Environment;
import android.view.KeyEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

public class MainMenuLayerDepth1 extends CCLayer{
	private static CGSize screenSize;
	private static float scaleFactor;
	private static CCScene optScene;
	private static float radioHeart;
	private static CCSprite radioMusicSet;
	private static CCSprite radioAudioSet;

	protected MainMenuLayerDepth1() {
		this.setIsKeyEnabled(true);
		this.setIsTouchEnabled(true);		
		screenSize = CCDirector.sharedDirector().winSize();
		scaleFactor  = CCDirector.sharedDirector().winSize().height / 500 ;
		
		/*< Music Set >*/
		radioMusicSet = CCSprite.sprite("radioSet.png");
		radioMusicSet.setScale(1f*scaleFactor); 
		radioMusicSet.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		
		/*< Audio Set >*/
		radioAudioSet = CCSprite.sprite("radioSet.png");
		radioAudioSet.setScale(1f*scaleFactor); 
		radioAudioSet.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		
		/*< Background >*/
		CCSprite backgroundMainMenu = CCSprite.sprite("backgroundMainMenu.jpg");
		backgroundMainMenu.setScale(screenSize.width / backgroundMainMenu.getContentSize().width);
		backgroundMainMenu.setAnchorPoint(CGPoint.ccp(0.5f,1f));
		backgroundMainMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height));
		addChild(backgroundMainMenu);
	}
	
	@Override
	public boolean ccKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)
			CCDirector.sharedDirector().replaceScene(MainMenuLayer.scene());
	    return super.ccKeyDown(keyCode, event);
	}
			
	/*< Details Options >*/
	public static CCScene optionsScene() {
		optScene = CCScene.node();
		CCLayer layer = new MainMenuLayerDepth1();
		optScene.addChild(layer);
		
		/*< Music >*/
		CCMenuItemImage buttonMusic = CCMenuItemImage.item("buttonMusic.png", "buttonMusic.png", layer, "buttonMusicCallBack");
		buttonMusic.setScale(1f *scaleFactor); 
		buttonMusic.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		
		CCMenu buttonMusicMenu = CCMenu.menu(buttonMusic);
		buttonMusicMenu.setContentSize(buttonMusic.getContentSize());
		buttonMusicMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height/2 + 80*scaleFactor));
		optScene.addChild(buttonMusicMenu);
		
		/*< Audio >*/
		CCMenuItemImage buttonAudio = CCMenuItemImage.item("buttonAudio.png", "buttonAudio.png", layer, "buttonAudioCallBack");
		buttonAudio.setScale(1f *scaleFactor); 
		buttonAudio.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		
		CCMenu buttonAudioMenu = CCMenu.menu(buttonAudio);
		buttonAudioMenu.setContentSize(buttonAudio.getContentSize());
		buttonAudioMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height/2));
		optScene.addChild(buttonAudioMenu);
		
		radioHeart = screenSize.width/2 - buttonAudio.getContentSize().width/2*scaleFactor;	
		
		/*< radioMusic >*/
		CCSprite radioMusicBG = CCSprite.sprite("radioBG.png");		
		radioMusicBG.setScale(1f*scaleFactor); 
		radioMusicBG.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		radioMusicBG.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2 + 80*scaleFactor));
		optScene.addChild(radioMusicBG);
		
		CCSprite radioMusicEmpty = CCSprite.sprite("radioEmpty.png");
		radioMusicEmpty.setScale(1f*scaleFactor); 
		radioMusicEmpty.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		radioMusicEmpty.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2 + 80*scaleFactor));
		optScene.addChild(radioMusicEmpty);
		
		if(MainActivity.MUSIC) {
			radioMusicSet.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2 + 80*scaleFactor));
			optScene.removeChild(radioMusicSet, true);
			optScene.addChild(radioMusicSet);
		}
				
		/*< radioAudio >*/
		CCSprite radioAudioBG = CCSprite.sprite("radioBG.png");
		radioAudioBG.setScale(1f*scaleFactor); 
		radioAudioBG.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		radioAudioBG.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2));
		optScene.addChild(radioAudioBG);
		
		CCSprite radioAudioEmpty = CCSprite.sprite("radioEmpty.png");
		radioAudioEmpty.setScale(1f *scaleFactor); 
		radioAudioEmpty.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		radioAudioEmpty.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2));
		optScene.addChild(radioAudioEmpty);
		
		if(MainActivity.AUDIO) {
			radioAudioSet.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2));
			optScene.removeChild(radioAudioSet, true);
			optScene.addChild(radioAudioSet);
		}
		
		/*< Level >*/
		CCMenuItemImage buttonLevel = CCMenuItemImage.item("buttonLevel.png", "buttonLevel.png", layer, "buttonLevelCallBack");
		buttonLevel.setScale(1f *scaleFactor); 
		buttonLevel.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		
		CCMenu buttonLevelMenu = CCMenu.menu(buttonLevel);
		buttonLevelMenu.setContentSize(buttonLevel.getContentSize());
		buttonLevelMenu.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height/2 - 80*scaleFactor));
		optScene.addChild(buttonLevelMenu);
		
		/*< MainMenu >*/
		optScene.addChild(buttonBackMainMenu(layer, 50*scaleFactor));
		
		return optScene;
	}
	
	public void buttonMusicCallBack(Object sender) {
		radioMusicSet.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2 + 80*scaleFactor));
		if(MainActivity.MUSIC) {
			MainActivity.MUSIC = false;
			optScene.removeChild(radioMusicSet, true);
		}else {
			MainActivity.MUSIC = true;
			optScene.addChild(radioMusicSet);
		}
	}
	
	public void buttonAudioCallBack(Object sender) {
		radioAudioSet.setPosition(CGPoint.ccp(radioHeart, screenSize.height/2));
		if(MainActivity.AUDIO) {
			MainActivity.AUDIO = false;
			optScene.removeChild(radioAudioSet, true);
		}else {
			MainActivity.AUDIO = true;
			optScene.addChild(radioAudioSet);
		}
	}
	
	public void buttonLevelCallBack(Object sender) {
		CCScene scene = MainMenuLayerDepth2.levelScene();
		CCDirector.sharedDirector().runWithScene(scene);	
	}
	/*< end Details Options >*/
	
	/*< Details of HighScores >*/
	public static CCScene highScoresScene() throws IOException { //to be changed
		CCScene scene = CCScene.node();
		CCLayer layer = new MainMenuLayerDepth1();
		scene.addChild(layer);
		File f = new File(Environment.getExternalStorageDirectory().getPath() + "/memory/" + "highScores.txt");
		f.delete();
		writeScoresFile("hien", "6969");
		writeScoresFile("nvh", "6600");
		writeScoresFile("nvhien", "6000");
		
		/*< Clear >*/
		CCMenuItemImage buttonClear = CCMenuItemImage.item("buttonClear.png", "buttonClear.png", layer, "buttonClearCallBack");
		buttonClear.setScale(1f *scaleFactor); 
		buttonClear.setAnchorPoint(CGPoint.ccp(0.5f, 0f));
		
		CCMenu buttonClearMenu = CCMenu.menu(buttonClear);
		buttonClearMenu.setContentSize(buttonClear.getContentSize());
		buttonClearMenu.setPosition(CGPoint.ccp(screenSize.width/2, 115*scaleFactor));
		scene.addChild(buttonClearMenu);
		
		/*< MainMenu >*/
		scene.addChild(buttonBackMainMenu(layer, 50*scaleFactor));
		
		readScoresFile();
		
		CCLabel player1 = CCLabel.makeLabel("1. " + MainActivity.HIGH_SCORES[0], "HeadingFont.ttf", 24);
		CCLabel score1 = CCLabel.makeLabel(MainActivity.HIGH_SCORES[1], "HeadingFont.ttf", 24);
		CCLabel player2 = CCLabel.makeLabel("2. " + MainActivity.HIGH_SCORES[2], "HeadingFont.ttf", 24);
		CCLabel score2 = CCLabel.makeLabel(MainActivity.HIGH_SCORES[3], "HeadingFont.ttf", 24);
		CCLabel player3 = CCLabel.makeLabel("3. " + MainActivity.HIGH_SCORES[4], "HeadingFont.ttf", 24);
		CCLabel score3 = CCLabel.makeLabel(MainActivity.HIGH_SCORES[5], "HeadingFont.ttf", 24);
		
		highScoresView(player1, score1, screenSize.height - 60*scaleFactor);
		scene.addChild(player1);
		scene.addChild(score1);
		
		highScoresView(player2, score2, screenSize.height - 100*scaleFactor);
		scene.addChild(player2);
		scene.addChild(score2);
		
		highScoresView(player3, score3, screenSize.height - 140*scaleFactor);
		scene.addChild(player3);
		scene.addChild(score3);
		
		return scene;
	}
	
	public static void highScoresView(CCLabel player, CCLabel scoreLb, float posY) {
		player.setColor(ccColor3B.ccc3(255, 0, 0));
		player.setAnchorPoint(CGPoint.ccp(0.5f,0f));
		player.setPosition(CGPoint.ccp(100*scaleFactor, posY));
		
		scoreLb.setColor(ccColor3B.ccc3(255, 111, 0));
		scoreLb.setAnchorPoint(CGPoint.ccp(0.5f,0f));
		scoreLb.setPosition(CGPoint.ccp(screenSize.width/2 + 20*scaleFactor, posY));
	}
		
	public static void writeScoresFile(String name, String score) throws IOException {
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state)) {
			File gameDir = new File(Environment.getExternalStorageDirectory().getPath() + "/memory");
			gameDir.mkdirs();
			FileWriter out = new FileWriter(new File(gameDir, "highScores.txt"), true);

			out.write(name + "\n");
			out.write(score + "\n");
			out.close();
		}
	}
	
	public static void readScoresFile() throws IOException {
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state)) {
			File gameDir = new File(Environment.getExternalStorageDirectory().getPath() + "/memory");
			gameDir.mkdirs();
			BufferedReader in = null;
			String line;
			try {
				int i = 0;
				in = new BufferedReader(new FileReader(new File(gameDir, "highScores.txt")));
				while((line = in.readLine()) != null) {
					MainActivity.HIGH_SCORES[i] = line;
					i++;
				}
				in.close();
			}catch(FileNotFoundException e) {
				
			}catch(IOException e) {
				
			}
		}
	}
	
	public void buttonClearCallBack(Object sender) throws IOException {
		File f = new File(Environment.getExternalStorageDirectory().getPath() + "/memory/" + "highScores.txt");
		f.delete();
		f.createNewFile();
//		highScoresView(player1, score1, "", "", 24, screenSize.height - 40*scaleFactor);
//		this.addChild(player1);
//		this.addChild(score1);
		
//		highScoresView(player2, score2, "", "", 24, screenSize.height - 60*scaleFactor);
//		this.addChild(player2);
//		this.addChild(score2);
		
//		highScoresView(player3, score3, "", "", 24, screenSize.height - 80*scaleFactor);
//		this.addChild(player3);
//		this.addChild(score3);
	}
	/*< end Details of HighScores >*/
	
	/*< Details of About >*/
	public static CCScene aboutScene() {
		CCScene scene = CCScene.node();
		CCLayer layer = new MainMenuLayerDepth1();
		scene.addChild(layer);
		
		CCLabel name = CCLabel.makeLabel("Nguyen Van Hien", "HeadingFont.ttf", 24);
		name.setColor(ccColor3B.ccc3(255, 111, 0));
		name.setAnchorPoint(CGPoint.ccp(0.5f,1f));
		name.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height - 20*scaleFactor));
		scene.addChild(name);
		
		CCLabel id = CCLabel.makeLabel("51001042", "HeadingFont.ttf", 24);
		id.setColor(ccColor3B.ccc3(255, 111, 0));
		id.setAnchorPoint(CGPoint.ccp(0.5f,1f));
		id.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height - 40*scaleFactor));
		scene.addChild(id);
		
		CCLabel email = CCLabel.makeLabel("nvhien1992@gmail.com", "HeadingFont.ttf", 24);
		email.setColor(ccColor3B.ccc3(255, 111, 0));
		email.setAnchorPoint(CGPoint.ccp(0.5f,1f));
		email.setPosition(CGPoint.ccp(screenSize.width/2, screenSize.height - 60*scaleFactor));
		scene.addChild(email);
		
		scene.addChild(buttonBackMainMenu(layer, 50*scaleFactor));
		
		return scene;
	}
	/*< end Details of About >*/
	
	
	/*< MainMenu Button >*/
	public static CCMenu buttonBackMainMenu(Object sender, float posY) {
		CCMenuItemImage buttonMainMenu = CCMenuItemImage.item("buttonMainMenu.png", "buttonMainMenu.png", (CCLayer)sender, "buttonMainMenuCallBack");
		buttonMainMenu.setScale(1f *scaleFactor); 
		buttonMainMenu.setAnchorPoint(CGPoint.ccp(0.5f, 0f));
		
		CCMenu buttonMainMenuMenu = CCMenu.menu(buttonMainMenu);
		buttonMainMenuMenu.setContentSize(buttonMainMenu.getContentSize());
		buttonMainMenuMenu.setPosition(CGPoint.ccp(screenSize.width/2, posY));
		
		return buttonMainMenuMenu;
	}
	
	public void buttonMainMenuCallBack(Object sender) {
		CCDirector.sharedDirector().replaceScene(MainMenuLayer.scene());
	}
	/*< end MainMenu Button >*/

}
