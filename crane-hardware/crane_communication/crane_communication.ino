#include <Servo.h>
#define MAG_START_ANGLE 10
#define MAG_END_ANGLE 90 // higher is right or counter-clockwise
#define MAIN_START_ANGLE 110 // higher is forwards
#define LINK_START_ANGLE 85 //higher is up
#define BASE_START_ANGLE 90 //higher is counter-clockwise / left

Servo mag_servo;
Servo main_servo;
Servo link_servo;
Servo base_servo;

int mag_pin = 9;
int main_pin = 10;
int link_pin = 11;
int base_pin = 6;

int mag_angle = MAG_START_ANGLE;
int main_angle = MAIN_START_ANGLE;
int link_angle = LINK_START_ANGLE;
int base_angle = BASE_START_ANGLE;

int mag_state = 0; // 0 open at 10 degrees, 1 closed at 90 degrees

char in_byte = 0;

void setup() {
  Serial.begin(9600);
  mag_servo.attach(mag_pin);
  main_servo.attach(main_pin);
  link_servo.attach(link_pin);
  base_servo.attach(base_pin);
  resetCranePositions();
}

void loop() {
  while (!Serial.available()){
    return;
  }
  in_byte = Serial.read();
  
  if (in_byte == '1'){
    /*if (mag_state == 0){
      mag_state = 1;
      for (mag_pos = MAG_START_ANGLE; mag_pos <= MAG_END_ANGLE; mag_pos++) {
        mag_servo.write(mag_pos);
        
      }
    } else {
      mag_state = 0;
      for (mag_pos = MAG_END_ANGLE; mag_pos >= MAG_START_ANGLE; mag_pos--) {
        mag_servo.write(mag_pos);
        
      }
    }*/
    
    Serial.print("*");
  }
}

void resetCranePositions(){
  setMagAngle(MAG_START_ANGLE, 1000);
  setMainAngle(MAIN_START_ANGLE, 1000);
  setLinkAngle(LINK_START_ANGLE, 1000);
  setBaseAngle(BASE_START_ANGLE, 1000);
}

void setMagAngle(int angle, int milliseconds){
  if (angle > mag_angle){
    while (mag_angle <= angle){
      mag_angle++;
      mag_servo.write(mag_angle);
      delay(milliseconds);
    }
  } else {
    while (mag_angle >= angle){
      mag_angle--;
      mag_servo.write(mag_angle);
      delay(milliseconds);
    }
  }
}

void setMainAngle(int angle, int milliseconds){
  if (angle > main_angle){
    while (main_angle <= angle){
      main_angle++;
      main_servo.write(mag_angle);
      delay(milliseconds);
    }
  } else {
    while (main_angle >= angle){
      main_angle--;
      main_servo.write(main_angle);
      delay(milliseconds);
    }
  }
}

void setLinkAngle(int angle, int milliseconds){
  if (angle > link_angle){
    while (link_angle <= angle){
      link_angle++;
      link_servo.write(link_angle);
      delay(milliseconds);
    }
  } else {
    while (link_angle >= angle){
      link_angle--;
      link_servo.write(link_angle);
      delay(milliseconds);
    }
  }
}

void setBaseAngle(int angle, int milliseconds){
  if (angle > base_angle){
    while (base_angle <= angle){
      base_angle++;
      base_servo.write(base_angle);
      delay(milliseconds);
    }
  } else {
    while (base_angle >= angle){
      base_angle--;
      base_servo.write(base_angle);
      delay(milliseconds);
    }
  }
}
