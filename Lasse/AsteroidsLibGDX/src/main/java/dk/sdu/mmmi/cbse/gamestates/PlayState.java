package dk.sdu.mmmi.cbse.gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.entities.Bullet;
import dk.sdu.mmmi.cbse.entities.Enemy;
import dk.sdu.mmmi.cbse.entities.Player;
import dk.sdu.mmmi.cbse.managers.GameKeys;
import dk.sdu.mmmi.cbse.managers.GameStateManager;
import java.util.ArrayList;

public class PlayState extends GameState {
	
	private ShapeRenderer sr;
	
	private Player player;
        private Enemy enemy;
        private ArrayList<Bullet> player_bullets;
        private ArrayList<Bullet> enemy_bullets;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		sr = new ShapeRenderer();
                
                player_bullets = new ArrayList<Bullet>();
                enemy_bullets = new ArrayList<Bullet>();
		
		player = new Player(player_bullets);
                enemy = new Enemy(enemy_bullets);
		
	}
	
	public void update(float dt) {
		//het user imputs
		handleInput();
		
                //update player
		player.update(dt);
                enemy.update(dt);
		
                //update bullets
                updateBullets(player_bullets, dt);
                updateBullets(enemy_bullets, dt);

                
                
	}
	private void updateBullets(ArrayList<Bullet> bullets, float dt){
                            for(int i = 0; i<bullets.size(); i++){
                    bullets.get(i).update(dt);
                    if(bullets.get(i).shouldRemove()){
                        bullets.remove(i);
                        i--;
                    }
                }
        }
        private void drawBullets(ArrayList<Bullet> bullets){
             for(int i = 0; i<bullets.size(); i++){
                    bullets.get(i).draw(sr);
                }
        }
	public void draw() {
		player.draw(sr);
                enemy.draw(sr);
                
                //bullets
                drawBullets(player_bullets);
                drawBullets(enemy_bullets);
	}
	
	public void handleInput() {
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
                if(GameKeys.isPressed(GameKeys.SPACE)){
                    player.shoot();
                }
	}
	
	public void dispose() {}
	
}









