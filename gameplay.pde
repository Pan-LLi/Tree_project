PImage img;
Obstacle o1=new Obstacle();
Obstacle o2=new Obstacle();
Obstacle o3=new Obstacle();
Obstacle o4=new Obstacle();
Obstacle o5=new Obstacle();
Flyingball ball=new Flyingball();
int speed;
boolean cashed=false;
int score = 0;
//Pressed UP on keyboard to play the game!
void setup(){
  size(400,800);
  img=loadImage("image1.jpg");
  o1.posx = width+50;
  o2.posx = width+150;
  o3.posx = width+230;
  o4.posx = width+310;
  o5.posx = width+400;
}
 
void draw(){
  background(img);
  ball.drawBall();
  o1.drawObstacle();
  o2.drawObstacle();
  o3.drawObstacle();
  o4.drawObstacle();
  o5.drawObstacle();
  ball.drag();
  collisionCheck();
  checkPosition(o1,ball);
  checkPosition(o2,ball);
  checkPosition(o3,ball);
  checkPosition(o4,ball);
  checkPosition(o5,ball);
}
void keyPressed(){
 if(keyCode==UP){
  ball.jump();
 }
 }
 
void collisionCheck(){
  if(cashed==false)
  {
    speed = 2;
    o1.posx-=speed;
    o2.posx-=speed;
    o3.posx-=speed;
    o4.posx-=speed;
    o5.posx-=speed;
    textSize(24);
    fill(255,255,255);
    textAlign(LEFT);
    text(score, 10, 30);   
  }
 
  if(cashed==true)
  {
    speed = 0;
    o1.posx-=speed;
    o2.posx-=speed;
    o3.posx-=speed;
    o4.posx-=speed;
    o5.posx-=speed;
    textSize(30);
    fill(0, 0, 128);
    textAlign(CENTER);
    text("Score "+score,width/2,height/2-50);
    text("Gameover",width/2,height/2);
    if(mousePressed==true) {
       score=0;
       cashed=false;
    }  
  }
}

void checkPosition(Obstacle o,Flyingball ball){
  if(ball.posy<o.h1 || ball.posy>height-o.h2){
    if(ball.posx>o.posx && ball.posx<o.posx+o.plength){
      cashed=true;
    }
  }
}
