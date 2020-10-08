
import processing.sound.*; //Import the library that does the sound
SoundFile kickDrum, blop;

float[] x = new float[0];
float[] y = new float[0];
float[] xSpeed = new float[0];
float[] ySpeed = new float[0];
float[] size = new float[0];
float[] r = new float[0];
float[] g = new float[0];
float[] b = new float[0];

boolean paused;

void setup() {
  size(500, 500);
  blop = new SoundFile(this, "blop.wav");
  kickDrum = new SoundFile(this, "kickDrum.wav");
  paused = false;
}

void draw() {
  if(!paused){
    background(0, 255, 255);
    ellipse(mouseX, mouseY, 50, 50);
  
    for (int i = 0; i < x.length; i++) {
      
      println(x.length);
  
      x[i] += xSpeed[i];
      if (x[i] < 0 || x[i] > width) {
        xSpeed[i] *= -1;
      }
  
      y[i] += ySpeed[i];
      if (y[i] < 0 || y[i] > height) {
        ySpeed[i] *= -1;
      }
  
      fill(r[i], g[i], b[i]);
      ellipse(x[i], y[i], size[i], size[i]);
    }
  }
}



void mousePressed() {
  blop.play();
  x = append(x, mouseX);
  y = append(y, mouseY);
  xSpeed = append(xSpeed, random(-5, 5));
  ySpeed = append(ySpeed, random(-5, 5));
  size = append(size, random(10, 20));
  r = append(r, random(256));
  g = append(g, random(256));
  b = append(b, random(256));
}

  void mouseDragged() {
  blop.play();
  x = append(x, mouseX);
  y = append(y, mouseY);
  xSpeed = append(xSpeed, random(-5, 5));
  ySpeed = append(ySpeed, random(-5, 5));
  size = append(size, random(10, 20));
  r = append(r, random(256));
  g = append(g, random(256));
  b = append(b, random(256));
}

void keyPressed(){
  if(key == 'p' || key == 'P'){
    paused = !paused;
  } else if(key == 'r' || key =='R'){
    x = new float[0];
    y = new float[0];
    xSpeed = new float[0];
    ySpeed = new float[0];
    size = new float[0];
    r = new float[0];
    g = new float[0];
    b = new float[0];
  }
}
