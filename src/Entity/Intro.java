/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Game;
import Base.Handler;
import Base.SpriteBinder;
import Base.util.EnumGameState;
import Physics.Model;
import Physics.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bailey
 */
public class Intro extends Entity{

    private Handler handler;

    private int curTicks = 0;
    private final int maxTicks = 90;
    
    public Intro(Model model, Handler handler) {
        super(model);
        model.assignTexture("into.png");
        model.offset.increaseVelX(Game.WIDTH/2);
        Model gear = Models.generateQuad(new Vector3D(Game.WIDTH/2, 0, Handler.cam.optimalRender), 128);
        gear.assignTexture("Gear.png");
        this.models.add(gear);
        this.handler = handler;
    }

    public void update() {
        this.models.get(1).RotateYOnlyPoints(2.4);
        if(this.curTicks>=this.maxTicks){
            this.remove = true;
            handler.egs = EnumGameState.Main;
        }
        if(this.curTicks<this.maxTicks){
            this.curTicks++;
        }
    }
    
    protected void render(Graphics g) {
        
    }

    public void dead() {
        
    }
    
}
