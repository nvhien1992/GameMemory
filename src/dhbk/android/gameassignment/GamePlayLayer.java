package dhbk.android.gameassignment;

import java.util.Random;

import org.cocos2d.actions.CCProgressTimer;
import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

import android.view.KeyEvent;
import android.view.MotionEvent;

public class GamePlayLayer extends CCLayer {
	private String[] backgroundArr;
	private CCSprite[][] iconArr, spriteArr;
	private int[][] matrixElements, rowColChecked;
	private boolean[] checked;
	
	private CCLabel scores;
	private static CGSize screenSize;
	private CCProgressTimer progressBarBG, progressBar;
	private CCMenu buttonPauseMenu, buttonResumeMenu;
	
	private int percentage, time_s;
	private int checks, numOfElement, checkToWin, combo;
	private boolean start, flagCombo;
	
	private float interval;
	private float numOfPic;
	private float buttonWidth, buttonHeight;
	private float buttonScale;
	private float availableWidth, availableHeight, offsetHeight;
	private float elementWidth, elementHeight;
	private float elementScaleX, progBarScaleX;
	private float elementScaleY, progBarScaleY;
		
	protected GamePlayLayer() {
		// TODO Auto-generated constructor stub
		this.setIsKeyEnabled(true);
		this.setIsTouchEnabled(true);
							
		initGlobVar();
		
		/*< Progress bar >*/
		progressBarBG = CCProgressTimer.progress("progressBarBG.png");
		progBarScaleX = screenSize.width/progressBarBG.getContentSize().width;
		progBarScaleY = screenSize.height*(0.08f)/progressBarBG.getContentSize().height;
		progressBarBG.setScaleX(progBarScaleX);
		progressBarBG.setScaleY(progBarScaleY);
		progressBarBG.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		progressBarBG.setPosition(CGPoint.ccp(screenSize.width/(2f), screenSize.height - progressBarBG.getContentSize().height*progBarScaleY/(2f)));
		progressBarBG.setPercentage(100);
		addChild(progressBarBG);
		
		percentage = 0;
		progressBar = CCProgressTimer.progress("progressBar.png");
		progressBar.setType(CCProgressTimer.kCCProgressTimerTypeHorizontalBarLR);
		progressBar.setScaleX(progBarScaleX);
		progressBar.setScaleY(progBarScaleY);
		progressBar.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
		progressBar.setPosition(CGPoint.ccp(screenSize.width/(2f), screenSize.height - progressBarBG.getContentSize().height*progBarScaleY/(2f)));
		progressBar.setPercentage(percentage);
		addChild(progressBar);
		/*< end Progress bar >*/
		
		buttonWidth = (2f)*screenSize.width/(5f);
						
//		/*< Play >*/
//		CCMenuItemImage buttonStart = CCMenuItemImage.item("button/buttonStart.png", "button/buttonStart.png", this, "buttonStartCallBack");
//		buttonScale = buttonWidth/buttonStart.getContentSize().width;
//		buttonStart.setScale(buttonScale);
//		buttonStart.setAnchorPoint(CGPoint.ccp(1f, 0f));
//		
//		buttonStartMenu = CCMenu.menu(buttonStart);
//		buttonStartMenu.setContentSize(buttonStart.getContentSize());
//		buttonStartMenu.setPosition(CGPoint.ccp(screenSize.width, 0f));
				
		/*< Pause >*/
		CCMenuItemImage buttonPause = CCMenuItemImage.item("button/buttonPause.png", "button/buttonPause.png", this, "buttonPauseCallBack");
		buttonScale = buttonWidth/buttonPause.getContentSize().width;
		buttonPause.setScale(buttonScale);
		buttonPause.setAnchorPoint(CGPoint.ccp(1f, 0f));
		
		buttonPauseMenu = CCMenu.menu(buttonPause);
		buttonPauseMenu.setContentSize(buttonPause.getContentSize());
		buttonPauseMenu.setPosition(CGPoint.ccp(screenSize.width, 0f));
		
		/*< Resume >*/
		CCMenuItemImage buttonResume = CCMenuItemImage.item("button/buttonResume.png", "button/buttonResume.png", this, "buttonResumeCallBack");
		buttonResume.setScale(buttonScale);
		buttonResume.setAnchorPoint(CGPoint.ccp(1f, 0f));
		
		buttonResumeMenu = CCMenu.menu(buttonResume);
		buttonResumeMenu.setContentSize(buttonResume.getContentSize());
		buttonResumeMenu.setPosition(CGPoint.ccp(screenSize.width, 0f));
		
		/*< ScoresBG >*/
		CCSprite scoreBG = CCSprite.sprite("scoreBG.png");
		scoreBG.setScaleY(buttonScale);
		scoreBG.setScaleX(screenSize.width/scoreBG.getContentSize().width);
		scoreBG.setAnchorPoint(CGPoint.ccp(0f, 0f));
		scoreBG.setPosition(CGPoint.ccp(0, 0));
		addChild(scoreBG);
		
		addChild(buttonPauseMenu);
		
		/*< Scores >*/
		CCLabel scoresTitle = CCLabel.makeLabel("Scores:", "HeadingFont.ttf", 24);
		ccColor3B colorScores = ccColor3B.ccc3(225, 0, 0);
		scoresTitle.setScale(buttonScale);
		scoresTitle.setColor(colorScores);
		scoresTitle.setAnchorPoint(CGPoint.ccp(1f, 0.5f));
		scoresTitle.setPosition(CGPoint.ccp(screenSize.width/(4f), buttonPause.getContentSize().height*buttonScale/(2f)));
		addChild(scoresTitle);
				
		start = true;	//start = true; pause = false;
		interval = (float)time_s/(100f);
		this.schedule("update", interval);
		
		buttonHeight = buttonPause.getContentSize().height*buttonScale;
		offsetHeight = progressBarBG.getContentSize().height*progBarScaleY;
		availableWidth = screenSize.width;
		availableHeight = screenSize.height - offsetHeight - scoreBG.getContentSize().height*buttonScale;	
		elementWidth = availableWidth/numOfPic;
		elementHeight = availableHeight/numOfPic;
		CCSprite icon = CCSprite.sprite("coverIcon/icon.png");
		elementScaleX = elementWidth/icon.getContentSize().width;
		elementScaleY = elementHeight/icon.getContentSize().height;
		
		/*< scores >*/
		scores = CCLabel.makeLabel(String.valueOf(MainActivity.SCORES), "HeadingFont.ttf", 24);
		colorScores = ccColor3B.ccc3(225, 255, 0);
		scores.setScale(buttonScale);
		scores.setColor(colorScores);
		scores.setAnchorPoint(CGPoint.ccp(0f, 0.5f));
		scores.setPosition(CGPoint.ccp((3f)*screenSize.width/(10f), buttonHeight/(2f)));
		addChild(scores);
		
		/*< Pic Background >*/
		randomBackgroundPic();
		
		addElements();
	}
	
	@Override
	public boolean ccKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) 
			CCDirector.sharedDirector().replaceScene(MainMenuLayer.scene());
	    return super.ccKeyDown(keyCode, event);
	}
	
//	public void buttonStartCallBack(Object sender) {
//		if(MainActivity.AUDIO)
//			SoundEngine.sharedEngine().playEffect(MainMenuLayer.context, R.raw.click);
//		start = true;
//		this.removeChild(buttonStartMenu, true);
//		this.addChild(buttonPauseMenu);
//	}
	
	public void buttonPauseCallBack(Object sender) {
		if(MainActivity.AUDIO)
			SoundEngine.sharedEngine().playEffect(MainMenuLayer.context, R.raw.click);
		start = false;
		this.removeChild(buttonPauseMenu, true);
		this.addChild(buttonResumeMenu);
	}
	
	public void buttonResumeCallBack(Object sender) {
		if(MainActivity.AUDIO)
			SoundEngine.sharedEngine().playEffect(MainMenuLayer.context, R.raw.click);
		start = true;
		this.removeChild(buttonResumeMenu, true);
		this.addChild(buttonPauseMenu);
	}
	
	public void initGlobVar() {
		checkToWin = 0;
		combo = 0;
		checks = 0;
		flagCombo = false;
		if(MainActivity.LEVEL == 1)
			numOfPic = 4;
		else if(MainActivity.LEVEL == 2)
			numOfPic = 6;
		else if(MainActivity.LEVEL == 3)
			numOfPic = 8;
		
		screenSize = CCDirector.sharedDirector().winSize();
		
		backgroundArr = new String[11];
		
		rowColChecked = new int[2][2];
		rowColChecked[0][0] = -1;
		rowColChecked[0][1] = -1;
		rowColChecked[1][0] = -2;
		rowColChecked[1][1] = -2;
		
		if(MainActivity.DIFFICULT.equals("easy"))
			time_s = 120;
		else if(MainActivity.DIFFICULT.equals("medium"))
			time_s = 90;
		else time_s = 60;
	}
	
	public void randomBackgroundPic() {
		for(int i = 0; i < 11; i++) {
			backgroundArr[i] = "background/backgroundGame" + String.valueOf(i) + ".jpg";
		}
		Random rand = new Random();
		int numPic = rand.nextInt(11);
		
		/*< Pic Background >*/
		CCSprite backgroundGame = CCSprite.sprite(backgroundArr[numPic]);
		backgroundGame.setScaleX(screenSize.width/backgroundGame.getContentSize().width);
		backgroundGame.setScaleY(availableHeight/backgroundGame.getContentSize().height);
		backgroundGame.setAnchorPoint(CGPoint.ccp(0.5f,1f));
		backgroundGame.setPosition(CGPoint.ccp(screenSize.width/(2f), screenSize.height - progressBarBG.getContentSize().height*progBarScaleY));
		addChild(backgroundGame);
	}

	public static CCScene scene() {
		CCScene scene = CCScene.node();
		CCLayer layer = new GamePlayLayer();
		scene.addChild(layer);
				
		return scene;
	}
	
	public void addElements() {		
		iconArr = new CCSprite[(int)numOfPic][(int)numOfPic];
		spriteArr = new CCSprite[(int)numOfPic][(int)numOfPic];
				
		numOfElement = (int)numOfPic*(int)numOfPic;
		int[] num = new int[numOfElement];
		matrixElements = new int[(int)numOfPic][(int)numOfPic];
		checked = new boolean[numOfElement];
		int n = 0;
		int tmp;
		
		/* init arrays >*/
		for(int i = 0; i < numOfElement; i++) {
			num[i] = 0;
		}	
		for(int i = 0; i < numOfElement; i++) {
			checked[i] = false;
		}
		
		/*< random >*/
		Random rand = new Random();		
		while(n < numOfElement) {
			tmp = rand.nextInt(numOfElement-1);
			if(num[tmp] == 0) {				
				if(n < numOfElement/2)
					num[tmp] = n;
				else num[tmp] = numOfElement - n- 1;
				n++;
			}
		}
		
		String[] arr = new String[numOfElement/2];
		for(int t = 0; t < numOfElement/2; t++) {
			arr[t] = "element/element" + String.valueOf(t+1) + ".png";
		}
				
		numOfElement = 0;
		for(int i = 0; i < numOfPic; i++) {
			for(int j = 0; j < numOfPic; j++) {
				CCSprite sprite = CCSprite.sprite(arr[num[numOfElement]]);
				matrixElements[i][j] = num[numOfElement];
				sprite.setAnchorPoint(0.5f, 0.5f);
				sprite.setScaleX(elementScaleX);
				sprite.setScaleY(elementScaleY);
				spriteArr[i][j] = sprite;
				spriteArr[i][j].setPosition(elementWidth/(2f) + (float)j*elementWidth, screenSize.height - offsetHeight - elementHeight/(2f) - (float)i*elementHeight);
				addChild(spriteArr[i][j]);
				
				numOfElement++;
			}
		}
		
		for(int i = 0; i < numOfPic; i++) {
			for(int j = 0; j < numOfPic; j++) {
				CCSprite ic = CCSprite.sprite("coverIcon/icon.png");
				ic.setAnchorPoint(0.5f, 0.5f);
				ic.setScaleX(elementScaleX);
				ic.setScaleY(elementScaleY);
				iconArr[i][j] = ic;
				iconArr[i][j].setPosition(elementWidth/(2f) + (float)j*elementWidth, screenSize.height - offsetHeight - elementHeight/(2f) - (float)i*elementHeight);
				addChild(iconArr[i][j]);
			}
		}
	}
	
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		float X = event.getX();
		float Y = event.getY();
		int COLUMNs = 0;
		int ROWs = 0;
		
		if(start) {
			if((X > 0 && X < numOfPic*elementWidth) && (Y > (offsetHeight) && Y < (numOfPic*elementHeight))) {
				checks++;
				/* disable touch after the second touch*/
				if(checks == 2)
					this.setIsTouchEnabled(false);
				
				if(MainActivity.AUDIO)
					SoundEngine.sharedEngine().playEffect(MainMenuLayer.context, R.raw.click);
				
				/* looking for the location of touched element*/
				for(int i = 0; i < numOfPic; i++) {
					if(Y < (elementHeight + (float)i*elementHeight + offsetHeight)) {
						ROWs = i;
						break;
					}
				}
				for(int j = 0; j < numOfPic; j++) {
					if(X < (elementWidth + (float)j*elementWidth)) {
						COLUMNs = j;
						break;
					}
				}
				
				/* check the existence of the touched element(s) */
				if(checked[(int)numOfPic*ROWs+COLUMNs] == false) {	//exist-->save the location of the touched elements to compare				
					if(checks == 1) {
						rowColChecked[0][0] = ROWs;
						rowColChecked[0][1] = COLUMNs;
					}
					if(checks == 2) {
						rowColChecked[1][0] = ROWs;
						rowColChecked[1][1] = COLUMNs;
					}				
				}else { //not exist-->no compare
					checks--;
					this.setIsTouchEnabled(true);
					return true;
				}
				
				/* touch at one location */
				if((rowColChecked[0][0] == rowColChecked[1][0]) && (rowColChecked[0][1] == rowColChecked[1][1])) {
					checks = 1;
					rowColChecked[1][0] = -2;
					rowColChecked[1][1] = -2;
					this.setIsTouchEnabled(true);
					return true;
				}
				
				float actualDuration = 0.3f;
				CCAnimation anim = CCAnimation.animation("coverIcon/icon.png");
				anim.addFrame("coverIcon/icon1.png");
				anim.addFrame("coverIcon/icon2.png");
				anim.addFrame("coverIcon/icon3.png");
				anim.addFrame("coverIcon/icon4.png");
				anim.addFrame("coverIcon/icon5.png");
				anim.addFrame("coverIcon/icon6.png");
				anim.addFrame("coverIcon/icon7.png");
				CCAnimate animate = CCAnimate.action(actualDuration, anim, false);
				CCCallFuncN removeIcon = CCCallFuncN.action(this, "removeIcon");
				
				if(checks == 2) { // will compare two touched elements
					checks = 0;
					CCCallFuncN checkElement = CCCallFuncN.action(this, "checkElement");
					CCSequence actions = CCSequence.actions(animate, removeIcon, checkElement);
					iconArr[ROWs][COLUMNs].runAction(actions);
				}else {
					this.setIsTouchEnabled(true);
					CCSequence actions = CCSequence.actions(animate, removeIcon);
					iconArr[ROWs][COLUMNs].runAction(actions);
				}
			}
		}
		
		return true;
	}
	
	public void removeIcon(Object sender) {
		removeChild((CCSprite)sender, true);
	}
	
	public void checkElement(Object sender) {
		this.setIsTouchEnabled(false);
		int row1,row2, col1, col2;
		row1 = rowColChecked[0][0];
		col1 = rowColChecked[0][1];
		row2 = rowColChecked[1][0];
		col2 = rowColChecked[1][1];
		
		if(matrixElements[row1][col1] == matrixElements[row2][col2]) {
			MainActivity.SCORES += 50;
			
			if(flagCombo) {
				combo++;
				if(combo >= 3)
					MainActivity.SCORES += 10*combo;
			}
			checked[(int)numOfPic*row1 + col1] = true;
			checked[(int)numOfPic*row2 + col2] = true;
			
			removeChild(spriteArr[row1][col1], true);
			removeChild(spriteArr[row2][col2], true);
			
			/*< Scores >*/
			removeChild(scores, true);
			scores = CCLabel.makeLabel(String.valueOf(MainActivity.SCORES), "HeadingFont.ttf", 24);
			ccColor3B colorScores = ccColor3B.ccc3(225, 255, 0);
			scores.setScale(buttonScale);
			scores.setColor(colorScores);
			scores.setAnchorPoint(CGPoint.ccp(0f, 0.5f));
			scores.setPosition(CGPoint.ccp((3f)*screenSize.width/(10f), buttonHeight/(2f)));
			addChild(scores);
			
			flagCombo = true;
			checkToWin += 2;
			if(checkToWin == numOfElement) {
				if(combo >= 2)
					MainActivity.SCORES += 10*(combo++);
				MainActivity.SCORES += 100 - percentage;
				MainActivity.LEVEL++;
				this.setIsTouchEnabled(true);
				if(MainActivity.LEVEL > 2) {
					MainActivity.LEVEL = 1;
					MainActivity.WIN = true;
					CCScene scene = GameOverLayer.scene();
					CCDirector.sharedDirector().replaceScene(scene);
				}else {
					CCScene scene = GamePlayLayer.scene();
					CCDirector.sharedDirector().replaceScene(scene); 
				}
			}
		}else {
			flagCombo = false;
			combo = 0;
			checked[(int)numOfPic*row1 + col1] = false;
			checked[(int)numOfPic*row2 + col2] = false;
			
			iconArr[row1][col1] = CCSprite.sprite("coverIcon/icon.png");
			iconArr[row1][col1].setAnchorPoint(0.5f, 0.5f);
			iconArr[row1][col1].setScaleX(elementScaleX);
			iconArr[row1][col1].setScaleY(elementScaleY);
			iconArr[row1][col1].setPosition(elementWidth/(2f) + (float)col1*elementWidth, screenSize.height - offsetHeight - elementHeight/(2f) - (float)row1*elementHeight);
			addChild(iconArr[row1][col1]);
			
			iconArr[row2][col2] = CCSprite.sprite("coverIcon/icon.png");
			iconArr[row2][col2].setAnchorPoint(0.5f, 0.5f);
			iconArr[row2][col2].setScaleX(elementScaleX);
			iconArr[row2][col2].setScaleY(elementScaleY);
			iconArr[row2][col2].setPosition(elementWidth/(2f) + (float)col2*elementWidth, screenSize.height - offsetHeight - elementHeight/(2f) - (float)row2*elementHeight);
			addChild(iconArr[row2][col2]);
		}
		rowColChecked[0][0] = -1;
		rowColChecked[0][1] = -1;
		rowColChecked[1][0] = -2;
		rowColChecked[1][1] = -2;
		
		this.setIsTouchEnabled(true);
	}
	
	public void update(float dt) {
		if(start) {
			if(percentage < 100)
				percentage++;
			progressBar.setPercentage(percentage);
			
			if(percentage == 100) {
				MainActivity.WIN = false;
				percentage = 0;
				CCScene scene = GameOverLayer.scene();
				CCDirector.sharedDirector().replaceScene(scene);
			}
		}
	}
}
