package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MovementNextGen", group = "Linear")
public class MovementNextGen extends LinearOpMode
{
    private DcMotor topLeft;
    private DcMotor topRight;
    private DcMotor bottomLeft;
    private DcMotor bottomRight;
   // private Servo b360;
   // private Servo m360;
    //private Servo t360;
    @Override
    public void runOpMode () throws InterruptedException
    {

        topLeft = hardwareMap.dcMotor.get("topLeft");
        topRight = hardwareMap.dcMotor.get("topRight");
        bottomLeft = hardwareMap.dcMotor.get("bottomLeft");
        bottomRight = hardwareMap.dcMotor.get("bottomRight");
       // b360 = hardwareMap.get(Servo.class, "bottom_arm");
        //m360 = hardwareMap.get(Servo.class, "middle_arm");
        //      t360 = hardwareMap.get(Servo.class, "top_arm");

        waitForStart();

        while (opModeIsActive())
        {
            //topLeft.setPower(.3);
            //topRight.setPower(.3);
            //bottomRight.setPower(.3);
            //bottomLeft.setPower(.3);
           float left_stick_x = gamepad1.left_stick_x;
           float left_stick_y = gamepad1.left_stick_y;
          float right_stick_x = gamepad1.right_stick_x;
           //float turtle2 = -gamepad1.left_stick_x;
           //float sideToSide = gamepad1.right_stick_x;

//            float topLeftSpeed = (throttle - turn + turtle);//+
//            float topRightSpeed = -(throttle + turn + turtle);//-
//            float bottomLeftSpeed = -(throttle + turn - turtle);//+
//            float bottomRightSpeed = (throttle - turn - turtle);//-


            float topLeftSpeed = (-left_stick_x + left_stick_y + right_stick_x);//+
            float topRightSpeed = (-left_stick_x - left_stick_y + right_stick_x);//-
            float bottomLeftSpeed = (left_stick_x + left_stick_y + right_stick_x);//+
            float bottomRightSpeed = (left_stick_x - left_stick_y + right_stick_x);//-


            telemetry.addData("Right JS", -gamepad1.left_stick_y);
            telemetry.addData("Left JS", gamepad1.left_stick_x);
            telemetry.addData("Top Left Motor Pwr",topLeftSpeed );
            telemetry.addData("Top Right Motor Pwr",topRightSpeed );
            telemetry.addData("Btm Left Motor Pwr",bottomLeftSpeed );
            telemetry.addData("Btm Right Motor Pwr",bottomRightSpeed );
            telemetry.update();
            topLeft.setPower(topLeftSpeed);
            topRight.setPower(topRightSpeed);
            bottomLeft.setPower(bottomLeftSpeed);
            bottomRight.setPower(bottomRightSpeed);

            idle();
        }
    }

}
