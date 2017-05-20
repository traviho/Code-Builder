#include <SoftwareSerial.h>

#include <Servo.h> 
Servo base_servo, mag_servo, main_servo, link_servo;

int bluetoothTx = 6;
int bluetoothRx = 7;
int speed = 1000;
char in_byte = 0;

SoftwareSerial bluetooth(bluetoothTx, bluetoothRx);

void setup()
{
  base_servo.attach(8);
  mag_servo.attach(9);
  main_servo.attach(10);
  link_servo.attach(11);
  //Setup usb serial connection to computer
  Serial.begin(9600);
  //Setup Bluetooth serial connection to android
  bluetooth.begin(9600);
  neutral23();
}

void loop()
{
  while (!Serial.available()){
    return;
  }
  in_byte = Serial.read();
  
  if (in_byte == '1'){
    swapFirst();
  } else if (in_byte == '2'){
    swapSecond();
  } else if (in_byte == '3'){
    swapThird();
  } 
  /*;*/
}

void swapFirst(){
  neutral23();
  
  neutral21();
  pick21();
  neutral21();
  
  neutral26();
  drop26();
  neutral26();
  
  neutral22();
  pick22();
  neutral22();

  neutral21();
  drop21();
  neutral21();

  neutral26();
  pick26();
  neutral26();

  neutral22();
  drop22();
  neutral22();
}

void swapSecond(){
  neutral23();
  
  neutral22();
  pick22();
  neutral22();
  
  neutral26();
  drop26();
  neutral26();
  
  neutral23();
  pick23();
  neutral23();

  neutral22();
  drop22();
  neutral22();

  neutral26();
  pick26();
  neutral26();

  neutral23();
  drop23();
  neutral23();
}

void swapThird(){
  neutral23();
  pick23();
  neutral23();
  
  neutral26();
  drop26();
  neutral26();
  
  neutral24();
  pick24();
  neutral24();

  neutral23();
  drop23();
  neutral23();

  neutral26();
  pick26();
  neutral26();

  neutral24();
  drop24();
  neutral24();
}

/* Grid 21*/

void neutral21(){
  neutral(112, 115, 120);
}

void drop21(){
  dropBlock(112, 112, 76);
}

void pick21(){
  pickBlock(112, 112, 76);
}

/* Grid 22*/

void neutral22(){
  neutral(90, 105, 120);
}

void drop22(){
  dropBlock(100, 120, 76);
}

void pick22(){
  pickBlock(100, 120, 76);
}

/* Grid 23*/

void neutral23(){
  neutral(82, 112, 120);
}

void drop23(){
  dropBlock(82, 110, 80);
}

void pick23(){
  pickBlock(82, 110, 76);
}

/* Grid 24 */

void neutral24(){
  neutral(66, 112, 120);
}

void drop24(){
  dropBlock(66, 120, 80);
}

void pick24(){
  pickBlock(66, 120, 76);
}

/* Grid 25 */

void neutral25(){
  neutral(42, 112, 120);
}

void drop25(){
  dropBlock(42, 125, 80);
}

void pick25(){
  pickBlock(42, 125, 76);
}

void neutral26(){
  neutral(46, 128, 120);
}

void drop26(){
  dropBlock(48, 128, 80);
}

void pick26(){
  pickBlock(48, 128, 76);
}

void pickBlock(int base_angle, int main_angle, int link_angle){
  base_servo.write(base_angle);
  mag_servo.write(90);
  main_servo.write(main_angle);
  link_servo.write(link_angle);
  delay(500);
  base_servo.write(base_angle + 3);
  main_servo.write(main_angle - 3);
  delay(500);
  base_servo.write(base_angle - 6);
  delay(500);
  main_servo.write(main_angle + 6);
  delay(500);
  base_servo.write(base_angle + 6);
  delay(500);
}

void dropBlock(int base_angle, int main_angle, int link_angle){
  base_servo.write(base_angle);
  delay(400);
  main_servo.write(main_angle);
  link_servo.write(link_angle);
  delay(1000);
  mag_servo.write(10);
  delay(400);
}

void neutral(int base_angle, int main_angle, int link_angle){
  int i = 0;
  base_servo.write(base_angle);

  mag_servo.write(90);

  main_servo.write(main_angle);

  link_servo.write(link_angle + 10);
  delay(1000);
}
