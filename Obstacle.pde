class Obstacle
{
  float h1 = random(height/2+300);
  float h2 = random(height/2+300);
  float openDist=150;
  float posx=width+200;
  float plength=70;
  
  void drawObstacle(){
    fill(255,0,0);
    rect(posx, 0, plength, h1);
    if(height-h2-0>openDist){
    rect(posx, height-h2, plength, h2);
  }
    if(posx<-100) {
     score+=1;
     posx=width;
     h1=random(height/2);
     h2=random(height/2);
    }
  } 
}
