/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base.input;

import Base.Handler;
import Base.SpriteSheet;
import Entity.BasicModel;
import Entity.Entity;
import Entity.Models;
import Physics.Model;
import Physics.Vector3D;
import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;


/**
 *
 * @author Bayjose
 */
public class FontInput {
    public SpriteSheet font;
    //percent to scale it, 1 = 100%
    public float fontSize = 1;
    String[][] characterIndex;
    int row = 0;
    int col = 0;
    int width = 0;
    int height = 0;
    public FontInput(String path){
        
        try {
            Scanner s1 = new Scanner(new File("Font/"+path+"/properties.txt"));
             width = s1.nextInt();
             height = s1.nextInt();
             col = s1.nextInt();
             row = s1.nextInt();
//            System.out.println("Widht:"+width+" Height:"+height);
            this.font = new SpriteSheet(width, height, col, row, "Font/"+path+"/font.png");
            this.characterIndex = new String[col][row];
            
            String TotalCharacters = "";
            Scanner s2 = new Scanner(new File("Font/"+path+"/index.txt"));
            try{
                for(int i=0; i<128; i++){
                    TotalCharacters= TotalCharacters+s2.nextLine();
                }
            }catch(java.util.NoSuchElementException ex2){
                
            }
            s2.close();
            int tempIndex = 0;
            for(int y = 0; y<row; y++){
                for(int x = 0; x<col; x++){
                    characterIndex[x][y] = TotalCharacters.charAt(tempIndex)+"";
//                    System.out.println(characterIndex[x][y]);
                    tempIndex++;
                }
            }
            s1.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Entity returnText(Vector3D position, String input){
        Entity text = new BasicModel(Models.generateQuad(new Vector3D(position.getX()-((input.length()/2)*width*fontSize), position.getY(), position.getZ()+Handler.cam.optimalRender), width*fontSize, height*fontSize));
        for(int i=0; i<input.length(); i++){
            loop:{
                for(int y = 0; y<row; y++){
                    for(int x = 0; x<col; x++){
    //                   System.out.println("Looking for:"+(input.charAt(i)+"")+"="+characterIndex[x][y]);
                       if((input.charAt(i)+"").equals(characterIndex[x][y])){
    //                       System.out.println("Found:"+characterIndex[x][y]);
                           if(i==0){
                               text.getModel().assignImageFromSpriteBinder(this.font.getImage(y, x));
                           }else{
                             Model temp = Models.generateQuad(new Vector3D(position.getX()+(i*width*fontSize)-((input.length()/2)*width*fontSize), position.getY(), position.getZ()+Handler.cam.optimalRender), width*fontSize, height*fontSize);
                             temp.assignImageFromSpriteBinder(this.font.getImage(y, x));
                             text.models.add(temp);
                           }
                           break loop;
                        }
                    }   
                }
                Model temp = Models.generateQuad(new Vector3D(position.getX()+(i*width*fontSize)-((input.length()/2)*width*fontSize), position.getY(), position.getZ()+Handler.cam.optimalRender), width*fontSize, height*fontSize);
                temp.assignTexture("Font/error.png");
                text.models.add(temp);
            }
        }
        return text;
    }
    public Entity returnTextbox(Vector3D position, String input){
        Entity text = new BasicModel(Models.generateQuad(new Vector3D(position.getX(), position.getY(), position.getZ()+Handler.cam.optimalRender), (input.length()+4)*width*fontSize, 2*height*fontSize));
        text.getModel().assignTexture("textBox.png");
        for(int i=0; i<input.length(); i++){
            loop:{
                for(int y = 0; y<row; y++){
                    for(int x = 0; x<col; x++){
    //                   System.out.println("Looking for:"+(input.charAt(i)+"")+"="+characterIndex[x][y]);
                       if((input.charAt(i)+"").equals(characterIndex[x][y])){
    //                       System.out.println("Found:"+characterIndex[x][y]);
                           {
                             Model temp = Models.generateQuad(new Vector3D(position.getX()+(i*width*fontSize)-((input.length()/2)*width*fontSize), position.getY(), position.getZ()+Handler.cam.optimalRender), width*fontSize, height*fontSize);
                             temp.assignImageFromSpriteBinder(this.font.getImage(y, x));
                             text.models.add(temp);
                           }
                           break loop;
                        }
                    }   
                }
                System.err.println("Unrecognised character: "+input.charAt(i)+"");
                Model temp = Models.generateQuad(new Vector3D(position.getX()+(i*width*fontSize)-((input.length()/2)*width*fontSize), position.getY(), position.getZ()+Handler.cam.optimalRender), width*fontSize, height*fontSize);
                temp.assignTexture("Font/error.png");
                text.models.add(temp);
            }
        }
        return text;
    }
    
    public Model[] returnTextboxModel(Vector3D position, String input){
        Model[] text = new Model[input.length()+1];
        text[0] = Models.generateQuad(new Vector3D(position.getX(), position.getY(), position.getZ()+Handler.cam.optimalRender), (input.length()+4)*width*fontSize, 2*height*fontSize);
        text[0].assignTexture("textBox.png");
        for(int i=0; i<input.length(); i++){
            loop:{
                for(int y = 0; y<row; y++){
                    for(int x = 0; x<col; x++){
    //                   System.out.println("Looking for:"+(input.charAt(i)+"")+"="+characterIndex[x][y]);
                       if((input.charAt(i)+"").equals(characterIndex[x][y])){
    //                       System.out.println("Found:"+characterIndex[x][y]);
                           {
                             Model temp = Models.generateQuad(new Vector3D(position.getX()+(i*width*fontSize)-((input.length()/2)*width*fontSize), position.getY(), position.getZ()+Handler.cam.optimalRender), width*fontSize, height*fontSize);
                             temp.assignImageFromSpriteBinder(this.font.getImage(y, x));
                             text[i+1]=(temp);
                           }
                           break loop;
                        }
                    }   
                }
                Model temp = Models.generateQuad(new Vector3D(position.getX()+(i*width*fontSize)-((input.length()/2)*width*fontSize), position.getY(), position.getZ()+Handler.cam.optimalRender), width*fontSize, height*fontSize);
                temp.assignTexture("Font/error.png");
                text[i+1]=(temp);
            }
        }
        return text;
    }
            
}
