class Flyingball{
 float posy = 46;
float posx = 56;
float yfall = 5;
float yup = 90;
void drawBall(){
  fill(137);
  ellipse(posx, posy, 55,55);
}
void jump(){
posy=posy-yup;
}
void drag(){
posy+=yfall;
}

}
