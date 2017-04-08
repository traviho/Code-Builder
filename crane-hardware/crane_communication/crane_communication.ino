#include <Servo.h>
#define MAG_START_POS 10
#define MAG_END_POS 90

Servo mag_servo;
int mag_pos = MAG_START_POS;
int mag_state = 0; // 0 open at 10 degrees, 1 closed at 90 degrees

char in_byte = 0;

void setup() {
  Serial.begin(9600);
  mag_servo.attach(9);
  mag_servo.write(MAG_START_POS);
}

void loop() {
  while (!Serial.available()){
    return;
  }
  in_byte = Serial.read();
  
  if (in_byte == '1'){
    if (mag_state == 0){
      mag_state = 1;
      for (mag_pos = MAG_START_POS; mag_pos <= MAG_END_POS; mag_pos++) {
        mag_servo.write(mag_pos);
        
      }
    } else {
      mag_state = 0;
      for (mag_pos = MAG_END_POS; mag_pos >= MAG_START_POS; mag_pos--) {
        mag_servo.write(mag_pos);
        
      }
    }
    
    Serial.print("*");
  }
}
