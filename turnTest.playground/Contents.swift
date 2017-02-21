var Kp = 0.6;
var finished = false;
var angle = 60.0;
var gyroAngle = 0.0;

var error = (angle - gyroAngle)/90.0;
var setSpeed = Kp * (error);
    
if (abs(gyroAngle - angle) >= 2.5){
    print("turn \(setSpeed)")
}
else {
    finished = true;
}
